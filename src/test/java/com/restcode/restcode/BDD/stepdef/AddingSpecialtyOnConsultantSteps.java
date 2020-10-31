package com.restcode.restcode.BDD.stepdef;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.restcode.restcode.domain.model.Product;
import com.restcode.restcode.domain.model.Specialty;
import com.restcode.restcode.domain.repository.IConsultantRepository;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;

public class AddingSpecialtyOnConsultantSteps {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private IConsultantRepository iConsultantRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private Specialty expectSpecialty;
    private Specialty actualSpecialty;

    @Before
    public void setup(){
        expectSpecialty = new Specialty();
        actualSpecialty = new Specialty();
        iConsultantRepository.deleteAll();
    }

    @Given("the consultant wants to add on specialty endpoint")
    public void theConsultantWantsToAddOnSpecialtyEndpoint(Specialty specialty) {
        expectSpecialty = specialty;
    }

    @When("consultant add a new specialty")
    public void consultantAddANewSpecialty() {
        actualSpecialty = expectSpecialty;
    }

    @Then("the specialty will be added successfully")
    public void theSpecialtyWillBeAddedSuccessfully()
    {
        validateSpecialty();
    }

    private void validateSpecialty(){
        Assertions.assertEquals(expectSpecialty.getName(),actualSpecialty.getName());
    }
}
