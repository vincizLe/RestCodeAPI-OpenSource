package com.restcode.restcode.resource;

import javax.persistence.Lob;

public class SaveCommentResource {
    @Lob
    private String commentary;

    public String getCommentary() {
        return commentary;
    }

    public void setCommentary(String commentary) {
        this.commentary = commentary;
    }
}
