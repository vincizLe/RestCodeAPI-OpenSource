package com.restcode.restcode.domain.service;

import com.restcode.restcode.domain.model.SaleDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface ISaleDetailService {
    Page<SaleDetail> getAllSaleDetailsBySaleId(Long saleId,Pageable pageable);
    SaleDetail getSaleDetailByIdAndSaleId(Long saleId,Long saleDetailId);
    SaleDetail createSaleDetail(Long saleId, SaleDetail saleDetail);
    ResponseEntity<?> deleteSaleDetail(Long saleId,Long saleDetailId);
}
