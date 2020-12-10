package com.pepper.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
@Entity
@Table(name = "tags")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","posts"})
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="tagId", updatable = false, nullable = false)
    private long tagId;
    @NotBlank(message = "tagName is mandatory")
    @Column(unique = true)
    private String tagName;
    @OneToMany(mappedBy="tag")
    private Set<Post> posts;
}