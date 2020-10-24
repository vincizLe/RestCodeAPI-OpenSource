package com.restcode.restcode.resource;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SavePlanResource {

    @NotNull
    @NotBlank
    @Size(max = 100)
    private String planName;

    @NotNull
    @NotBlank
    private Double price;

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
