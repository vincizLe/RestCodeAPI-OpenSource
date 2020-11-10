
package com.restcode.restcode.UnitTests;


import com.restcode.restcode.domain.model.Consultant;
import com.restcode.restcode.domain.repository.IConsultantRepository;
import com.restcode.restcode.domain.service.IConsultantService;
import com.restcode.restcode.service.ConsultantService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

@ExtendWith(SpringExtension.class)
public class ConsultantServiceIntegrationsTest {
    @Autowired
    private IConsultantService consultantService;

    @MockBean
    private IConsultantRepository consultantRepository;


    @TestConfiguration
    static class ConsultantServiceImplTestConfiguration{
        @Bean
        public IConsultantService consultantService(){
            return new ConsultantService();
        }
    }

    @Test
    @DisplayName("When getConsultantById With Valid Id then Return Consultant")
    public void WhenGetConsultantByIdWithValidIdReturnConsultant(){


        //Arrange
        Consultant consultant= new Consultant();
        consultant.setId(10L);
        Mockito.when(consultantRepository.findById(10L)).thenReturn(Optional.of(consultant));

        //Act

        Consultant foundConsultant=consultantService.getConsultantById(10L);


        //Assert

        assertThat(foundConsultant.getId()).isEqualTo(10L);
    }

    /*
    @Test
    @DisplayName("When getConsultantById With Invalid Id Then Throws ResourceNotFoundException")
    public void WhenGetConsultantByIdWithInvalidIdThenThrowsResourceNotFoundException(){
        // Arrange
        Consultant consultant=new Consultant();
        consultant.setId(10L);
        String template="Resource %s not found for %s with value %s";
        Mockito.when(consultantRepository.findById(10L)).thenReturn(Optional.empty());
        String expectedMessage=String.format(template,"Consultant","Id",10L);

        // Act
        Throwable exception=catchThrowable(()->{
            Consultant foundConsultant=consultantService.getConsultantById(10L);
        });

        // Assert

        assertThat(exception).isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);

    }

     */

   /* @Test
    @DisplayName("When createdConsultant")
    public void WhenCreatedConsultant(){
        //Arrange
        Consultant consultant=new Consultant();
        consultant.setId(20L);
        consultant.setLinkedln("https://www.linkedin.com/prueba");
        consultant.setPhone(9824563145L);
        consultant.setPassword("Password");
        consultant.setEmail("prueba@gmail.com");
        consultant.setNames("FirstName");
        consultant.setSurnames("SecondName");

        Mockito.when(consultantRepository.save(consultant)).thenReturn(consultant);
        //Act
        Consultant foundConsultant=consultantService.createConsultant(planId,consultant);

        //Assert
        assertThat(foundConsultant.getClass()).isEqualTo(consultant.getClass());
    }*/
}