package com.pepper.backend.api;

import com.pepper.backend.model.Tag;
import com.pepper.backend.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public Tag addTag(@RequestBody Tag tag){
        return tagService.addTag(tag);
    }

    @GetMapping("/tags/{id}")
    public ResponseEntity<Tag> getTagById(@PathVariable Long id){
        return tagService.getTagById(id);
    }
}
