package com.restcode.restcode.service;

import com.restcode.restcode.domain.model.Comment;
import com.restcode.restcode.domain.repository.ICommentRepository;
import com.restcode.restcode.domain.repository.IOwnerRepository;
import com.restcode.restcode.domain.service.ICommentService;
import com.restcode.restcode.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public class CommentService implements ICommentService {

    @Autowired
    private ICommentRepository commentRepository;

    @Autowired
    private IOwnerRepository ownerRepository;

    @Override
    public Page<Comment> getAllCommentsByOwnerId(Long ownerId, Pageable pageable) {
        return commentRepository.findByOwnerId(ownerId, pageable);
    }
    @Override
    public Comment getCommentByIdAndOwnerId(Long ownerId, Long commentId) {
        return commentRepository.findByIdAndOwnerId(commentId, ownerId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Comment not found with Id " + commentId +
                                " and OwnerId " + ownerId));
    }

    @Override
    public Comment createComment(Long ownerId, Comment comment) {
        return ownerRepository.findById(ownerId).map(owner -> {
            comment.setOwner(owner);
            return commentRepository.save(comment);
        }).orElseThrow(()-> new ResourceNotFoundException(
                "Owner", "Id", ownerId));
    }

    @Override
    public Comment updateComment(Long ownerId, Long commentId, Comment commentDetails) {
        if(!ownerRepository.existsById(ownerId))
            throw  new ResourceNotFoundException("Owner", "Id", ownerId);

        return commentRepository.findById(commentId).map(comment -> {
            comment.setCommentary(commentDetails.getCommentary());
            return commentRepository.save(comment);
        }).orElseThrow( () -> new ResourceNotFoundException("Comment", "Id", commentId));
    }

    @Override
    public ResponseEntity<?> deleteComment(Long ownerId, Long commentId) {
        return commentRepository.findByIdAndOwnerId(commentId, ownerId).map(comment -> {
            commentRepository.delete(comment);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(
                "Comment not found with id " + commentId + " and Owner Id " + ownerId));
    }
}
