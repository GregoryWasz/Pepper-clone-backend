package com.pepper.backend.service;

import com.pepper.backend.dto.CreatePostDto;
import com.pepper.backend.dto.UpdatePostDto;
import com.pepper.backend.exception.ResourceNotFoundException;
import com.pepper.backend.model.Post;
import com.pepper.backend.model.User;
import com.pepper.backend.repository.PostRepository;
import com.pepper.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    private final UserRepository userRepository;

    public List<Post> getAllPosts(){
        return postRepository.findAll();
    }

    public Post addPost(CreatePostDto CreatePostDto){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String loggedInUserName = authentication.getName();
        User user = userRepository.findByUsername(loggedInUserName)
                .orElseThrow(() ->new ResourceNotFoundException("User not found"));

        Post post = new Post();
        post.setUserId(user.getUserId());
        post.setTitle(CreatePostDto.getTitle());
        post.setContent(CreatePostDto.getContent());
        post.setPriceBefore(CreatePostDto.getPriceBefore());
        post.setPriceAfter(CreatePostDto.getPriceAfter());
        post.setTagId(CreatePostDto.getTagId());
        return postRepository.save(post);
    }

    public Post getPostById(long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post with id " + id + " not exist"));
    }

    public void deletePost(long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post with id " + id + " not exist"));
        postRepository.delete(post);
    }

    public Post updatePost(long id, UpdatePostDto userPost) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post with id " + id + " not exist"));
        post.setTitle(userPost.getTitle());
        post.setContent(userPost.getContent());
        post.setPriceBefore(userPost.getPriceBefore());
        post.setPriceAfter(userPost.getPriceAfter());
        post.setActive(userPost.getActive());
        return postRepository.save(post);
    }

    public List<Post> getPostByTitle(String title) {
        return postRepository.findByTitleContains(title)
                .orElseThrow(() -> new ResourceNotFoundException("Post with title " + title + " not exist"));
    }
}
