package com.pepper.backend.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="postId")
    private long postId;

    private String title;

    private String content;

    private LocalDateTime postDate = LocalDateTime.now();

    private double priceBefore;

    private double priceAfter;

    private int votes = 0;

    private boolean active = true;

    private long tagId;

    private long userId;
}
// TODO VALIDATION