package com.coding.medapp.controllers;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
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
        if (result.hasErrors()) {
            return "register.jsp";
        } else {
            // Hashear contraseña
            String passHash = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
            newUser.setPassword(passHash);
            userServices.register(newUser, result);
            session.setAttribute("userInSession", newUser);
            return "redirect:/inicio";
        }
    }

    // Entramos a Login
    @GetMapping("/login")
    public String login() {
        return "login.jsp";
    }

    // Logeamos el usuario
    @PostMapping("/login")
    public String login(@RequestParam("email") String email, @RequestParam("password") String password, RedirectAttributes redirectAttributes, HttpSession session) {
        User userTryingLogin = userServices.findByEmail(email);

        if (userTryingLogin == null) {
            redirectAttributes.addFlashAttribute("errorLogin", "Wrong email/password");
            return "redirect:/login";
        } else if (BCrypt.checkpw(password, userTryingLogin.getPassword())) {
            session.setAttribute("userInSession", userTryingLogin);
            return "redirect:/inicio";
        } else {
            redirectAttributes.addFlashAttribute("errorLogin", "Wrong email/password");
            return "redirect:/login";
        }
    }

    // Cerrar Session
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("userInSession");
        return "redirect:/";
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