package com.pepper.backend.service;

import com.pepper.backend.dto.CommentDto;
import com.pepper.backend.dto.CreateCommentDto;
import com.pepper.backend.exception.ResourceNotFoundException;
import com.pepper.backend.model.Comment;
import com.pepper.backend.model.User;
import com.pepper.backend.repository.CommentRepository;
import com.pepper.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    private final UserRepository userRepository;

    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    public Comment addComment(CreateCommentDto CreateCommentDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String loggedInUserName = authentication.getName();
        User user = userRepository.findByUsername(loggedInUserName)
                .orElseThrow(() ->new ResourceNotFoundException("User not found"));
        Comment comment = new Comment();
        comment.setContent(CreateCommentDto.getContent());
        comment.setPostId(CreateCommentDto.getPostId());
        comment.setUserId(user.getUserId());
        return commentRepository.save(comment);
    }

    public void deleteComment(long id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Comment with id " + id + " not exist"));
        commentRepository.delete(comment);
    }

    public Comment getCommentById(long id) {
        return commentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Comment with id " + id + " not exist"));
    }

    public Comment updateCommentContent(long id, CommentDto CommentDto) {
        Comment databaseComment = commentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Comment with id " + id + " not exist"));
        databaseComment.setContent(CommentDto.getContent());
        return commentRepository.save(databaseComment);
    }
}
