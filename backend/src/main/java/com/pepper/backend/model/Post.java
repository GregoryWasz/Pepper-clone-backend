package com.pepper.backend.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
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

    @PositiveOrZero
    private double priceBefore;

    @PositiveOrZero
    private double priceAfter;

    private int votes = 0;

    private boolean active = true;

    @NotBlank
    private String dealLink;

    @Positive
    private long tagId;

    private long userId;
}