package com.restcode.restcode.resource;

import javax.persistence.Lob;
import javax.validation.constraints.NotNull;

public class SaveCommentResource {
    @Lob
    @NotNull
    private String commentary;

    public String getCommentary() {
        return commentary;
    }

    public void setCommentary(String commentary) {
        this.commentary = commentary;
    }
}
