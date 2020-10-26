package com.restcode.restcode.resource;

public class RestaurantResource {
    private Long id;
    private String name;
    private String address;
    private Long phoneNumber;

    public Long getId() { return id; }

    public RestaurantResource setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() { return name; }

    public RestaurantResource setName(String name) {
        this.name = name;
        return this;
    }

    public String getAddress() { return address; }

    public RestaurantResource setAddress(String address) {
        this.address = address;
        return this;
    }

    public Long getPhoneNumber() { return phoneNumber; }

    public RestaurantResource setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }
}
