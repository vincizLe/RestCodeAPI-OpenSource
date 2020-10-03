package com.open.restcode.Resource;

import com.sun.istack.NotNull;

import javax.persistence.Lob;

public class SaveCommentResource {

    @NotNull
    @Lob
    String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
