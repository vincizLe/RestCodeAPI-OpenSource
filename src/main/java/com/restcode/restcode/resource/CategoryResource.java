package com.restcode.restcode.resource;

import com.restcode.restcode.domain.model.Category;

public class CategoryResource {
    private Long id;
    private String categoryName;

    public Long getId() { return id; }

    public CategoryResource setId(Long id) { this.id = id; return this;}

    public String getCategoryName() { return categoryName; }

    public CategoryResource setCategoryName(String categoryName) { this.categoryName = categoryName; return this;}
}
