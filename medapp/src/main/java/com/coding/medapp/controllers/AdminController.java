package com.coding.medapp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.coding.medapp.models.Doctor;
import com.coding.medapp.models.HealthInsurance;
import com.coding.medapp.models.Rol;
import com.coding.medapp.models.Speciality;
import com.coding.medapp.models.User;
import com.coding.medapp.services.DoctorServices;
import com.coding.medapp.services.HealthInsuranceServices;
import com.coding.medapp.services.SpecialityServices;
import com.coding.medapp.services.UserServices;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class AdminController {
    
	@Autowired
    private UserServices userServices;
    @Autowired
    private DoctorServices doctorServices;

    @Autowired
    private HealthInsuranceServices healthInsuranceServices;
    
    @Autowired
    private SpecialityServices specialityServices;

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
            model.addAttribute("rol", "nada");
            return "welcomeAdmin.jsp";
        } else {
            return "redirect:/";
        }
    }

    @GetMapping("/admin/userList")
    public String adminUserList(HttpSession session, Model model) {
        User userTemp = (User) session.getAttribute("userInSession"); // Obj User o null. userInSession es el nombre del atributo en el servicio de sesion
        if (userTemp == null) {
            return "redirect:/login";
        }
        // =====REVISAMOS SU ROL========
        if (userTemp.getRole().equals(Rol.Roles[0])) {
            // Obtener Lista de usuarios ordenados alfabéticamente
            List<User> userList = userServices.findAllUsersAlphabetically(Rol.Roles[1]);
            model.addAttribute("patients", userList); 
            model.addAttribute("roles", Rol.Roles);
            return "infoPatients.jsp";
        } else {
            return "redirect:/";
        }
    }


    @PutMapping("/patient/editRole/{id}")
    public String updatePatientRole(@PathVariable("id") Long id, @RequestParam(value = "role")String role, HttpSession session, Model model){
        System.out.println("el rol es "+ role);
        User userTemp = (User) session.getAttribute("userInSession"); //Obj User o null. userInSession es el nombre del atributo en el servicio de sesion
        if(userTemp == null) {
            return "redirect:/login";
        }
        // =====REVISAMOS SU ROL========
        if (userTemp.getRole().equals(Rol.Roles[0])) {   
            User userEdit = userServices.getUser(id);
            userEdit.setRole(role);
            userServices.saveRol(userTemp);
            if (role.equals("DOCTOR")) {
                doctorServices.newDoctor(id);
            } 
            return "redirect:/admin/userList";
        } else {
            return "redirect:/";
        }
    }


    @GetMapping("/admin/doctorList")
    public String adminDoctorList(HttpSession session, Model model){
        User userTemp = (User) session.getAttribute("userInSession"); //Obj User o null. userInSession es el nombre del atributo en el servicio de sesion
		if(userTemp == null) {
			return "redirect:/login";
		}
        // =====REVISAMOS SU ROL========
        if (userTemp.getRole().equals(Rol.Roles[0])) {
            //Obtener Lista de pacientes
            List<Doctor> doctorList = doctorServices.findAllDoctors();
            model.addAttribute("doctors", doctorList); 
            model.addAttribute("roles", Rol.Roles);
            return "infoDoctors.jsp";
        } else {
            return "redirect:/";
        }  
    }
    
    @GetMapping("/admin/insuranceList")
    public String adminInsuranceList(@ModelAttribute("newInsurance") HealthInsurance insurance, 
                                      HttpSession session, Model model) {
        User userTemp = (User) session.getAttribute("userInSession");
        if (userTemp == null) {
            return "redirect:/login";
        }
        if (userTemp.getRole().equals(Rol.Roles[0])) {
            List<HealthInsurance> insurances = healthInsuranceServices.findAllHealthInsurancesSorted();
            model.addAttribute("insurances", insurances);
            return "insurances.jsp"; // Verifica que este archivo JSP esté en el lugar correcto
        } else {
            return "redirect:/";
        }
    }

    
    @PostMapping("/addInsurance")
    public String addInsurance(@Valid @ModelAttribute("newInsurance") HealthInsurance insurance, 
                                @RequestParam(value="name", required = false) String insuranceName,
                                BindingResult result, HttpSession session, Model model) {
        if (insuranceName.length() == 0) {
            return "redirect:/admin/insuranceList";
        } else {
            insurance.setName(insuranceName);
            healthInsuranceServices.saveHealthInsurance(insurance);
            return "redirect:/admin/insuranceList";
        }
    }
    
    

    
    @DeleteMapping("/admin/insuranceList/delete/{id}")
    public String deleteInsurance(@PathVariable("id") Long id, HttpSession session) {
        User userTemp = (User) session.getAttribute("userInSession");
        
        if (userTemp == null) {
            return "redirect:/login";
        }

        // =====REVISAMOS SU ROL========
        if (userTemp.getRole().equals(Rol.Roles[0])) {
            // Elimina la especialidad con el ID proporcionado
            healthInsuranceServices.deleteHealthInsurance(id);
            return "redirect:/admin/insuranceList";
        } else {
            return "redirect:/";
        }
    }
    
    
    @GetMapping("/admin/specialitiesList")
    public String adminSpecialitiesList(@ModelAttribute("newSpeciality") Speciality speciality, 
                                        HttpSession session, Model model) {
        User userTemp = (User) session.getAttribute("userInSession"); //Obj User o null. userInSession es el nombre del atributo en el servicio de sesion
        if (userTemp == null) {
            return "redirect:/login";
        }
        // =====REVISAMOS SU ROL========
        if (userTemp.getRole().equals(Rol.Roles[0])) {
            List<Speciality> specialities = specialityServices.findAllSpecialitiesSorted();
            model.addAttribute("specialities", specialities);
            return "specialties.jsp";
        } else {
            return "redirect:/";
        }
    }
    
   @DeleteMapping("/adminDelete/{id}") 
   public String adminDelete(@PathVariable("id")Long id, HttpSession session) {
	   User userTemp = (User) session.getAttribute("userInSession");
       
       if (userTemp == null) {
           return "redirect:/login";
       }

       // =====REVISAMOS SU ROL========
       if (userTemp.getRole().equals(Rol.Roles[0])) {
           // Elimina la especialidad con el ID proporcionado
           userServices.deleteUser(id);
           return "redirect:/admin/adminList";
       } else {
           return "redirect:/";
       }
   }

   @DeleteMapping("/doctorDelete/{id}")
   public String doctorDelete(@PathVariable("id")Long id, HttpSession session) {
	   User userTemp = (User) session.getAttribute("userInSession");
       
       if (userTemp == null) {
           return "redirect:/login";
       }

       // =====REVISAMOS SU ROL========
       if (userTemp.getRole().equals(Rol.Roles[0])) {
           // Elimina la especialidad con el ID proporcionado
           doctorServices.removeDoctorById(id);
           return "redirect:/admin/doctorList";
       } else {
           return "redirect:/";
       }
   }
   
   @DeleteMapping("/patientDelete/{id}")
   public String patientDelete(@PathVariable("id")Long id, HttpSession session) {
	   User userTemp = (User) session.getAttribute("userInSession");
       
       if (userTemp == null) {
           return "redirect:/login";
       }
    // =====REVISAMOS SU ROL========
       if (userTemp.getRole().equals(Rol.Roles[0])) {
           // Elimina la especialidad con el ID proporcionado
           userServices.deleteUser(id);
           return "redirect:/admin/userList";
       } else {
           return "redirect:/";
       }
   }
       
  
    
    @PostMapping("/addSpeciality")
    public String addSpeciality(@Valid @ModelAttribute("newSpeciality") Speciality speciality, 
                                @RequestParam(value="name", required = false) String specialityName,
                                BindingResult result, HttpSession session, Model model) {
        if (specialityName.length() == 0) {
            return "redirect:/admin/specialitiesList";
        } else {
            speciality.setName(specialityName);
            specialityServices.saveSpeciality(speciality);
            return "redirect:/admin/specialitiesList";
        }
    }
    
    @DeleteMapping("/admin/specialitiesList/delete/{id}")
    public String deleteSpeciality(@PathVariable("id") Long id, HttpSession session) {
        User userTemp = (User) session.getAttribute("userInSession");
        
        if (userTemp == null) {
            return "redirect:/login";
        }

        // =====REVISAMOS SU ROL========
        if (userTemp.getRole().equals(Rol.Roles[0])) {
            // Elimina la especialidad con el ID proporcionado
            specialityServices.deleteSpeciality(id);
            return "redirect:/admin/specialitiesList";
        } else {
            return "redirect:/";
        }
    }


    
   
    
    @PutMapping("/doctor/editRole/{id}")
    public String updateDoctorList(@PathVariable("id") Long id, @RequestParam(value = "role")String role, HttpSession session, Model model){
        System.out.println("el rol es "+ role);
        User userTemp = (User) session.getAttribute("userInSession"); //Obj User o null. userInSession es el nombre del atributo en el servicio de sesion
        if(userTemp == null) {
            return "redirect:/login";
        }
        // =====REVISAMOS SU ROL========
        if (userTemp.getRole().equals(Rol.Roles[0])) {   
            User userEdit = userServices.getUser(id);
            Doctor doctorRemove = userEdit.getDoctor2();
            userEdit.setRole(role);
            userServices.saveRol(userTemp);
            if (role.equals("USER")) {
                doctorServices.removeDoctor(doctorRemove);
            } 
            return "redirect:/admin/doctorList";
        } else {
            return "redirect:/";
        }
    }
    
    
    

    @GetMapping("/admin/adminList")
    public String adminAdminList(HttpSession session, Model model){
        User userTemp = (User) session.getAttribute("userInSession"); //Obj User o null. userInSession es el nombre del atributo en el servicio de sesion
		if(userTemp == null) {
			return "redirect:/login";
		}
        // =====REVISAMOS SU ROL========
        if (userTemp.getRole().equals(Rol.Roles[0])) {
            //Obtener Lista de pacientes
            List<User> userList = userServices.findAllUserRol("ADMIN");
            model.addAttribute("admins", userList);
            //model.addAttribute("roles", Rol.Roles);
            return "infoAdmins.jsp";
        } else {
            return "redirect:/";
        }  
    }


    //Buscar usuario por dni
    @GetMapping("/admin/patient")
    public String adminUser(HttpSession session, @RequestParam(value = "dni") String dni, Model model){
        User userTemp = (User) session.getAttribute("userInSession"); //Obj User o null. userInSession es el nombre del atributo en el servicio de sesion
		if(userTemp == null) {
			return "redirect:/login";
		}
        // =====REVISAMOS SU ROL========
        if (userTemp.getRole().equals(Rol.Roles[0])) {
            //Obtener Lista de pacientes
        	// Verificar si el DNI es nulo o inválido
            if (dni.length() == 0) {
                return "redirect:/admin/userList";
            }

            List<User> userList = userServices.usrDni(dni);
            if (!userList.isEmpty()) {
                User user = userList.get(0);
                if (user.getRole().equals("USER")) {
                    model.addAttribute("patients", userList); 
                    model.addAttribute("roles", Rol.Roles);
                    return "infoPatients.jsp";
                }    
                return "redirect:/admin/userList";
            }else {
                return "redirect:/admin/userList";
            }
            
        } else {
            return "redirect:/";
        }  
    }
    
    @GetMapping("/admin/doctor")
    public String adminDoctor(HttpSession session, @RequestParam(value = "dni", required = false) String dni, Model model) {
        User userTemp = (User) session.getAttribute("userInSession");
        if (userTemp == null) {
            return "redirect:/login";
        }

        // Verificar el rol del usuario
        if (userTemp.getRole().equals(Rol.Roles[0])) { // Asegúrate de que Rol.Roles[0] sea el rol admin correcto
            // Verificar si el DNI es válido
            if (dni == null || dni.trim().isEmpty()) {
                return "redirect:/admin/doctorList"; // Redirige si el DNI es nulo o vacío
            }

            // Obtener la lista de doctores según el DNI
            List<Doctor> doctorList = doctorServices.doctorDni(dni);
            if (!doctorList.isEmpty()) {
                model.addAttribute("doctors", doctorList);
                model.addAttribute("roles", Rol.Roles);
                return "infoDoctors.jsp"; // 
            } else {
                return "redirect:/admin/doctorList"; // Nombre del archivo JSP sin .jsp
            }
        } else {
            return "redirect:/";
        }
    }
	
}

