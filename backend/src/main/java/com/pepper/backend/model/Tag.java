package com.pepper.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

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
}