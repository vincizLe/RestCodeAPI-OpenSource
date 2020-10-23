package com.restcode.restcode.resource;

import javax.validation.constraints.NotNull;

public class SaveSaleResource {
    @NotNull
    private String clientFullname;

    public String getClientFullname() {
        return clientFullname;
    }

    public void setClientFullname(String clientFullname) {
        this.clientFullname = clientFullname;
    }
}
