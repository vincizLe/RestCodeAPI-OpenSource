package com.restcode.restcode.resource;

import javax.validation.constraints.NotNull;

public class SaveSaleDetailResource {
    @NotNull
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
