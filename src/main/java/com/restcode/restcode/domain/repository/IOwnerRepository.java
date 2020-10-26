package com.restcode.restcode.domain.repository;

import com.restcode.restcode.domain.model.Owner;
import com.restcode.restcode.domain.model.Plan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IOwnerRepository extends JpaRepository<Owner, Long> {

}
