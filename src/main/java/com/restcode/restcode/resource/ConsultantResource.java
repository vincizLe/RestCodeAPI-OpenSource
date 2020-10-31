package com.restcode.restcode.resource;

import com.restcode.restcode.domain.model.User;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

public class ConsultantResource extends User {

    private Long id;
    private String linkedln;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLinkedln() {
        return linkedln;
    }

    public void setLinkedln(String linkedln) {
        this.linkedln = linkedln;
    }
}
