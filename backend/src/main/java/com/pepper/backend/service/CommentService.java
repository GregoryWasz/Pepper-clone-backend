package com.pepper.backend.service;

import com.pepper.backend.dto.CommentDto;
import com.pepper.backend.dto.MessageDto;
import com.pepper.backend.dto.UpdateCommentDto;
import com.pepper.backend.dto.CreateCommentDto;
import com.pepper.backend.exception.ResourceNotFoundException;
import com.pepper.backend.model.Comment;
import com.pepper.backend.model.User;
import com.pepper.backend.repository.CommentRepository;
import com.pepper.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
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

    public ResponseEntity<?> deleteComment(long id, Principal principal) {
        User user = userRepository.findByUsername(principal.getName())
                .orElseThrow(() -> new ResourceNotFoundException("Comment with id " + id + " not exist"));
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Comment with id " + id + " not exist"));

        if (user.getUserId() != comment.getUserId()){
            return ResponseEntity.ok(new MessageDto("You don't have permission to do it"));
        }

        commentRepository.delete(comment);
        return ResponseEntity.ok(new MessageDto("Comment successfully deleted"));
    }

    public Comment getCommentById(long id) {
        return commentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Comment with id " + id + " not exist"));
    }

    public ResponseEntity<?> updateCommentContent(long id, UpdateCommentDto updateCommentDto, Principal principal) {
        User user = userRepository.findByUsername(principal.getName())
                .orElseThrow(() -> new ResourceNotFoundException("Comment with id " + id + " not exist"));

        Comment databaseComment = commentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Comment with id " + id + " not exist"));

        if (user.getUserId() != databaseComment.getUserId()){
            return ResponseEntity.ok(new MessageDto("You don't have permission to do it"));
        }

        databaseComment.setContent(updateCommentDto.getContent());
        commentRepository.save(databaseComment);

        return ResponseEntity.ok(new MessageDto("Comment successfully updated"));
    }

    public List<CommentDto> getCommentsByPostId(Long postId){
        List<Comment> comments = commentRepository.findByPostId(postId);

        return comments.stream()
                .map(comment -> ( new CommentDto(
                        comment.getCommentId(),
                        comment.getContent(),
                        comment.getPostDate(),
                        userRepository.findByUserId(comment.getUserId()).getUsername()))
                )
                .collect(Collectors.toList());
    }
}
