package com.pepper.backend.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="commentId")
    private long commentId;

    private String content;

    private Date commentDate;

    private long userId;

    private long postId;
}

// TODO Create Repository, Service, Controller
// TODO Create New Comment
// TODO GET by id (view Comment)
// TODO PUT change by id (change content)
// TODO DELETE by id (delete Comment)
// TODO Pagination
// TODO VALIDATION