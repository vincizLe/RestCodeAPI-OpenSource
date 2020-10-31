package com.restcode.restcode.BDD.stepdef;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.restcode.restcode.domain.model.Assignment;
import com.restcode.restcode.domain.model.Product;
import com.restcode.restcode.domain.repository.IRestaurantRepository;
import io.cucumber.java.Before;
import io.cucumber.java.bs.A;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;

public class GivingPermitsToConsultantOnRestaurant {
    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    private Assignment expectAssignment;
    private Assignment actualAssignment;

    @Before
    public void setup()
    {
        expectAssignment = new Assignment();
        actualAssignment = new Assignment();
    }


    @Given("the owner wants ti give on permission endpoint")
    public void theOwnerWantsTiGiveOnPermissionEndpoint(Assignment assignment) {

        expectAssignment = assignment;
    }

    @When("the owner give permission to consultant")
    public void theOwnerGivePermissionToConsultant()
    {
        actualAssignment = expectAssignment;
    }

    @Then("the permit will be completed")
    public void thePermitWillBeCompleted()
    {
        validateAssignment();
    }

    private void validateAssignment(){
        Assertions.assertEquals(expectAssignment.getDateAssignment(),actualAssignment.getDateAssignment());
    }
}
