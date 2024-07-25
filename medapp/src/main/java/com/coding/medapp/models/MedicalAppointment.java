package com.coding.medapp.models;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="medicalAppointments")
public class MedicalAppointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Future
    @NotNull(message="Appointment Time is required")
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime appointmentTime;
    
    @Future
    @NotNull(message="Appointment Date is required")
    @DateTimeFormat(pattern = "dd/MM")
    private LocalDate appointmentDate;
    
    @Column(updatable=false)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createdAt;
    
    @NotEmpty(message="Insurance must be required")
    private String appointmentInsurance;
    
    @NotEmpty(message="Speciality must be required")
    private String appointmentSpeciality;
    
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User patient;

    // Propiedad para el estado de la cita
    private String status;

    //-------Constructor Empty --------------
    
    public MedicalAppointment() {}
    
    //----------------------------------

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    

    public String getAppointmentInsurance() {
		return appointmentInsurance;
	}

	public void setAppointmentInsurance(String appointmentInsurance) {
		this.appointmentInsurance = appointmentInsurance;
	}

	public String getAppointmentSpeciality() {
		return appointmentSpeciality;
	}

	public void setAppointmentSpeciality(String appointmentSpeciality) {
		this.appointmentSpeciality = appointmentSpeciality;
	}

	@Future
    @NotNull(message = "Appointment Time is required")
    public LocalTime getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(LocalTime localTime) {
        this.appointmentTime = localTime;
    }

    @Future
    @NotNull(message = "Appointment Date is required")
    public LocalDate getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(LocalDate localDate) {
        this.appointmentDate = localDate;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public User getPatient() {
        return patient;
    }

    public void setPatient(User patient) {
        this.patient = patient;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    

    @PrePersist 
    protected void onCreate() {
        this.createdAt = new Date(); //Default current_timestamp
        this.status = "Scheduled"; // Se establece el estado inicial a "Scheduled"
		// Alterna entre "Scheduled", "Rescheduled" y "Cancelled"
    }
    
    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = new Date(); //default current_timestamp on update current_timestamp
    }
}