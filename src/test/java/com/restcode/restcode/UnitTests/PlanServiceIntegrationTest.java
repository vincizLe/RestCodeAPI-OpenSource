package com.restcode.restcode.UnitTests;

import com.restcode.restcode.domain.model.Owner;
import com.restcode.restcode.domain.model.Plan;
import com.restcode.restcode.domain.repository.IOwnerRepository;
import com.restcode.restcode.domain.repository.IPlanRepository;
import com.restcode.restcode.domain.service.IPlanService;
import com.restcode.restcode.service.PlanService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
public class PlanServiceIntegrationTest {

    @Autowired
    private IPlanService planService;

    @MockBean
    private IPlanRepository planRepository;

    @MockBean
    private IOwnerRepository ownerRepository;

    @TestConfiguration
    static class PlanServiceTestConfiguration {
        @Bean
        public IPlanService planService() {
            return new PlanService();
        }
    }

   /* @Test
    @DisplayName("When getPlanByOwnerID With Valid ID Then Returns Plan")
    public void whenGetOwnerByNameWithValidNamesThenReturnsOwner() {
        // Arrange
        Long ownerId = 1L;
        Plan plan = new Plan();
        Owner owner = new Owner();
        owner.setId(1L);
        plan.setId(1L);
        owner.setPlan(plan);
        Mockito.when(ownerRepository.findById(ownerId))
                .thenReturn(Optional.of(owner));
        // Act
        Plan foundPlan = planService.getPlanByOwnerId(ownerId);

        // Assert
        assertThat(foundPlan.getId().equals(owner.getPlan().getId()));
    }*/

}
