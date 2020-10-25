package com.restcode.restcode.resource;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SaveCategoryResource {
    @NotNull
    @NotBlank
    @Size(max=100)
    private String categoryName;


    public String getCategoryName() { return categoryName; }

    public void setCategoryName(String categoryName) { this.categoryName = categoryName; }
}
