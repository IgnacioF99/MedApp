package com.coding.medapp.repository;

import com.coding.medapp.models.MedicalAppointment;
import com.coding.medapp.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MedicalAppointmentRepository extends JpaRepository<MedicalAppointment, Long> {
    List<MedicalAppointment> findByPatient(User patient);
    List<MedicalAppointment> findByDoctor(User doctor);
    List<MedicalAppointment> findByAppointmentDate(LocalDate appointmentDate);
    List<MedicalAppointment> findByAppointmentDateBetween(LocalDate startDate, LocalDate endDate);
    
    List<MedicalAppointment> findByDoctorIdOrderByAppointmentDateAscAppointmentTimeAsc(Long doctorId);

    List<MedicalAppointment> findByDoctorIdAndAppointmentDate(Long doctorId, LocalDate appointmentDate);

}