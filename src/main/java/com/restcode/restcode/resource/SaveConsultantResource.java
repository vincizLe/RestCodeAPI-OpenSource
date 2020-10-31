package com.restcode.restcode.resource;

import com.restcode.restcode.domain.model.User;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SaveConsultantResource extends User{


    private String linkedln;

    public String getLinkedln() {
        return linkedln;
    }

    public void setLinkedln(String linkedln) {
        this.linkedln = linkedln;
    }

}
