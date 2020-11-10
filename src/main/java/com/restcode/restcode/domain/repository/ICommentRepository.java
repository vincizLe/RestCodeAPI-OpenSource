package com.restcode.restcode.domain.repository;

import com.restcode.restcode.domain.model.Comment;
import com.restcode.restcode.domain.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICommentRepository extends JpaRepository<Comment, Long> {
    Page<Comment> findByConsultantId(Long consultantId, Pageable pageable);
    Page<Comment> findByOwnerId(Long ownerId, Pageable pageable);
}
