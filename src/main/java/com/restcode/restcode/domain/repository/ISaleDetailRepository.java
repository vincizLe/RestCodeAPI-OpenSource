package com.restcode.restcode.domain.repository;

import com.restcode.restcode.domain.model.SaleDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISaleDetailRepository extends JpaRepository <SaleDetail,Long> {
    Page<SaleDetail> findBySaleId(Long saleId, Pageable pageable);
}
