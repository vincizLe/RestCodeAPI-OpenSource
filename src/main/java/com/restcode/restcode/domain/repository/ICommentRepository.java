package com.restcode.restcode.domain.repository;

import com.restcode.restcode.domain.model.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ICommentRepository extends JpaRepository<Comment, Long> {
    Page<Comment> findByOwnerId(Long ownerId, Pageable pageable);
    Optional<Comment> findByIdAndOwnerId(Long commentId, Long ownerId);
}
