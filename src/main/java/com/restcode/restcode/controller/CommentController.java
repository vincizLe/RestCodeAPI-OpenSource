package com.restcode.restcode.controller;

import com.restcode.restcode.domain.model.Comment;
import com.restcode.restcode.domain.model.Consultant;
import com.restcode.restcode.domain.model.Product;
import com.restcode.restcode.resource.*;
import com.restcode.restcode.service.CommentService;
import com.restcode.restcode.service.ConsultantService;
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

@Tag(name="Comments", description ="Comment API")
@RestController
@RequestMapping("/api")
public class CommentController {

    @Autowired
    private ModelMapper mapper;
    @Autowired
    private CommentService commentService;

    private Comment convertToEntity(SaveCommentResource resource){
        return mapper.map(resource,Comment.class);
    }

    private CommentResource convertToResource(Comment entity){
        return mapper.map(entity,CommentResource.class);
    }

    @Operation(summary="Get All Comments By Consultant Id")
    @GetMapping("consultants/{consultantId}/comments")
    public Page<CommentResource> getAllCommentsByConsultantId(
            @PathVariable (value = "consultantId") Long consultantId, Pageable pageable){
        Page<Comment> commentPage = commentService.getAllCommentsByConsultantId(consultantId,pageable);
        List<CommentResource> resources = commentPage.getContent()
                .stream().map(this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(resources,pageable,resources.size());
    }

    @Operation(summary="Get All Comments By Owner Id")
    @GetMapping("owners/{ownerId}/comments")
    public Page<CommentResource> getAllCommentsByOwnerId(
            @PathVariable (value = "ownerId") Long ownerId, Pageable pageable){
        Page<Comment> commentPage = commentService.getAllCommentsByOwnerId(ownerId,pageable);
        List<CommentResource> resources = commentPage.getContent()
                .stream().map(this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(resources,pageable,resources.size());
    }


    @Operation(summary="Create Comment")
    @PostMapping("owners/{ownerId}/consultants/{consultantId}/comments")
    public CommentResource createComment(
            @PathVariable(value = "consultantId") Long consultantId,
            @PathVariable(value = "ownerId") Long ownerId,
            @Valid @RequestBody SaveCommentResource resource) {
        return convertToResource(commentService.createComment(consultantId,ownerId,convertToEntity(resource)));
    }

    @Operation(summary="Delete Comment")
    @DeleteMapping("/owners/{ownerId}/comments/{commentId}")
    public ResponseEntity<?> deleteSaleDetail(
            @PathVariable (value = "ownerId") Long ownerId,
            @PathVariable (value = "commentId") Long commentId) {
        return commentService.deleteComment(ownerId, commentId);
    }
}