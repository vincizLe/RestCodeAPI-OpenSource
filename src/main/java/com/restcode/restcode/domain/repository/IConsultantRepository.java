package com.restcode.restcode.domain.repository;

import com.restcode.restcode.domain.model.Consultant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.lang.constant.Constable;
import java.util.Optional;

public interface IConsultantRepository extends JpaRepository<Consultant,Long> {
    Page<Consultant> findAll(Pageable pageable);
}
