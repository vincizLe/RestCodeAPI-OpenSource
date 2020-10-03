package com.open.restcode.Resource;

import com.open.restcode.Domain.Model.AuditModel;

public class BanckAcountResource extends AuditModel {

    private int id;
    private String name;
    private long number;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }
}
