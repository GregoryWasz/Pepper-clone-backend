package com.pepper.backend.service;

import com.pepper.backend.exception.ResourceNotFoundException;
import com.pepper.backend.model.Tag;
import com.pepper.backend.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService {

    @Autowired
    private TagRepository tagRepository;

    public List findAllTags(){
        return tagRepository.findAll();
    }

    public Tag addTag(Tag tag){
        return tagRepository.save(tag);
    }

    public ResponseEntity<Tag> getTagById(Long id){
        Tag tag = tagRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not exist with id: " + id));
        return ResponseEntity.ok(tag);
    }
}
