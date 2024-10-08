package com.coding.medapp.controllers;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.coding.medapp.models.Doctor;
import com.coding.medapp.models.HealthInsurance;
import com.coding.medapp.models.MedicalAppointment;
import com.coding.medapp.models.Rol;
import com.coding.medapp.models.Speciality;
import com.coding.medapp.models.User;
import com.coding.medapp.services.DoctorServices;
import com.coding.medapp.services.HealthInsuranceServices;
import com.coding.medapp.services.MedicalAppointmentService;
import com.coding.medapp.services.SpecialityServices;
import com.coding.medapp.services.UserServices;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class UserController {
   
	@Autowired
    private UserServices userServices;
    
	@Autowired
	private SpecialityServices specialityServices;
	
    @Autowired
    private HealthInsuranceServices insuranceServices;
    
    @Autowired
    private DoctorServices doctorServices;
    
    @Autowired
    private MedicalAppointmentService medicalAppointmentServices;

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
        User userTemp = (User) session.getAttribute("userInSession");
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
            redirectAttributes.addFlashAttribute("errorLogin", "Wrong email/password");
            return "redirect:/login";
        } else {
            session.setAttribute("userInSession", userTryingLogin);
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
    @GetMapping("/patient")
    public String welcomePatient(HttpSession session, Model model){
        // =====REVISAMOS SESION=========
        User userTemp = (User) session.getAttribute("userInSession"); //Obj User o null. userInSession es el nombre del atributo en el servicio de sesion
		if(userTemp == null) {
			return "redirect:/login";
		}
        // =====REVISAMOS SU ROL========
        if (userTemp.getRole().equals(Rol.Roles[1])) {
        	List<Speciality> specialities = specialityServices.findAllSpecialties();
        	model.addAttribute("specialities", specialities);
        	List<Doctor> doctors = doctorServices.findAllDoctors();
        	model.addAttribute("doctors", doctors);
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
            if (userTemp.getId() == id) {
                User myPatient = userServices.getUser(id);
                model.addAttribute("user", myPatient); // Asegúrate de añadir el usuario al modelo
                List<MedicalAppointment> appointments = myPatient.getMedicalAppointments();
                if (appointments.isEmpty()) {
                    model.addAttribute("noAppointments", true);
                } else {
                	
                    model.addAttribute("noAppointments", false);
                    model.addAttribute("appointments", appointments);
                }
                return "patientProfile.jsp";     
            } else {
                return "redirect:/";
            }
        }
        return "redirect:/";
    }
    
    
    
    @GetMapping("/patient/search")
    public String SearchBySpeciality(@RequestParam(value="speciality", required= false) Long specialityId,
            HttpSession session,  Model model) {

 // =====REVISAMOS SESION=========
    User userTemp = (User) session.getAttribute("userInSession"); //Obj User o null. userInSession es el nombre del atributo en el servicio de sesion
    if(userTemp == null) {
        return "redirect:/login";
    }
    // =====REVISAMOS SU ROL========
    if (userTemp.getRole().equals(Rol.Roles[1])) {
        List<Doctor> doctors;
        if (specialityId == null) {
            // Si no se selecciona ninguna especialidad, devolver todos los doctores
            doctors = doctorServices.findAllDoctors();
        } else {
            // Filtrar doctores por la especialidad seleccionada
            doctors = doctorServices.findDoctorsBySpeciality(specialityId);
            if (doctors.isEmpty()) {
                model.addAttribute("noDoctorsFound", true);
            }

        }

        // Agregamos la lista de doctores al modelo
        model.addAttribute("doctors", doctors);
        List<Speciality> specialities = specialityServices.findAllSpecialties();
    	model.addAttribute("specialities", specialities);

        // Devolvemos la vista correspondiente
        return "welcomePatient.jsp";
    }
    else {
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
        	if(userTemp.getId() == id) {
	            User user = userServices.getUser(id); // Obtener el usuario para editar
	            //Muestra todas las obras sociales y las manda al jsp
	            List<HealthInsurance> healthInsurances = insuranceServices.findAllHealthInsurances(); 
	            model.addAttribute("healthInsurances", healthInsurances);
	            model.addAttribute("user", user); // Añadir el usuario al modelo
	            return "patientProfileEdit.jsp";
        	} else {
                return "redirect:/";
            }
            
        } else {
            return "redirect:/";
        }
    
    }
    
	            

    @PutMapping("/patient/update/{id}")
    public String updateProfile(@Valid @ModelAttribute("user") User userUpdated, BindingResult result, HttpSession session,Model model) {
        User userTemp = (User) session.getAttribute("userInSession");
        if (userTemp == null) {
            return "redirect:/login";
        }
        if (result.hasErrors()) {
        	List<HealthInsurance> healthInsurances = insuranceServices.findAllHealthInsurances();
        	model.addAttribute("healthInsurances", healthInsurances);
            return "patientProfileEdit.jsp";
        }
        if (userTemp.getRole().equals(Rol.Roles[1])) {
            userServices.saveUser(userUpdated);
            return "redirect:/patient/" + userUpdated.getId();
            
        } else {
            return "redirect:/";
        }
    }
    
    @GetMapping("/patient/calendar/{id}")
    public String patientCalendarDoctor(@PathVariable("id") Long id, HttpSession session, Model model) {
        User userTemp = (User) session.getAttribute("userInSession");
        
        // Verifica si el usuario está en sesión
        if (userTemp == null) {
            return "redirect:/login";
        }

        // Verifica el rol del usuario
        if (userTemp.getRole().equals(Rol.Roles[1])) {
            // Obtén el doctor según el ID
            Doctor myDoctor = doctorServices.getDoctor(id);
            User myUser = myDoctor.getDoctor();
            List<HealthInsurance> insurances = myDoctor.getInsurance();
            Speciality speciality = myDoctor.getDoctorSpeciality();

            // Agrega atributos al modelo para usarlos en la vista
            model.addAttribute("user", myUser);
            model.addAttribute("doctor", myDoctor); 
            model.addAttribute("insurances", insurances);
            model.addAttribute("speciality", speciality);

            // Obtener la fecha actual
            LocalDate now = LocalDate.now();
            int currentMonth = now.getMonthValue();
            int currentYear = now.getYear();

            // Pasar el mes y el año actuales al modelo
            model.addAttribute("currentMonth", currentMonth);
            model.addAttribute("currentYear", currentYear);

            
            // Genera una lista de horarios
            List<String> times = new ArrayList<>();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
            LocalTime startTime = myDoctor.getStartTime();
            LocalTime endTime = myDoctor.getEndTime();
            for (LocalTime time = startTime; time.isBefore(endTime) || time.equals(endTime); time = time.plusMinutes(15)) {
                times.add(time.format(formatter));
            }
            
            if (!myDoctor.getMedicalAppointments().isEmpty()) {
                List<MedicalAppointment> appointmentUse = myDoctor.getMedicalAppointments();
                List<String> timeUses = new ArrayList<>();
    
                for (MedicalAppointment medicalAppointment : appointmentUse) {
                    timeUses.add(medicalAppointment.getAppointmentTime().format(formatter));
                }
    
                List<String> availableTimes = new ArrayList<>(times);
                for (String time : timeUses) {
                    availableTimes.remove(time);
                }
    
                model.addAttribute("times", availableTimes);
                return "calendar.jsp";
            }
            model.addAttribute("times", times);
            return "calendar.jsp";
        } else {
            // Redirige a la página de inicio si el rol no coincide
            return "redirect:/";
        }
    }

    @PostMapping("/update-profile")
    public String updateProfile(@RequestParam("profileImage") MultipartFile profileImage, HttpSession session, RedirectAttributes redirectAttributes) {
        User userInSession = (User) session.getAttribute("userInSession");
        try {
            String imageUrl = userServices.saveProfileImage(profileImage);
            userInSession.setProfileImageUrl(imageUrl);
            userServices.updateUser(userInSession);
            redirectAttributes.addFlashAttribute("successMessage", "Profile image updated successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error uploading profile image.");
        }
        return "redirect:/profile";
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
    
    
    //Cancelar turno
    @PostMapping("/cancelAppointment/{id}")
    public String deleteAppointment(@PathVariable("id")Long id,HttpSession session) {
    	User userTemp = (User) session.getAttribute("userInSession");
        
        if (userTemp == null) {
            return "redirect:/login";
        }

        // =====REVISAMOS SU ROL========
        if (userTemp.getRole().equals(Rol.Roles[1])) {
        	MedicalAppointment cancelDate = medicalAppointmentServices.getAppointmentById(id);
            // Elimina la especialidad con el ID proporcionado
            medicalAppointmentServices.deleteAppointment(cancelDate);
            return "redirect:/patient/" + userTemp.getId();
        } else {
            return "redirect:/";
        }
    }
    
}

