package com.restcode.restcode.service;

import com.restcode.restcode.domain.model.Sale;
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

    @Override
    public Page<Sale> getAllSales(Pageable pageable) {
        return saleRepository.findAll(pageable);
    }

    @Override
    public Sale getSaleById(Long saleId) {
        return saleRepository.findById(saleId).orElseThrow(
                ()-> new ResourceNotFoundException("Sale","Id",saleId)
        );
    }

    @Override
    public Sale createSale(Sale sale) {
        return saleRepository.save(sale);
    }

    @Override
    public ResponseEntity<?> deleteSale(Long saleId) {
        Sale sale=saleRepository.findById(saleId).orElseThrow(
                ()-> new ResourceNotFoundException("Sale","Id",saleId)
        );
        saleRepository.delete(sale);
        return ResponseEntity.ok().build();
    }

    //Its belong to the test
    /*@Override
    public Sale getSaleByClientFullname(String client_fullname) {
        return saleRepository.findByClientFullname(client_fullname)
                .orElseThrow(()->new ResourceNotFoundException("Sale","Client Fullname",client_fullname));
    }*/
}
