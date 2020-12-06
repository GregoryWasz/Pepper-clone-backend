package com.pepper.backend.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Data
@Entity
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="postId")
    private long postId;
    private String content;
    private Date postDate;
    private double priceBefore;
    private double priceAfter;
    private int votes;
    private boolean active;
    @ManyToOne
    @JoinColumn(name="tagId", nullable=false)
    private Tag tag;

    @ManyToOne
    @JoinColumn(name="userId", nullable=false)
    private User user;

    @OneToMany(mappedBy="post")
    private Set<Comment> PostComments;
}
