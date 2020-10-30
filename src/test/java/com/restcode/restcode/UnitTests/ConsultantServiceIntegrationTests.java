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

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
public class ConsultantServiceIntegrationTests {
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
    @DisplayName("When getConsultantById wWith Valid Id then Return Consultant")
    public void WhenGetConsultantByIdWithValidIdReturnConsultant(){

            Consultant consultant= new Consultant();
            consultant.setId(10L);
        Mockito.when(consultantRepository.findById(10L)).thenReturn(Optional.of(consultant));


        Consultant foundConsultant=consultantService.getConsultantById(10L);


        assertThat(foundConsultant.getId()).isEqualTo(10L);
    }




}
