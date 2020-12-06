package com.pepper.backend.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "users")
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="userId")
    private long userId;
    private String username;
    private String password;
    private String email;
    @ManyToOne
    @JoinColumn(name="roleId", nullable=false)
    private Role role;

    @OneToMany(mappedBy="user")
    private Set<Post> posts;

    @OneToMany(mappedBy="user")
    private Set<Comment> UserComments;
}
