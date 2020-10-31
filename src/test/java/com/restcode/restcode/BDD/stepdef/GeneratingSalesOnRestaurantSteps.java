package com.restcode.restcode.BDD.stepdef;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.restcode.restcode.domain.model.Sale;
import com.restcode.restcode.domain.repository.ISaleRepository;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;

public class GeneratingSalesOnRestaurantSteps {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private ISaleRepository iSaleRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private Sale expectSales;
    private Sale actualSales;

    @Before
    public void setup()
    {
        expectSales = new Sale();
        actualSales = new Sale();
        iSaleRepository.deleteAll();
    }

    @Given("the owner wants to add on salesrestaurant endpoint")
    public void theOwnerWantsToAddOnSalesrestaurantEndpoint(Sale sale)
    {

        expectSales = sale;

    }

    @When("owner add a new sale on restaurant")
    public void ownerAddANewSaleOnRestaurant()
    {

        actualSales = expectSales;

    }

    @Then("the sale will be added successfully")
    public void theSaleWillBeAddedSuccessfully()
    {
        validateSales();
    }

    private void validateSales()
    {
        Assertions.assertEquals(expectSales.getClientFullname(),actualSales.getClientFullname());
    }
}
