package com.pepper.backend.api;

import com.pepper.backend.model.Post;
import com.pepper.backend.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping("/posts")
    public List<Post> getAllPosts(){
        return postService.getAllPosts();
    }

    @PostMapping("/posts")
    public Post addPost(@RequestBody Post post){
        return postService.addPost(post);
    }

    // TODO GET by id (view Post)
    // TODO PUT change by id (change title, content, pricebefore, priceafter, active)
    // TODO DELETE by id (delete Tag)
    // TODO Search Get by name (search tags by PostName)
    // TODO Get all user posts -> Post
    // TODO VALIDATION
    // TODO Pagination
}
