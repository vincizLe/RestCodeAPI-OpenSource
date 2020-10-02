package com.open.restcode.Domain.Service;

import com.open.restcode.Domain.Model.Comment;
import org.springframework.data.domain.Page;

import java.awt.print.Pageable;

public interface CommentService {
    Page<Comment> getAllCommentsByUserID(Integer id, Pageable pageable);
    Comment createComment(Integer id,Comment comment);
}
