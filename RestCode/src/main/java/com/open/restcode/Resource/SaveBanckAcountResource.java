package com.open.restcode.Resource;

import com.sun.istack.NotNull;

public class SaveBanckAcountResource {

    @NotNull
    private String name;

    @NotNull

    private long number;

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
