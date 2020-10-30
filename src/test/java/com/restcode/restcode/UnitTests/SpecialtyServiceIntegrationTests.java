package com.restcode.restcode.UnitTests;


import com.restcode.restcode.domain.model.Consultant;
import com.restcode.restcode.domain.model.Specialty;
import com.restcode.restcode.domain.repository.IConsultantRepository;
import com.restcode.restcode.domain.repository.ISpecialtyRepository;
import com.restcode.restcode.domain.service.IConsultantService;
import com.restcode.restcode.domain.service.ISpecialtyService;
import com.restcode.restcode.service.SpecialtyService;
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
public class SpecialtyServiceIntegrationTests {

    @Autowired
    private ISpecialtyService specialtyService;


    @MockBean
    private IConsultantRepository consultantRepository;

    @MockBean
    private ISpecialtyRepository specialtyRepository;


    @TestConfiguration
    static class SpecialtyServiceImplTestConfiguration{
        @Bean
        public ISpecialtyService specialtyService(){
            return new SpecialtyService();
        }
    }

    @Test
    @DisplayName("When getSpecialtyByIdAndConsultantId With Valid Id Then Returns Specialty")
    public void whenGetSpecialtyByIdWithValidIdTheReturnsSpecialty(){
        //Arrange

        Consultant consultant=new Consultant();
        consultant.setId(30L);
        Specialty specialty=new Specialty();

        specialty.setId(20L);
        specialty.setConsultant(consultant);
        Mockito.when(specialtyRepository.findByIdAndConsultantId(20L,30L)).thenReturn(Optional.of(specialty));


        //Act

        Specialty foundSpecialty=specialtyService.getSpecialtyByIdAndConsultantId(20L,30L);

        //Assert


        assertThat(foundSpecialty.getId()).isEqualTo(20L);
        assertThat(foundSpecialty.getConsultant().getId()).isEqualTo(31L);
    }


}
