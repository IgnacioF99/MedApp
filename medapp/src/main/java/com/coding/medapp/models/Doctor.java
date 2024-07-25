package com.coding.medapp.models;

import java.sql.Time;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name="doctors")
public class Doctor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message= "Must be required")
    @Pattern(regexp = "\\d+", message = "La matrícula debe ser un número")
    private String license;

	
	@NotEmpty(message="Must be required")
	private String availability;
	
	@DateTimeFormat(pattern = "HH:mm")
	@NotNull(message="Start time is required")	
	private LocalTime startTime;
	
	@NotNull(message="End time is required")
	@DateTimeFormat(pattern = "HH:mm")
	private LocalTime endTime;
	
	
	
	@Column(updatable=false)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date createdAt;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date updatedAt;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User doctor;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
		name = "doctor_has_specialities",
		joinColumns = @JoinColumn(name = "doctor_id"),
		inverseJoinColumns = @JoinColumn(name = "speciality_id")
	)
	private List<Speciality> specialitiesDoctor;

	@OneToMany(mappedBy = "doctor", fetch = FetchType.LAZY)
	private List<MedicalAppointment> medicalAppointments;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
		name = "doctor_has_insurances",
		joinColumns = @JoinColumn(name = "doctor_id"),
		inverseJoinColumns = @JoinColumn(name = "healthInsurance_id")
	)
	private List<HealthInsurance> insurance;
	
	
	

	//---------------------- Constructor Empty --------------
	public Doctor() {}
	
	 public String getSpecialitiesDoctors() {
	        if (specialitiesDoctor == null || specialitiesDoctor.isEmpty()) {
	            return "Ninguna especialidad";
	        }
	        return specialitiesDoctor.stream()
	                .map(Speciality::getName) // Obtener el nombre de cada especialidad
	                .collect(Collectors.joining(", ")); // Unir los nombres con una coma y espacio
	    }
	//---------------------- Constructor Empty ---------------

	 public String getInsurancesDoctor() {
		    if (insurance == null || insurance.isEmpty()) {
		        return "Ninguna obra social";
		    }
		    return insurance.stream()
		            .map(HealthInsurance::getName) // Obtener el nombre de cada obra social
		            .collect(Collectors.joining(", ")); // Unir los nombres con una coma y espacio
		}
	
	
	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}

	

	public String getLicense() {
		return license;
	}

	public void setLicense(String license) {
		this.license = license;
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

	
	//------------ Getters And Setters ----------
	
	//------------ PrePersist -------------------
	
	public User getDoctor() {
		return doctor;
	}

	public void setDoctor(User doctor) {
		this.doctor = doctor;
	}
	
	

	public String getAvailability() {
		return availability;
	}

	public void setAvailability(String availability) {
		this.availability = availability;
	}
	
	
	


	public LocalTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}

	public LocalTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
	}

	public List<Speciality> getSpecialitiesDoctor() {
		return specialitiesDoctor;
	}

	public void setSpecialitiesDoctor(List<Speciality> specialitiesDoctor) {
		this.specialitiesDoctor = specialitiesDoctor;
	}

	public List<MedicalAppointment> getMedicalAppointments() {
		return medicalAppointments;
	}

	public void setMedicalAppointments(List<MedicalAppointment> medicalAppointments) {
		this.medicalAppointments = medicalAppointments;
	}

	public List<HealthInsurance> getInsurance() {
		return insurance;
	}

	public void setInsurance(List<HealthInsurance> insurance) {
		this.insurance = insurance;
	}

	@PrePersist //Before creating a user
	protected void onCreate() {
		this.createdAt = new Date(); //Default current_timestamp
	}
	
	
	@PreUpdate //before update
	protected void onUpdate() {
		this.updatedAt = new Date(); //default current_timestamp on update current_timestamp
	
	}
	
	//------------ PrePersist -------------------
}
