package com.restcode.restcode.domain.service;

import com.restcode.restcode.domain.model.Sale;
import com.restcode.restcode.domain.model.SaleDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface ISaleService {
    Page<Sale> getAllSalesByRestaurantId(Long restaurantId, Pageable pageable);
    Sale getSaleById(Long saleId);
    Sale createSale(Long restaurantId, Sale sale);
    ResponseEntity<?> deleteSale(Long saleId);


    //Its belong to the test
    //FSale getSaleByClientFullname(String clientFullname);
}
