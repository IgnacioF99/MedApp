package com.coding.medapp.services;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.YearMonth;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coding.medapp.models.MedicalAppointment;
import com.coding.medapp.models.User;
import com.coding.medapp.repository.MedicalAppointmentRepository;

@Service
public class MedicalAppointmentService {
    @Autowired
    private MedicalAppointmentRepository appointmentRepository;

    public boolean isAppointmentAvailable(LocalDate date, LocalTime time) {
        return appointmentRepository.findByAppointmentDateAndAppointmentTime(date, time).isEmpty();
    }

    public MedicalAppointment createAppointment(MedicalAppointment appointment) {
        if (!isAppointmentAvailable(appointment.getAppointmentDate(), appointment.getAppointmentTime())) {
            throw new IllegalArgumentException("La cita ya ha sido agendada para esta fecha y hora.");
        }
        return appointmentRepository.save(appointment);
    }

    public List<MedicalAppointment> getAppointmentsByPatient(User patient) {
        return appointmentRepository.findByPatient(patient);
    }

    public List<MedicalAppointment> getAppointmentsByDoctor(User doctor) {
        return appointmentRepository.findByDoctor(doctor);
    }

    public MedicalAppointment getAppointmentById(Long id) {
        return appointmentRepository.findById(id).orElse(null);
    }

    public List<MedicalAppointment> getAppointmentsByDate(LocalDate date) {
        return appointmentRepository.findByAppointmentDate(date);
    }

    public void deleteAppointment(MedicalAppointment appointment) {
        appointmentRepository.delete(appointment);
    }

    public void updateAppointment(MedicalAppointment appointment) {
        appointmentRepository.save(appointment);
    }

    public void cancelAppointment(MedicalAppointment appointment) {
        appointment.setStatus("Cancelled");
        appointmentRepository.save(appointment);
    }

    public List<MedicalAppointment> getAppointmentsByMonth(int year, int month) {
        // Obtener el primer y último día del mes
        YearMonth yearMonth = YearMonth.of(year, month);
        LocalDate startDate = yearMonth.atDay(1);
        LocalDate endDate = yearMonth.atEndOfMonth();

        // Buscar citas entre el primer y último día del mes
        return appointmentRepository.findByAppointmentDateBetween(startDate, endDate);
    }

    public List<MedicalAppointment> getAppointmentsForDoctor(Long doctorId) {
        return appointmentRepository.findByDoctorIdOrderByAppointmentDateAscAppointmentTimeAsc(doctorId);
    }

    public List<MedicalAppointment> getAppointmentsForToday(Long doctorId) {
        LocalDate today = LocalDate.now();
        return appointmentRepository.findByDoctorIdAndAppointmentDate(doctorId, today);
    }
<<<<<<< HEAD
    
    public List<MedicalAppointment> getAppointmentsForWeek(Long doctorId) {
        LocalDate today = LocalDate.now();
        LocalDate startOfWeek = today.with(DayOfWeek.MONDAY);
        LocalDate endOfWeek = today.with(DayOfWeek.SUNDAY);
        
        return appointmentRepository.findByDoctorIdAndAppointmentDateBetween(doctorId, startOfWeek, endOfWeek);
    }

    
    
}
=======
}
>>>>>>> 3bf7d91bb82970abc2f2f139561c6c867af0076f
