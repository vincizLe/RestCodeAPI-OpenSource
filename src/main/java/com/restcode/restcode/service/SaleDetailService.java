package com.restcode.restcode.service;

import com.restcode.restcode.domain.model.Product;
import com.restcode.restcode.domain.model.Sale;
import com.restcode.restcode.domain.model.SaleDetail;
import com.restcode.restcode.domain.repository.IProductRepository;
import com.restcode.restcode.domain.repository.ISaleDetailRepository;
import com.restcode.restcode.domain.repository.ISaleRepository;
import com.restcode.restcode.domain.service.ISaleDetailService;
import com.restcode.restcode.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class SaleDetailService implements ISaleDetailService {
    @Autowired
    private ISaleDetailRepository saleDetailRepository;

    @Autowired
    private ISaleRepository saleRepository;

    @Autowired
    private IProductRepository productRepository;

    @Override
    public Page<SaleDetail> getAllSaleDetailsBySaleId(Long saleId, Pageable pageable) {
        return saleDetailRepository.findBySaleId(saleId, pageable);
    }

    @Override
    public SaleDetail getSaleDetailByIdAndSaleId(Long saleId,Long saleDetailId) {
        return saleDetailRepository.findById(saleDetailId).orElseThrow(
                ()-> new ResourceNotFoundException("Sale","Id",saleDetailId)
        );
    }

    @Override
    public SaleDetail createSaleDetail(Long saleId, Long productId, SaleDetail saleDetail) {
        Product product = productRepository.findById(productId).orElseThrow(
                ()-> new ResourceNotFoundException("Product","Id",productId)
        );
        return saleRepository.findById(saleId).map(sale -> {
            saleDetail.setSale(sale);
            saleDetail.setProduct(product);
            return saleDetailRepository.save(saleDetail);
        }).orElseThrow(() -> new ResourceNotFoundException(
                "Sale", "Id", saleId));
    }

    @Override
    public ResponseEntity<?> deleteSaleDetail(Long saleId,Long saleDetailId) {
        return saleDetailRepository.findById(saleDetailId).map(saleDetail-> {
            saleDetailRepository.delete(saleDetail);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(
                "Sale Detail not found with Id " + saleDetailId));
    }
}
