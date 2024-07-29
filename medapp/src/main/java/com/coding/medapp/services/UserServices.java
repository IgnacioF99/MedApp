package com.coding.medapp.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import com.coding.medapp.models.Doctor;
import com.coding.medapp.models.Rol;
import com.coding.medapp.models.User;
import com.coding.medapp.repository.UserRepository;

@Service
public class UserServices {
    @Autowired
    private UserRepository userRepository;

    @Value("${app.upload.directory}")
    private String uploadDirectory;

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    //Listar usuario x roll
    public List<User> findAllUserRol(String rol){
        return userRepository.findByRoleLike(rol);
    }

    //Buscar usuario por id
    public User getUser(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    /*Método que registre a un nuevo usuario*/
    public User register(User newUser, BindingResult result) {
        // Comparar las contraseñas
        String password = newUser.getPassword();
        String confirm = newUser.getConfirm();
        if (!password.equals(confirm)) {
            result.rejectValue("confirm", "Matches", "Password and confirmation don't match");
        }

        // Revisar que el email no esté registrado
        String email = newUser.getEmail();
        User userExist = userRepository.findByEmail(email);
        if (userExist != null) {
            result.rejectValue("email", "Unique", "E-mail already exists");
        }

        // Revisar que el DNI no este registrado
        String dni = newUser.getDni();
        User userExistDni = userRepository.findByDni(dni); 
        if (userExistDni != null) {
            result.rejectValue("dni", "Unique", "DNI already exists");
        }

        // Asignamos el rol
        newUser.setRole(Rol.Roles[1]);

        if (result.hasErrors()) {
            return null;
        } else {
            // Hashear contraseña
            String passHash = BCrypt.hashpw(password, BCrypt.gensalt());
            newUser.setPassword(passHash); // Establecemos el password hasheado
            String confirmHash = BCrypt.hashpw(confirm, BCrypt.gensalt());
            newUser.setConfirm(confirmHash);

            // Asignar rol por defecto
            newUser.setRole(Rol.Roles[1]); // Asignar rol "USER" por defecto

            return userRepository.save(newUser);
        }
    }

    public User login(String email, String password) {
        User userTryingLogin = userRepository.findByEmail(email);
        if (userTryingLogin == null) {
            return null;
        }

        if (BCrypt.checkpw(password, userTryingLogin.getPassword())) {
            return userTryingLogin;
        } else {
            return null;
        }
    }

    public void assignRole(User user, String role) {
        user.setRole(role);
        userRepository.save(user);
    }

    public String getUserRole(User user) {
        return user.getRole();
    }

    public String saveProfileImage(MultipartFile profileImage) throws IOException {
        String fileName = UUID.randomUUID().toString() + profileImage.getOriginalFilename();
        Path filePath = Paths.get(uploadDirectory, fileName);
        Files.copy(profileImage.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        return "/images/" + fileName;
    }

    public void updateUser(User user) {
        userRepository.save(user);
    }

    public User saveUser(User user) {
        // Hashear la contraseña y el confirm
        // Establecer el rol del usuario
        // Asignar el rol "USER" por defecto

        // Guardar el usuario en el repositorio
        return userRepository.save(user);
    }

    public User saveRol(User user){
        return userRepository.save(user);
    }

    public List<User> usrDni(String dni){
        List<User> userdni = new ArrayList<>();
        User user = userRepository.findByDni(dni);
        if (user != null) {
            userdni.add(user);
        }
        return userdni;
    }
    
  
    public void deleteUser(Long id) {
    	userRepository.deleteById(id);
    }
    
    public List<User> findAllUsersAlphabetically(String role) {
        // Filtrar usuarios por rol
        List<User> users = userRepository.findByRoleLike(role);
        // Ordenar alfabéticamente por nombre
        Collections.sort(users, Comparator.comparing(User::getFirstName));
        return users;
    }
    
    
    
    
    
}