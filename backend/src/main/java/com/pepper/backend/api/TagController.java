package com.pepper.backend.api;

import com.pepper.backend.model.Tag;
import com.pepper.backend.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/")
public class TagController {

    @Autowired
    private TagService tagService;

    @GetMapping("/tags")
    public List<Tag> getAllTags(){
        return tagService.findAllTags();
    }

    @PostMapping("/tags")
    public Tag addTag(@Valid @RequestBody Tag tag){
        return tagService.addTag(tag);
    }

    @GetMapping("/tags/{id}")
    public ResponseEntity<Tag> getTagById(@PathVariable Long id){
        return tagService.getTagById(id);
    }

    @GetMapping("/tags/name/{tagName}")
    public ResponseEntity<Tag> getTagByTagName(@PathVariable String tagName){
        return tagService.getTagByTagName(tagName);
    }

    @PutMapping("/tags/{id}")
    public ResponseEntity<Tag> updateTag(@PathVariable Long id, @RequestBody Tag updatedTag){
        return tagService.updateTag(id, updatedTag);
    }

    @DeleteMapping("/tags/{id}")
    public ResponseEntity<Map<String,Boolean>> deleteTag(@PathVariable Long id){
        return tagService.deleteTag(id);
    }
}
