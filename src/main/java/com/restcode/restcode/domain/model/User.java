package com.restcode.restcode.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public abstract class User {
    @NotNull
    @Column(name = "names",nullable = false)
    private String names;

    @NotNull
    @Column(name = "surnames",nullable = false)
    private String surnames;

    @NotNull
    @Column(name = "emails",nullable = false)
    private String email;

    @NotNull
    @Column(name = "passwords",nullable = false)
    private String password;

    @NotNull
    @Column(name = "phones",nullable = false)
    private Long phone;

    //Falta especificar la relación
    //private Plan plan;


    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getSurnames() {
        return surnames;
    }

    public void setSurnames(String surnames) {
        this.surnames = surnames;
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

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }
}
