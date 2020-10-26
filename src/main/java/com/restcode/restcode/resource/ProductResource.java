package com.restcode.restcode.resource;

public class ProductResource {
    private Long id;
    private String productName;

    public Long getId() { return id; }

    public ProductResource setId(Long id) { this.id = id; return this;}

    public String getProductName() { return productName; }

    public ProductResource setProductName(String productName) { this.productName = productName; return this;}
}
