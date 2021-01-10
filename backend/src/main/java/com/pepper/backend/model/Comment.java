package com.pepper.backend.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="commentId")
    private long commentId;

    @NotBlank
    private String content;

    private LocalDateTime postDate = LocalDateTime.now();

    @NotNull
    private long userId;

    @NotNull
    private long postId;
}
