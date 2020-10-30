package com.restcode.restcode.domain.service;

import com.restcode.restcode.domain.model.Owner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IOwnerService {
    Page<Owner> getAllOwners(Pageable pageable);
    Owner getOwnerById(Long ownerId);
    Owner createOwner(Owner owner);
    Owner updateOwner(Long ownerId, Owner ownerRequest);
    Owner assignOwnerPlan(Long ownerId, Long planId);
    Owner getOwnerByNames(String Names);

}
