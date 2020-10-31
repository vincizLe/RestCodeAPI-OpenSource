package com.restcode.restcode.service;

import com.restcode.restcode.domain.model.Sale;
import com.restcode.restcode.domain.model.SaleDetail;
import com.restcode.restcode.domain.repository.IRestaurantRepository;
import com.restcode.restcode.domain.repository.ISaleRepository;
import com.restcode.restcode.domain.service.ISaleService;
import com.restcode.restcode.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class SaleService implements ISaleService {
    @Autowired
    private ISaleRepository saleRepository;

    @Autowired
    private IRestaurantRepository restaurantRepository;

    @Override
    public Sale getSaleById(Long saleId) {
        return saleRepository.findById(saleId).orElseThrow(
                ()-> new ResourceNotFoundException("Sale","Id",saleId)
        );
    }

    @Override
    public Sale createSale(Long restaurantId, Sale sale) {
        return restaurantRepository.findById(restaurantId).map(restaurant -> {
            sale.setRestaurant(restaurant);
            return saleRepository.save(sale);
        }).orElseThrow(() -> new ResourceNotFoundException(
                "Restaurant", "Id", restaurantId));
    }

    @Override
    public ResponseEntity<?> deleteSale(Long saleId) {
        Sale sale=saleRepository.findById(saleId).orElseThrow(
                ()-> new ResourceNotFoundException("Sale","Id",saleId)
        );
        saleRepository.delete(sale);
        return ResponseEntity.ok().build();
    }

    @Override
    public Page<Sale> getAllSalesByRestaurantId(Long restaurantId, Pageable pageable) {
        return saleRepository.findByRestaurantId(restaurantId, pageable);
    }

    //Its belong to the test
    /*@Override
    public Sale getSaleByClientFullname(String client_fullname) {
        return saleRepository.findByClientFullname(client_fullname)
                .orElseThrow(()->new ResourceNotFoundException("Sale","Client Fullname",client_fullname));
    }*/
}
