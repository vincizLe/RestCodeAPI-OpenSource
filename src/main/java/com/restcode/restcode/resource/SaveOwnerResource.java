package com.restcode.restcode.resource;

import javax.validation.constraints.NotNull;

public class SaveOwnerResource {

    @NotNull
    private Long ruc;

    public Long getRuc() {
        return ruc;
    }

    public void setRuc(Long ruc) {
        this.ruc = ruc;
    }
}
