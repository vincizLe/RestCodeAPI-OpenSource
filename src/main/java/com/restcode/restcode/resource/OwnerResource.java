package com.restcode.restcode.resource;

import com.restcode.restcode.domain.model.User;

public class OwnerResource extends User {

    private Long id;
    private Long ruc;


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
