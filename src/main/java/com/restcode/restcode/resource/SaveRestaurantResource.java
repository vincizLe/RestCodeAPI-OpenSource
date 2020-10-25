package com.restcode.restcode.resource;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SaveRestaurantResource {

    @NotNull
    @NotBlank
    @Size(max=100)
    private String restaurantName;

    @NotNull
    @NotBlank
    @Size(max=200)
    private String address;

    @NotNull
    private String phoneNumber;


    public String getRestaurantName() { return restaurantName; }

    public void setRestaurantName(String restaurantName) { this.restaurantName = restaurantName; }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) { this.address = address; }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
}
