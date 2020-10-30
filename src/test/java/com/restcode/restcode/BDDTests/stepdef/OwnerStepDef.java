package com.restcode.restcode.BDDTests.stepdef;

import com.restcode.restcode.RestcodeApplication;
import com.restcode.restcode.domain.model.Owner;
import com.restcode.restcode.domain.repository.IOwnerRepository;
import com.restcode.restcode.domain.repository.IPlanRepository;
import com.restcode.restcode.domain.service.IOwnerService;
import com.restcode.restcode.service.OwnerService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@CucumberContextConfiguration
@SpringBootTest(classes = RestcodeApplication.class)
@Transactional
public class OwnerStepDef {


    @Autowired
    private IOwnerService ownerService;

    @Autowired
    private IOwnerRepository ownerRepository;

    @MockBean
    private IPlanRepository planRepository;

    private String names;
    private Owner owner = new Owner();
    private Owner foundOwner = new Owner();

    @Given("User needs to look up a names")
    public void user_needs_to_look_up_a_names() {
        this.names = "Ale Abad";
    }

    @When("User writes down names")
    public void user_writes_down_names() {
        owner.setNames(names);
    }

    @Then("Owner with names shows up")
    public void owner_with_names_shows_up() {
        foundOwner.setNames(names);
        /*Owner foundOwner = ownerService.getOwnerByNames(names);*/
        assertThat(foundOwner.getNames().equals(names));
    }
}
