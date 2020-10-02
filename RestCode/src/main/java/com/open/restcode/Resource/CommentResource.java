package com.open.restcode.Resource;

import com.open.restcode.Domain.Model.AuditModel;

public class CommentResource extends AuditModel {

    private int id;
    private String text;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
