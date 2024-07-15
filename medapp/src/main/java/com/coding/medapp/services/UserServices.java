package com.coding.medapp.services;

import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.coding.medapp.models.Rol;
import com.coding.medapp.models.User;
import com.coding.medapp.repository.UserRepository;

@Service
public class UserServices {
    @Autowired
    private UserRepository userRepository;

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public User getUser(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    /*Método que registre a un nuevo usuario*/
    public User register(User newUser, BindingResult result) {
        // Comparar las contraseñas
        String password = newUser.getPassword();
        String confirm = newUser.getConfirm();
        if (!password.equals(confirm)) {
            // SI no son iguales
            // path, clave, mensaje
            result.rejectValue("confirm", "Matches", "Password and confirmation don't match");
        }

        // Revisar que el email no esté registrado
        String email = newUser.getEmail();
        User userExist = userRepository.findByEmail(email); // Objeto de User o null
        if (userExist != null) {
            // El correo ya está registrado
            result.rejectValue("email", "Unique", "E-mail already exists");
        }

        //Asignamos el rol
        newUser.setRole(Rol.Roles[1]);

        // Si existe error, regreso null
        if (result.hasErrors()) {
            return null;
        } else {
            // NO HAY ERRORES
            // Hashear contraseña
            String passHash = BCrypt.hashpw(password, BCrypt.gensalt());
            newUser.setPassword(passHash); // Establecemos el password hasheado

            // Asignar rol por defecto
            newUser.setRole(Rol.Roles[1]); // Asignar rol "USER" por defecto

            return userRepository.save(newUser);
        }
    }

    /*Método que revisa que los datos sean correctos para Iniciar Sesión*/
    public User login(String email, String password) {
        // Revisamos que el correo exista en BD
        User userTryingLogin = userRepository.findByEmail(email); // Objeto User o NULL

        if (userTryingLogin == null) {
            return null;
        }

        // Comparar las contraseñas
        // BCrypt.checkpw(Contra NO encriptada, Contra SI encriptada) -> True o False
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
}