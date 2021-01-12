package com.pepper.backend.api;

import com.pepper.backend.dto.MessageDto;
import com.pepper.backend.model.Tag;
import com.pepper.backend.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/tags")
@RequiredArgsConstructor
public class TagController {

    private final TagService tagService;

    @GetMapping("")
    public List<Tag> getAllTags() {
        return tagService.findAllTags();
    }

    @PostMapping("")
    @PreAuthorize("hasAnyRole('ROLE_USER')")
    public Tag addTag(@Valid @RequestBody Tag tag) {
        return tagService.addTag(tag);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tag> getTagById(@PathVariable Long id) {
        return tagService.getTagById(id);
    }

    @GetMapping("/name/{tagName}")
    public ResponseEntity<Tag> getTagByTagName(@PathVariable String tagName) {
        return tagService.getTagByTagName(tagName);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public Tag updateTag(@PathVariable Long id, @RequestBody Tag updatedTag) {
        return tagService.updateTag(id, updatedTag);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<?> deleteTag(@PathVariable Long id) {
        tagService.deleteTag(id);
        return ResponseEntity.ok(new MessageDto("Tag successfully deleted"));
    }
}
