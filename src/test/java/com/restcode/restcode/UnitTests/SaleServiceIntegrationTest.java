package com.restcode.restcode.UnitTests;

import com.restcode.restcode.domain.model.Sale;
import com.restcode.restcode.domain.repository.ISaleRepository;
import com.restcode.restcode.domain.service.ISaleService;
import com.restcode.restcode.service.SaleService;
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
public class SaleServiceIntegrationTest {

    @Autowired
    private ISaleService saleService;

    @MockBean
    private ISaleRepository saleRepository;

    @TestConfiguration
    static class SaleServiceTestConfiguration {
        @Bean
        public ISaleService saleService() {
            return new SaleService();
        }
    }
    /*
    @Test
    @DisplayName("When get Sale By Client Fullname with valid Client Fullname then returns Sale")
    public void whenGetSaleByIdWithValidIdThenReturnsSale() {
        // Arrange
        String client_fullname = "Lenin";
        Sale sale = new Sale();
        sale.setId((long) 2);
        sale.setClient_fullname(client_fullname);
        Mockito.when(saleRepository.findByClientFullname(client_fullname))
                .thenReturn(Optional.of(sale));
        // Act
        Sale foundSale = saleService.getSaleByClientFullname(client_fullname);

        // Assert
        assertThat(foundSale.getClient_fullname()).isEqualTo(client_fullname);
    }*/

}
