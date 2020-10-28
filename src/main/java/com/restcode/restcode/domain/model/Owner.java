package com.restcode.restcode.domain.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="owners")
public class Owner extends User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long ruc;

    public Owner planIs(Plan plan) {
        this.setPlan(plan);
        return this;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRuc() {
        return ruc;
    }

    public void setRuc(Long ruc) {
        this.ruc = ruc;
    }

}
