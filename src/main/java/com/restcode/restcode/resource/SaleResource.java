package com.restcode.restcode.resource;

import java.util.Date;

public class SaleResource {
    private Long id;
    private String clientFullname;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClientFullname() {
        return clientFullname;
    }

    public void setClientFullname(String clientFullname) {
        this.clientFullname = clientFullname;
    }
}
