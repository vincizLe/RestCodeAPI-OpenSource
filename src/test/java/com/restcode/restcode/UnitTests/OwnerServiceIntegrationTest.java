package com.restcode.restcode.UnitTests;

import com.restcode.restcode.domain.model.Owner;
import com.restcode.restcode.domain.repository.IOwnerRepository;
import com.restcode.restcode.domain.repository.IPlanRepository;
import com.restcode.restcode.domain.service.IOwnerService;
import com.restcode.restcode.domain.service.IPlanService;
import com.restcode.restcode.exception.ResourceNotFoundException;
import com.restcode.restcode.service.OwnerService;
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
import static org.assertj.core.api.Assertions.catchThrowable;

@ExtendWith(SpringExtension.class)
public class OwnerServiceIntegrationTest {

    @Autowired
    private IOwnerService ownerService;

    @MockBean
    private IOwnerRepository ownerRepository;

    @MockBean
    private IPlanRepository planRepository;

    @TestConfiguration
    static class OwnerServiceTestConfiguration {
        @Bean
        public IOwnerService ownerService() {
            return new OwnerService();
        }
    }

    @Test
    @DisplayName("When getOwnerByNames With Valid Names Then Returns Owner")
    public void whenGetOwnerByNameWithValidNamesThenReturnsOwner() {
        // Arrange
        String names = "Ale";
        Owner owner = new Owner();
        owner.setId(1L);
        owner.setNames(names);
        Mockito.when(ownerRepository.findByNames(names))
                .thenReturn(Optional.of(owner));
        // Act
        Owner foundOwner = ownerService.getOwnerByNames(names);

        // Assert
        assertThat(foundOwner.getNames().equals(names));
    }

    @Test
    @DisplayName("When getOwnerByNames With Invalid Names Then Throws ResourceNotFoundException")
    public void whenGetOwnerByNamesWithInvalidNamesThenThrowsResourceNotFoundException() {
        // Arrange
        String names = "Ale";
        String template = "Resource %s not found for %s with value %s";
        Mockito.when(ownerRepository.findByNames(names))
                .thenReturn(Optional.empty());
        String expectedMessage = String.format(template, "Owner", "Names", names);

        // Act
        Throwable exception = catchThrowable(() -> {
            Owner foundOwner = ownerService.getOwnerByNames(names);
        });

        // Assert
        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);
    }

}
