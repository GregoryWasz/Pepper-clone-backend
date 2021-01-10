package com.pepper.backend.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="postId")
    private long postId;

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    private LocalDateTime postDate = LocalDateTime.now();

    @NotBlank
    private double priceBefore;

    @NotBlank
    private double priceAfter;

    private int votes = 0;

    private boolean active = true;

    @NotBlank
    private long tagId;

    @NotBlank
    private long userId;
}