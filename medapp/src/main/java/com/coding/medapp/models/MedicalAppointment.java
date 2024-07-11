package com.coding.medapp.models;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Future;
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
	private Date appointmentTime;
	
	@Future
	@NotNull(message="Appointment Date is required")
	@DateTimeFormat(pattern = "dd/MM")
	private Date appointmentDate;
	
	@Column(updatable=false)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date createdAt;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date updatedAt;
	
	//-------Constructor Empty --------------
	
	public MedicalAppointment() {}
	
	//----------------------------------

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getAppointmentTime() {
		return appointmentTime;
	}

	public void setAppointmentTime(Date appointmentTime) {
		this.appointmentTime = appointmentTime;
	}

	public Date getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(Date appointmentDate) {
		this.appointmentDate = appointmentDate;
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
	
	//-------Constructor Empty 
	
	//PrePersist
	
		@PrePersist //Before creating a user
		protected void onCreate() {
			this.createdAt = new Date(); //Default current_timestamp
		}
		
		
		@PreUpdate //before update
		protected void onUpdate() {
			this.updatedAt = new Date(); //default current_timestamp on update current_timestamp
		}
	
}

