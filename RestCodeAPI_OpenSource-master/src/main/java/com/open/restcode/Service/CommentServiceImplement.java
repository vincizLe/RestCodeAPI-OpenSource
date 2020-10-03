package com.open.restcode.Service;

import com.open.restcode.Domain.Model.Comment;
import com.open.restcode.Domain.Repository.CommentRepository;
import com.open.restcode.Domain.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import java.awt.print.Pageable;

public class CommentServiceImplement implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public Page<Comment> getAllCommentsByUserID(Integer id, Pageable pageable) {
        return null;
    }

    @Override
    public Comment createComment(Integer id, Comment comment) {
        return null;
    }
}
