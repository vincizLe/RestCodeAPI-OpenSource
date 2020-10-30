package com.restcode.restcode.controller;

import com.restcode.restcode.domain.model.Comment;
import com.restcode.restcode.domain.service.ICommentService;
import com.restcode.restcode.resource.CommentResource;
import com.restcode.restcode.resource.SaveCommentResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Tag(name="comments", description ="Comments API")
@RestController
@RequestMapping("/api")
public class CommentController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private ICommentService commentService;

    private Comment convertToEntity(SaveCommentResource resource) {
        return mapper.map(resource, Comment.class);
    }
    private CommentResource convertToResource(Comment entity) {
        return mapper.map(entity, CommentResource.class);
    }

    @GetMapping("/owners/{ownerId}/comments")
    public Page<CommentResource> getAllCommentsByOwnerId(
            @PathVariable(value = "ownerId") Long ownerId,
            Pageable pageable) {
        Page<Comment> commentPage = commentService.getAllCommentsByOwnerId(ownerId, pageable);
        List<CommentResource> resources = commentPage.getContent().stream()
                .map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @GetMapping("/owners/{ownerId}/comments/{commentId}")
    public CommentResource getCommentByIdAndOwnerId(
            @PathVariable(name = "ownerId") Long ownerId,
            @PathVariable(name = "commentId") Long commentId) {
        return convertToResource(commentService.getCommentByIdAndOwnerId(ownerId, commentId));
    }

    @PostMapping("/owners/{ownerId}/comments")
    public CommentResource createComment(
            @PathVariable(value = "ownerId") Long ownerId,
            @Valid @RequestBody SaveCommentResource resource) {
        return convertToResource(commentService.createComment(ownerId,
                convertToEntity(resource)));
    }

    @PutMapping("/owners/{ownerId}/comments/{commentId}")
    public CommentResource updateComment(
            @PathVariable (value = "ownerId") Long ownerId,
            @PathVariable (value = "commentId") Long commentId,
            @Valid @RequestBody SaveCommentResource resource) {
        return convertToResource(commentService.updateComment(ownerId, commentId,
                convertToEntity(resource)));
    }

    @DeleteMapping("/owners/{ownerId}/comments/{commentId}")
    public ResponseEntity<?> deleteComment(
            @PathVariable (value = "ownerId") Long ownerId,
            @PathVariable (value = "commentId") Long commentId) {
        return commentService.deleteComment(ownerId, commentId);
    }

}
