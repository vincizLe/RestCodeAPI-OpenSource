package com.restcode.restcode.service;

import com.restcode.restcode.domain.model.Owner;
import com.restcode.restcode.domain.repository.IOwnerRepository;
import com.restcode.restcode.domain.service.IOwnerService;
import com.restcode.restcode.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class OwnerService implements IOwnerService {

    @Autowired
    private IOwnerRepository ownerRepository;

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
        return null;
        /*Owner owner = ownerRepository.findById(ownerId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Owner", "Id", ownerId));
        return ownerRepository.save(
                owner.setNames(ownerRequest.getNames())
                .setSurnames(ownerRequest.getSurnames())
                .setEmail(ownerRequest.getEmail())
                .setPassword(ownerRequest.getPassword())
                .setPhone(ownerRequest.getPhone()));*/
    }
}
