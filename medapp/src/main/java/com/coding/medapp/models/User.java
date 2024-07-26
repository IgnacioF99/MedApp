package com.coding.medapp.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotEmpty(message = "First name is required.")
    @Size(min = 2, message = "First name needs at least 2 chars")
    private String firstName;

    @NotEmpty(message = "Last name is required.")
    @Size(min = 2, message = "Last name needs at least 2 chars")
    private String lastName;

    @NotNull(message = "DNI is required.")
    @Min(value = 999999, message = "DNI invalid")
    @Max(value = 99999999, message = "DNI invalid")
    private Integer dni;

    @NotEmpty(message = "Email is required.")
    @Email(message = "Invalid email") //Validar que sea un correo electronico valido
    private String email;

    @NotEmpty(message = "Password is required.")
    @Size(min = 6, message = "Password needs at least 6 chars")
    private String password;
   
    @NotEmpty(message = "Password is required.")
    @Size(min = 6, message = "Password needs at least 6 chars")
    private String confirm;

    private String role;

    private String profileImageUrl;

    @Column(updatable=false)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createdAt;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date updatedAt;

    @OneToMany(mappedBy = "patient", fetch = FetchType.LAZY)
    private List<Content> contents = new ArrayList<>();


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "healthInsurance_id")
    private HealthInsurance insurance;

    @OneToOne(mappedBy = "doctor", fetch = FetchType.LAZY)
    private Doctor doctor2;

    @OneToMany(mappedBy = "patient", fetch = FetchType.LAZY)
    private List<MedicalAppointment> medicalAppointments; 

    //=========================================================

    public User() {
    }
    

    //=========================================================

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getDni() {
        return dni;
    }

    public void setDni(Integer dni) {
        this.dni = dni;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirm() {
        return confirm;
    }

    public void setConfirm(String confirm) {
        this.confirm = confirm;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
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

    //=========================================================

    public List<Content> getContents() {
        return contents;
    }

    public void setContents(List<Content> contents) {
        this.contents = contents;
    }

    public HealthInsurance getInsurance() {
        return insurance;
    }

    public void setInsurance(HealthInsurance insurance) {
        this.insurance = insurance;
    }

    public Doctor getDoctor2() {
        return doctor2;
    }

    public void setDoctor2(Doctor doctor2) {
        this.doctor2 = doctor2;
    }

    public List<MedicalAppointment> getMedicalAppointments() {
        return medicalAppointments;
    }

    public void setMedicalAppointments(List<MedicalAppointment> medicalAppointments) {
        this.medicalAppointments = medicalAppointments;
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = new Date();
    }
    
    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = new Date();
    }
}