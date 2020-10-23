package com.restcode.restcode.domain.repository;

import com.restcode.restcode.domain.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ISaleRepository extends JpaRepository<Sale,Long> {
    //Its belong to the test
    //public Optional<Sale> findByClientFullname(String clientFullname);
}
