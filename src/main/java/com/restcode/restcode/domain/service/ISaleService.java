package com.restcode.restcode.domain.service;

import com.restcode.restcode.domain.model.Sale;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface ISaleService {
    Page<Sale> getAllSales(Pageable pageable);
    Sale getSaleById(Long saleId);
    Sale createSale(Sale sale);
    ResponseEntity<?> deleteSale(Long saleId);

    //Its belong to the test
    //FSale getSaleByClientFullname(String clientFullname);
}
