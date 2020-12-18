package com.pepper.backend.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="commentId")
    private long commentId;

    private String content;

    private LocalDateTime postDate = LocalDateTime.now();

    private long userId;

    private long postId;
}
