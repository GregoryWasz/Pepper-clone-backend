package com.pepper.backend.service;

import com.pepper.backend.model.Comment;
import com.pepper.backend.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    public Comment addComment(Comment comment) {
        return commentRepository.save(comment);
    }
}
