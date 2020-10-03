package com.open.restcode.Resource;

import com.sun.istack.NotNull;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class SaveUserResource {

    @NotNull
    @NotBlank
    @Size(max = 30)
    private String userName;

    @NotNull
    @NotBlank
    @Size(max = 30)
    private String email;

    @NotNull
    @NotBlank
    @Size(max = 24)
    private String password;

    @NotNull
    @NotBlank
    @Size(max = 30)
    private String name;

    @NotNull
    @NotBlank
    @Size(max = 30)
    private String lastName;

    @NotNull
    @NotBlank
    @Size(max = 12)
    private String phone;

    @NotNull
    @NotBlank
    @Size(max = 50)
    private String linkedin;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLinkedin() {
        return linkedin;
    }

    public void setLinkedin(String linkedin) {
        this.linkedin = linkedin;
    }
}
