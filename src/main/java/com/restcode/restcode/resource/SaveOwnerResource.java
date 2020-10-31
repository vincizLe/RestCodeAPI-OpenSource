package com.restcode.restcode.resource;

import javax.validation.constraints.NotNull;

public class SaveOwnerResource {
    @NotNull
    private String names;

    @NotNull
    private String surnames;

    @NotNull
    private String email;

    @NotNull
    private String password;

    @NotNull
    private Long phone;

    @NotNull
    private Long ruc;

    public Long getRuc() {
        return ruc;
    }

    public void setRuc(Long ruc) {
        this.ruc = ruc;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getSurnames() {
        return surnames;
    }

    public void setSurnames(String surnames) {
        this.surnames = surnames;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }
}
