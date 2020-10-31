package com.restcode.restcode.resource;

import javax.validation.constraints.NotNull;

public class SaveSaleDetailResource {
    @NotNull
    private int quantity;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
