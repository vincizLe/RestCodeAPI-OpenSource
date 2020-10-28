package com.restcode.restcode.service;

import com.restcode.restcode.domain.model.Owner;
import com.restcode.restcode.domain.model.Plan;
import com.restcode.restcode.domain.repository.IOwnerRepository;
import com.restcode.restcode.domain.repository.IPlanRepository;
import com.restcode.restcode.domain.service.IOwnerService;
import com.restcode.restcode.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class OwnerService implements IOwnerService {

    @Autowired
    private IOwnerRepository ownerRepository;

    @Autowired
    private IPlanRepository planRepository;

    @Override
    public Page<Owner> getAllOwners(Pageable pageable) {
        return ownerRepository.findAll(pageable);
    }

    @Override
    public Owner getOwnerById(Long ownerId) {
        return ownerRepository.findById(ownerId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Post", "Id", ownerId));
    }

    @Override
    public Owner createOwner(Owner owner) {
        return ownerRepository.save(owner);
    }

    @Override
    public Owner updateOwner(Long ownerId, Owner ownerRequest) {
        return ownerRepository.findById(ownerId)
                .map(owner -> {
                    owner.setNames(ownerRequest.getNames());
                    owner.setSurnames(ownerRequest.getSurnames());
                    owner.setEmail(ownerRequest.getEmail());
                    owner.setPassword(ownerRequest.getPassword());
                    owner.setPhone(ownerRequest.getPhone());
                    owner.setRuc(ownerRequest.getRuc());
                    return ownerRepository.save(owner);
                })
                .orElseThrow(() -> new ResourceNotFoundException(
                        "OwnerId " + ownerId + " not found"
                ));
    }

    @Override
    public Owner assignOwnerPlan(Long ownerId, Long planId) {
        Plan plan = planRepository.findById(planId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Plan", "Id", planId));
        return ownerRepository.findById(ownerId).map(owner -> {
            return ownerRepository.save(owner.planIs(plan));
        }).orElseThrow(() -> new ResourceNotFoundException(
                "Post", "Id", ownerId));
    }


}
