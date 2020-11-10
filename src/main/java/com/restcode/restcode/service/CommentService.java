package com.restcode.restcode.service;

import com.restcode.restcode.domain.model.Comment;
import com.restcode.restcode.domain.model.Owner;
import com.restcode.restcode.domain.model.Product;
import com.restcode.restcode.domain.repository.*;
import com.restcode.restcode.domain.service.ICommentService;
import com.restcode.restcode.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CommentService implements ICommentService {
    @Autowired
    private ICommentRepository commentRepository;

    @Autowired
    private IConsultantRepository consultantRepository;

    @Autowired
    private IOwnerRepository ownerRepository;

    @Override
    public Page<Comment> getAllCommentsByConsultantId(Long consultantId, Pageable pageable) {
        return commentRepository.findByConsultantId(consultantId,pageable);
    }

    @Override
    public Page<Comment> getAllCommentsByOwnerId(Long ownerId, Pageable pageable) {
        return commentRepository.findByOwnerId(ownerId,pageable);
    }

    @Override
    public Comment createComment(Long consultantId, Long ownerId, Comment comment) {
        Owner owner = ownerRepository.findById(ownerId).orElseThrow(
                ()-> new ResourceNotFoundException("Owner","Id",ownerId)
        );
        return consultantRepository.findById(consultantId).map(consultant -> {
            comment.setConsultant(consultant);
            comment.setOwner(owner);
            return commentRepository.save(comment);
        }).orElseThrow(() -> new ResourceNotFoundException(
                "Consultant", "Id", consultantId));
    }

    @Override
    public ResponseEntity<?> deleteComment(Long ownerId,Long commentId) {
        return commentRepository.findById(commentId).map(comment-> {
            commentRepository.delete(comment);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(
                "Comment not found with Id " + commentId));
    }
}
