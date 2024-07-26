package com.coding.medapp.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.coding.medapp.models.Content;
import com.coding.medapp.models.Doctor;
import com.coding.medapp.models.HealthInsurance;
import com.coding.medapp.models.MedicalAppointment;
import com.coding.medapp.models.MedicalHistory;
import com.coding.medapp.models.Rol;
import com.coding.medapp.models.Speciality;
import com.coding.medapp.models.User;
import com.coding.medapp.services.ContentServices;
import com.coding.medapp.services.DoctorServices;
import com.coding.medapp.services.HealthInsuranceServices;
import com.coding.medapp.services.MedicalAppointmentService;
import com.coding.medapp.services.MedicalHistoryServices;
import com.coding.medapp.services.SpecialityServices;
import com.coding.medapp.services.UserServices;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import java.util.List;

@Controller
public class DoctorController {
	
	@Autowired
	private ContentServices contentServices;
	
	@Autowired
	private MedicalHistoryServices medicalHistoryServices;
	

    @Autowired
    private DoctorServices doctorServices;

    @Autowired
    private SpecialityServices specialityServices;

    @Autowired
    private HealthInsuranceServices insuranceServices;
    
    @Autowired
    private MedicalAppointmentService appointmentServices;
    
    @Autowired
    private UserServices userServices;

    
    @GetMapping("/doctor")
    public String doctor(HttpSession session, Model model) {
        // ===== REVISAMOS SESIÓN =====
        User userTemp = (User) session.getAttribute("userInSession"); // Objeto User o null
        if (userTemp == null) {
            return "redirect:/login";
        }

        // ===== REVISAMOS SU ROL =====
        if (userTemp.getRole().equals(Rol.Roles[2])) { // Verifica si el usuario tiene el rol de doctor
            // Obtiene el ID del doctor desde el objeto User
            Long doctorId = userTemp.getDoctor2().getId();

            // Llama al servicio para obtener las citas del día actual para el doctor
            List<MedicalAppointment> appointments = appointmentServices.getAppointmentsForToday(doctorId);

            // Agrega las citas al modelo para que se muestren en la vista
            model.addAttribute("appointments", appointments);
            
            return "welcomeDoctor.jsp"; // 
        } else {
            return "redirect:/"; // Redirige si el rol no es el esperado
        }
    }
			
    @GetMapping("/doctor/{id}")
    public String profileDoctor(@PathVariable("id") Long id, HttpSession session, Model model) {
        User userTemp = (User) session.getAttribute("userInSession"); 
        if (userTemp == null) {
            return "redirect:/login";
        }
        if (userTemp.getRole().equals(Rol.Roles[2])) {
            if (userTemp.getDoctor2().getId() == id) {
                Doctor myDoctor = doctorServices.getDoctor(id);
                User myUser = myDoctor.getDoctor();
                model.addAttribute("user", myUser);
                model.addAttribute("doctor", myDoctor); // Asegúrate de añadir el usuario al modelo
                return "doctorProfile.jsp";
            } else {
                return "redirect:/";
            }
            
        } else {
            return "redirect:/";
        }
    
    }

    @GetMapping("/doctor/edit/{id}")
    public String editDoctorProfile(@PathVariable("id") Long id, HttpSession session, Model model) {
        User userTemp = (User) session.getAttribute("userInSession");
        if (userTemp == null) {
            return "redirect:/login";
        }
        if (userTemp.getDoctor2().getId() == id) {
            Doctor doctor = doctorServices.getDoctor(id);
            List<Speciality> specialties = specialityServices.findAllSpecialties();
            List<HealthInsurance> healthInsurances = insuranceServices.findAllHealthInsurances(); 
            model.addAttribute("doctor", doctor);
            model.addAttribute("specialities", specialties);
            model.addAttribute("healthInsurances", healthInsurances);
            return "doctorProfileEdit.jsp";
        }
        return "redirect:/login";
    }

    @PutMapping("/doctor/update/{id}")
    public String updateProfile(@Valid @ModelAttribute("doctor") Doctor doctorUpdated, BindingResult result, HttpSession session, Model model,
                                @RequestParam(value = "specialitiesDoctor", required = false) Long specialityId,
                                @RequestParam(value = "insurance", required = false) Long insuranceId) {
        User userTemp = (User) session.getAttribute("userInSession");
        if (userTemp == null) {
            return "redirect:/login";
        }
        if (result.hasErrors()) {
            List<Speciality> specialties = specialityServices.findAllSpecialties();
            List<HealthInsurance> healthInsurances = insuranceServices.findAllHealthInsurances(); 
            model.addAttribute("specialities", specialties);
            model.addAttribute("healthInsurances", healthInsurances);
            return "doctorProfileEdit.jsp";
        }
        if (userTemp.getRole().equals(Rol.Roles[2])) {
        	//Obtenemos el doctor
            Doctor existingDoctor = doctorServices.getDoctor(doctorUpdated.getId());
          	//Si selecciono alguna especialidad en el form usa el metodo de AddSpeciality
            if (specialityId != null) { 
                specialityServices.addSpeciality(existingDoctor.getId(), specialityId);
            }
            //Si selecciono alguna obra social - se la agrega
            if(insuranceId != null) {
            	insuranceServices.addInsurance(existingDoctor.getId(), insuranceId);
       	
            }
            //Setea las obras sociales al doc- recibiendo las que tiene el existing doctor
            doctorUpdated.setInsurance(existingDoctor.getInsurance());
            // Setea las especialidades existentes en el doctor actualizado
            doctorServices.saveDoctor(doctorUpdated);
            System.out.println(doctorUpdated.getId());
            return "redirect:/doctor/" + doctorUpdated.getId();
        } else {
            return "redirect:/";
        }
    }
    

    
    
    @GetMapping("/doctor/createMedicalHistory/{id}")
    public String createMedicalHistory(@ModelAttribute("newContent") Content newContent, @PathVariable("id")Long id, Model model) {
    	User myPatient = userServices.getUser(id);
    	model.addAttribute("user", myPatient);
    	
    	return "newMedicalHistory.jsp";
    	
    	
    }
    
    @PostMapping("/createMedicalHistory/{id}")
    public String createMedicalHistoryPatient(@Valid @ModelAttribute("newContent") Content newContent,
                                               @PathVariable("id") Long id,
                                               BindingResult result,
                                               HttpSession session) {
        User userTemp = (User) session.getAttribute("userInSession");
        if (userTemp == null) {
            return "redirect:/login";
        }
        
        if (result.hasErrors()) {
            return "newMedicalHistory.jsp";
        } else {
            // Obtener el paciente
            User myPatient = userServices.getUser(id);
            MedicalHistory medicalHistory = medicalHistoryServices.getMedicalHistory(id);
            if(medicalHistory == null) {
            	medicalHistory = new MedicalHistory();
            	
                medicalHistoryServices.saveMedicalHistory(medicalHistory);
            }
            // Asociar el contenido con el historial médico
            newContent.setMedHistory(medicalHistory);
            newContent.setPatient(myPatient);
            
            // Guardar el contenido 
            contentServices.saveContent(newContent);

            // Agregar el contenido a la lista de contenidos del historial médico
            medicalHistory.getContents().add(newContent);
            medicalHistoryServices.saveMedicalHistory(medicalHistory);

            
            return "redirect:/doctor";
        }
    }


    
}
    