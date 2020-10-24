package com.restcode.restcode.domain.service;

import com.restcode.restcode.domain.model.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface ICommentService {
    Page<Comment> getAllCommentsByOwnerId(Long ownerId, Pageable pageable);
    Comment getCommentByIdAndOwnerId(Long ownerId, Long commentId);
    Comment createComment(Long ownerId, Comment comment);
    Comment updateComment(Long ownerId, Long commentId, Comment commentDetails);
    ResponseEntity<?> deleteComment(Long ownerId, Long commentId);

}
