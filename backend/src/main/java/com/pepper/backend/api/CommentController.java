package com.pepper.backend.api;

import com.pepper.backend.model.Comment;
import com.pepper.backend.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/comments")
    public ResponseEntity<?> getAllComments() {
        return ResponseEntity.ok(commentService.getAllComments());
    }

    @PostMapping("/comments")
    public ResponseEntity<?> addComment(@RequestBody Comment comment) {
        return ResponseEntity.ok(commentService.addComment(comment));
    }


}
