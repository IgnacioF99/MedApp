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

@Entity
@Table(name = "contents")
public class Content {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date date;

    private String treatment;

    private String observations;

    private String vaccines;

    @Column(updatable=false)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createdAt;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date updatedAt;

    
    //=========================================================

    public Content() {
    }

    //=========================================================

    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public Date getDate() {
        return date;
    }


    public void setDate(Date date) {
        this.date = date;
    }


    public String getTreatment() {
        return treatment;
    }


    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }


    public String getObservations() {
        return observations;
    }


    public void setObservations(String observations) {
        this.observations = observations;
    }


    public String getVaccines() {
        return vaccines;
    }


    public void setVaccines(String vaccines) {
        this.vaccines = vaccines;
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

    @PrePersist
    protected void onCreate() {
        this.createdAt = new Date();
    }
    
    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = new Date();
    }

}
