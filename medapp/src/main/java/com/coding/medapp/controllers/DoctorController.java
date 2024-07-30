package com.coding.medapp.controllers;

import java.time.LocalDate;
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

            return "welcomeDoctor.jsp";
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
                                @RequestParam(value = "doctorSpeciality", required = false) Long specialityId,
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
            // Obtener el doctor existente
            Doctor existingDoctor = doctorServices.getDoctor(doctorUpdated.getId());

            // Solo actualiza la especialidad si se proporciona una nueva
            if (specialityId != null) {
                specialityServices.addSpeciality(doctorUpdated.getId(), specialityId);
            } else {
                // Conservar la especialidad existente si no se proporciona una nueva
                doctorUpdated.setDoctorSpeciality(existingDoctor.getDoctorSpeciality());
            }

            // Actualizar el seguro si se selecciona uno nuevo
            if (insuranceId != null) {
                insuranceServices.addInsurance(doctorUpdated.getId(), insuranceId);
            }

            // Establecer los seguros existentes y la especialidad actualizada
            doctorUpdated.setInsurance(existingDoctor.getInsurance());

            // Guardar el doctor actualizado
            doctorServices.saveDoctor(doctorUpdated);

            System.out.println("Updated Doctor ID: " + doctorUpdated.getId());
            return "redirect:/doctor/" + doctorUpdated.getId();
        } else {
            return "redirect:/";
        }
    }


    

    
    
    @GetMapping("/doctor/createMedicalHistory/{id}")
    public String createMedicalHistory(@ModelAttribute("newContent") Content newContent, @PathVariable("id") Long id, Model model, HttpSession session) {
        User userTemp = (User) session.getAttribute("userInSession");
        if (userTemp == null) {
            return "redirect:/login";
        }
        // Verificar si el usuario es un doctor antes de permitir el acceso
        if (!userTemp.getRole().equals(Rol.Roles[2])) { // Ajustar el índice según la posición del rol Doctor
            return "redirect:/"; // Redirige a la página raíz si no es doctor
        }

        User myPatient = userServices.getUser(id);
        model.addAttribute("user", myPatient);

        return "newMedicalHistory.jsp";
    }

    
    @GetMapping("/doctor/medicalHistory/{id}")
    public String viewMedicalHistory(@PathVariable("id") Long id, Model model, HttpSession session) {
        User userTemp = (User) session.getAttribute("userInSession");
        if (userTemp == null) {
            return "redirect:/login";
        }
        // Verifica si el rol del usuario es ni admin ni doctor
        if (!userTemp.getRole().equals(Rol.Roles[0]) && !userTemp.getRole().equals(Rol.Roles[2])) {
            return "redirect:/";
        }

        User patient = userServices.getUser(id);

        // Obtener contenidos del usuario
        List<Content> contents = medicalHistoryServices.getContentsByUserId(id);

        // Obtener historial médico a partir de los contenidos
        MedicalHistory medicalHistory = medicalHistoryServices.getMedicalHistoryFromContents(contents);

        model.addAttribute("patient", patient);
        model.addAttribute("contents", contents);
        model.addAttribute("medicalHistory", medicalHistory);
        Doctor doctor = userTemp.getDoctor2();
        model.addAttribute("doctor", doctor);

        return "medicalHistoryView.jsp";
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
        // Verificar si el usuario es un doctor antes de permitir la creación del historial médico
        if (!userTemp.getRole().equals(Rol.Roles[2])) { // Ajustar el índice según la posición del rol Doctor
            return "redirect:/"; // Redirige a la página raíz si no es doctor
        }

        Doctor doctor = userTemp.getDoctor2();
        Speciality speciality = doctor.getDoctorSpeciality();
        User myPatient = userServices.getUser(id);
        MedicalHistory medicalHistory = medicalHistoryServices.getMedicalHistory(id);

        if (medicalHistory == null) {
            medicalHistory = new MedicalHistory();
            medicalHistoryServices.saveMedicalHistory(medicalHistory);
        }

        // Asociar el contenido con el historial médico
        newContent.setContentSpeciality(speciality);
        newContent.setDate(LocalDate.now());
        newContent.setMedHistory(medicalHistory);
        newContent.setPatient(myPatient);

        // Guardar el contenido
        contentServices.saveContent(newContent);

        // Agregar el contenido a la lista de contenidos del historial médico
        medicalHistory.getContents().add(newContent);

        return "redirect:/doctor";
    }

    
    @GetMapping("/doctor/medicalHistory/{patientId}/edit/{contentId}")
    public String editContent(@PathVariable("patientId") Long patientId,
                              @PathVariable("contentId") Long contentId,
                              Model model,
                              HttpSession session) {
        User userTemp = (User) session.getAttribute("userInSession");
        if (userTemp == null) {
            return "redirect:/login";
        }
        // Verificar si el usuario es un doctor antes de permitir la edición del contenido
        if (!userTemp.getRole().equals(Rol.Roles[2])) { // Ajustar el índice según la posición del rol Doctor
            return "redirect:/"; // Redirige a la página raíz si no es doctor
        }

        Content content = contentServices.getContent(contentId);
        if (content == null) {
            return "redirect:/error"; // Manejo de errores si el contenido no existe
        }

        User patient = content.getPatient();
        Speciality speciality = content.getContentSpeciality();
        LocalDate date = content.getDate();
        MedicalHistory medicalHistory = content.getMedHistory();

        model.addAttribute("medicalHistory", medicalHistory);
        model.addAttribute("date", date);
        model.addAttribute("patient", patient);
        model.addAttribute("speciality", speciality);
        model.addAttribute("editedContent", content);

        return "medicalHistoryEdit.jsp";
    }
    
    
    @PutMapping("/editMedicalHistory")
    public String updateContent(@ModelAttribute("editedContent") Content updatedContent, HttpSession session) {
        User userTemp = (User) session.getAttribute("userInSession");
        if (userTemp == null) {
            return "redirect:/login";
        }
        // Verificar si el usuario es un doctor antes de permitir la actualización del contenido
        if (!userTemp.getRole().equals(Rol.Roles[2])) { // Ajustar el índice según la posición del rol Doctor
            return "redirect:/"; // Redirige a la página raíz si no es doctor
        }

        // Recuperar el contenido existente usando el ID del contenido proporcionado en el formulario
        Content existingContent = contentServices.getContent(updatedContent.getId());
        if (existingContent == null) {
            return "redirect:/error"; // Manejo de errores si el contenido no existe
        }

        // Actualizar el contenido existente con los nuevos datos
        existingContent.setFamilyHistory(updatedContent.getFamilyHistory());
        existingContent.setAllergies(updatedContent.getAllergies());
        existingContent.setTreatment(updatedContent.getTreatment());
        existingContent.setObservations(updatedContent.getObservations());

        contentServices.updateContent(existingContent);
        return "redirect:/doctor"; // Redirige al historial del paciente
    }
 
    @GetMapping("/doctor/nextAppointments")
    public String nextAppointments(Model model, HttpSession session) {
        User userTemp = (User) session.getAttribute("userInSession");
        if (userTemp == null) {
            return "redirect:/login";
        }

        Doctor doctor = userTemp.getDoctor2();
        List<MedicalAppointment> appointments = appointmentServices.getAppointmentsForDoctor(doctor.getId());
        model.addAttribute("appointments", appointments);
        return "appointments.jsp";
    }
}

   

    

    