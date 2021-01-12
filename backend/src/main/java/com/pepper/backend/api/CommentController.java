package com.pepper.backend.api;

import com.pepper.backend.dto.CommentDto;
import com.pepper.backend.dto.UpdateCommentDto;
import com.pepper.backend.dto.CreateCommentDto;
import com.pepper.backend.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
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
    public List<CommentDto> getCommentsByPostId(@PathVariable long postId) {
        return commentService.getCommentsByPostId(postId);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_USER')")
    public ResponseEntity<?> deleteComment(@PathVariable long id, Principal principal) {
        return commentService.deleteComment(id, principal);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_USER')")
    public ResponseEntity<?> updateComment(@PathVariable long id, @RequestBody UpdateCommentDto updateCommentDto, Principal principal) {
        return commentService.updateCommentContent(id, updateCommentDto, principal);
    }
}
