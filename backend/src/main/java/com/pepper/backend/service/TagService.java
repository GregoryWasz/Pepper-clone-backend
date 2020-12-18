package com.pepper.backend.service;

import com.pepper.backend.exception.AlreadyExistException;
import com.pepper.backend.exception.IdOverrideException;
import com.pepper.backend.exception.ResourceNotFoundException;
import com.pepper.backend.model.Tag;
import com.pepper.backend.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TagService {

    private final TagRepository tagRepository;

    public List<Tag> findAllTags(){
        return tagRepository.findAll();
    }

    private void TagNameValidation(Tag tag){
        boolean matchName = tagRepository.findAll().stream()
                .anyMatch(foundedTag -> foundedTag.getTagName().equals(tag.getTagName()));
        if(matchName){
            throw new AlreadyExistException("Tag with that name already exist");
        }
    }

    public Tag addTag(Tag tag){
        TagNameValidation(tag);
        if(tagRepository.existsById(tag.getTagId())){
            throw new IdOverrideException("Don't manipulate ID please");
        }
        return tagRepository.save(tag);
    }

    public ResponseEntity<Tag> getTagById(Long id){
        Tag tag = tagRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tag not exist with id: " + id));
        return ResponseEntity.ok(tag);
    }

    public ResponseEntity<Tag> getTagByTagName(String tagName){
        Tag tag = tagRepository.findTagByTagName(tagName)
                .orElseThrow(() -> new ResourceNotFoundException("Tag not exist with name: " + tagName));
        return ResponseEntity.ok(tag);
    }

    public Tag updateTag(Long id, Tag updatedTag){
        TagNameValidation(updatedTag);
        Tag tag = tagRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tag not exist with id: " + id));
        tag.setTagName(updatedTag.getTagName());
        tagRepository.save(tag);
        return tagRepository.save(tag);
    }

    public void deleteTag(Long id){
        Tag tag = tagRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tag not exist with id: " + id));
        tagRepository.delete(tag);
    }
}
