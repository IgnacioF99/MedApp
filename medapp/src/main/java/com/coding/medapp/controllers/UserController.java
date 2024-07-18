package com.coding.medapp.controllers;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.coding.medapp.models.Rol;
import com.coding.medapp.models.User;
import com.coding.medapp.services.UserServices;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class UserController {
    @Autowired
    private UserServices userServices;

    // Entramos a Register
    @GetMapping("/register")
    public String register(@ModelAttribute("newUser") User newUser) {
        return "register.jsp";
    }

    // Guardamos un Usuario nuevo
    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("newUser") User newUser, BindingResult result, HttpSession session) {
    	userServices.register(newUser, result);
    	
        if (result.hasErrors()) {
            return "register.jsp";
        } else {
            
            session.setAttribute("userInSession", newUser);
            return "redirect:/inicio";
        }
    }

    // Entramos a Login
    @GetMapping("/login")
    public String login(HttpSession session) {
        // =====REVISAMOS SESION=========
        User userTemp = (User) session.getAttribute("userInSession"); //Obj User o null. userInSession es el nombre del atributo en el servicio de sesion
		if(userTemp != null) {
			return "redirect:/";
		}
        return "login.jsp";
    }

    // Logeamos el usuario
    @PostMapping("/login")
    public String login(@RequestParam("email") String email, @RequestParam("password") String password, RedirectAttributes redirectAttributes, HttpSession session) {
        User userTryingLogin = userServices.login(email, password);

        if (userTryingLogin == null) {
            // Tiene algo mal
            redirectAttributes.addFlashAttribute("errorLogin", "Wrong email/password");
            return "redirect:/login";
        } else {
            session.setAttribute("userInSession", userTryingLogin); // Guardando en sesión el objeto de User
            return "redirect:/inicio";
        }
    }

    // Cerrar Session
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("userInSession");
        return "redirect:/";
    }


    //Inicio de paciente
    @GetMapping("/paciente")
    public String welcomePatient(HttpSession session, Model model){
        // =====REVISAMOS SESION=========
        User userTemp = (User) session.getAttribute("userInSession"); //Obj User o null. userInSession es el nombre del atributo en el servicio de sesion
		if(userTemp == null) {
			return "redirect:/login";
		}
        // =====REVISAMOS SU ROL========
        if (userTemp.getRole().equals(Rol.Roles[1])) {
            return "welcomePatient.jsp";
        } else {
            return "redirect:/";
        }
    }
    
    @GetMapping("/patient/{id}")
    public String profilePatient(@PathVariable("id") Long id, HttpSession session, Model model) {
        User userTemp = (User) session.getAttribute("userInSession"); 
        if (userTemp == null) {
            return "redirect:/login";
        }
        if (userTemp.getRole().equals(Rol.Roles[1])) {
            User myPatient = userServices.getUser(id);
            model.addAttribute("user", myPatient); // Asegúrate de añadir el usuario al modelo
            return "patientProfile.jsp";
        } else {
            return "redirect:/";
        }
    }

    @GetMapping("/patient/edit/{id}")
    public String editProfile(@PathVariable("id") Long id, HttpSession session, Model model) {
        User userTemp = (User) session.getAttribute("userInSession");
        if (userTemp == null) {
            return "redirect:/login";
        }
        if (userTemp.getRole().equals(Rol.Roles[1])) {
            User user = userServices.getUser(id); // Obtener el usuario para editar
            model.addAttribute("user", user); // Añadir el usuario al modelo
            return "patientProfileEdit.jsp";
        } else {
            return "redirect:/";
        }
    }

    @PutMapping("/patient/update/{id}")
    public String updateProfile(@Valid @ModelAttribute("user") User userUpdated, BindingResult result, HttpSession session) {
        User userTemp = (User) session.getAttribute("userInSession");
        if (userTemp == null) {
            return "redirect:/login";
        }
        if (result.hasErrors()) {
            return "patientProfileEdit.jsp";
        }
        if (userTemp.getRole().equals(Rol.Roles[1])) { 	   
        	String hashedPassword = BCrypt.hashpw(userUpdated.getPassword(), BCrypt.gensalt());
        	String hashedConfirm = BCrypt.hashpw(userUpdated.getConfirm(), BCrypt.gensalt());
        	userUpdated.setPassword(hashedPassword);
        	userUpdated.setConfirm(hashedConfirm);
        	userUpdated.setRole(Rol.Roles[1]);
            userServices.saveUser(userUpdated);
            return "redirect:/patient/" + userUpdated.getId();
            
        } else {
            return "redirect:/";
        }
    }

    //Inicio de Doctor
    @GetMapping("/doctor")
    public String welcomeDoctor(HttpSession session, Model model){
        // =====REVISAMOS SESION=========
        User userTemp = (User) session.getAttribute("userInSession"); //Obj User o null. userInSession es el nombre del atributo en el servicio de sesion
		if(userTemp == null) {
			return "redirect:/login";
		}
        // =====REVISAMOS SU ROL========
        if (userTemp.getRole().equals(Rol.Roles[2])) {
            return "welcomeDoctor.jsp";
        } else {
            return "redirect:/";
        }
    }

    //Inicio de Admin
    @GetMapping("/admin")
    public String welcomeAdmin(HttpSession session, Model model){
        // =====REVISAMOS SESION=========
        User userTemp = (User) session.getAttribute("userInSession"); //Obj User o null. userInSession es el nombre del atributo en el servicio de sesion
		if(userTemp == null) {
			return "redirect:/login";
		}
        // =====REVISAMOS SU ROL========
        if (userTemp.getRole().equals(Rol.Roles[0])) {
            return "welcomeAdmin.jsp";
        } else {
            return "redirect:/";
        }
    }



    // Asignar rol a un usuario
    @PostMapping("/assign_role")
    public String assignRole(@RequestParam("userId") Long userId, @RequestParam("role") String role, RedirectAttributes redirectAttributes) {
        User user = userServices.getUser(userId);
        if (user != null) {
            userServices.assignRole(user, role);
            redirectAttributes.addFlashAttribute("successMessage", "Role assigned successfully!");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "User not found.");
        }
        return "redirect:/users";
    }

    // Mostrar usuarios y sus roles
    @GetMapping("/users")
    public String showUsers(Model model) {
        model.addAttribute("users", userServices.findAllUsers());
        return "users.jsp";
    }
}