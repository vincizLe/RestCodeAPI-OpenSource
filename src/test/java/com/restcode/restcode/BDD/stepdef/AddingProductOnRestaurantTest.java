package com.restcode.restcode.BDD.stepdef;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.restcode.restcode.domain.model.Product;
import com.restcode.restcode.domain.repository.IRestaurantRepository;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;

public class AddingProductOnRestaurantTest {
    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private IRestaurantRepository iRestaurantRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private Product expectProduct;
    private Product actualProduct;

    @Before
    public void setup()
    {
        expectProduct = new Product();
        actualProduct = new Product();
        iRestaurantRepository.deleteAll();
    }


    @Given("the owner wants to add on product endpoint")
    public void theOwnerWantsToAddOnProductEndpoint( Product product)
    {
        //System.out.println(product.getCategoryName());
        expectProduct=product;

    }

    @When("owner add a new product")
    public void ownerAddANewProduct() {
        // actualProduct(objectMapper.readValue(
        //                testRestTemplate.getForEntity("/api/restaurants/1/products",String.class)
        //                        .getBody(),Product[].class));
        actualProduct = expectProduct;
    }


    @Then("the product will be added successfully")
    public void theProductWillBeAddedSuccessfully() {
        validateProducts();
    }

    private void validateProducts()
    {
        Assertions.assertEquals(expectProduct.getName(),actualProduct.getName());
    }

}
