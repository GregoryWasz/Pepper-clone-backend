package com.pepper.backend.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "tags")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="tagId")
    private long tagId;
    private String tagName;
    @OneToMany(mappedBy="tag")
    private Set<Post> posts;
}
