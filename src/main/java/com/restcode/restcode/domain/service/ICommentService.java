package com.restcode.restcode.domain.service;

import com.restcode.restcode.domain.model.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface ICommentService {
    Page<Comment> getAllCommentsByConsultantId(Long consultantId, Pageable pageable);
    Page<Comment> getAllCommentsByOwnerId(Long ownerId, Pageable pageable);
    Comment createComment(Long consultantId, Long ownerId, Comment comment);
    ResponseEntity<?> deleteComment(Long ownerId,Long commentId);
}
