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
    private long post_id;
    private String content;
    private Date post_date;
    private double price_before;
    private double price_after;
    private int votes;
    private boolean active;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "tag_id", referencedColumnName = "tag_id")
    private Tag tag;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;

    @OneToMany(mappedBy="post")
    private Set<Comment> PostComments;
}
