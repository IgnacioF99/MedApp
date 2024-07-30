package com.coding.medapp.services;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.YearMonth;
import java.util.List;
import java.util.stream.Collectors;

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
        List<MedicalAppointment> appointments = getAppointmentsByDate(date);
        for (MedicalAppointment appointment : appointments) {
            if (appointment.getAppointmentTime().equals(time)) {
                return false; // El horario ya está ocupado
            }
        }
        return true; // El horario está disponible
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
        YearMonth yearMonth = YearMonth.of(year, month);
        LocalDate startDate = yearMonth.atDay(1);
        LocalDate endDate = yearMonth.atEndOfMonth();
        return appointmentRepository.findByAppointmentDateBetween(startDate, endDate);
    }

    public List<MedicalAppointment> getAppointmentsForDoctor(Long doctorId) {
        return appointmentRepository.findByDoctorIdOrderByAppointmentDateAscAppointmentTimeAsc(doctorId);
    }

    public List<MedicalAppointment> getAppointmentsForToday(Long doctorId) {
        LocalDate today = LocalDate.now();
        List<MedicalAppointment> appointments = appointmentRepository.findByDoctorIdAndAppointmentDate(doctorId, today);
        return appointments.stream()
                           .sorted((a1, a2) -> a1.getAppointmentTime().compareTo(a2.getAppointmentTime()))
                           .collect(Collectors.toList());
    }


}	
