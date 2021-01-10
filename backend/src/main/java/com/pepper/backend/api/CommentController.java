package com.pepper.backend.api;

import com.pepper.backend.dto.UpdateCommentDto;
import com.pepper.backend.dto.CreateCommentDto;
import com.pepper.backend.dto.MessageDto;
import com.pepper.backend.model.Comment;
import com.pepper.backend.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @GetMapping("")
    public ResponseEntity<?> getAllComments() {
        return ResponseEntity.ok(commentService.getAllComments());
    }

    @PostMapping("")
    @PreAuthorize("hasAnyRole('ROLE_USER')")
    public ResponseEntity<?> addComment(@Valid @RequestBody CreateCommentDto CreateCommentDto) {
        return ResponseEntity.ok(commentService.addComment(CreateCommentDto));
    }

    @GetMapping("/{postId}")
    @PreAuthorize("hasAnyRole('ROLE_USER')")
    public List<Comment> getCommentsByPostId(@PathVariable long postId) {
        return commentService.getCommentsByPostId(postId);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_USER')")
    public ResponseEntity<?> deleteComment(@PathVariable long id) {
        commentService.deleteComment(id);
        return ResponseEntity.ok(new MessageDto("Comment successfully deleted"));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_USER')")
    public Comment updateComment(@PathVariable long id, @RequestBody UpdateCommentDto updateCommentDto) {
        return commentService.updateCommentContent(id, updateCommentDto);
    }
}
