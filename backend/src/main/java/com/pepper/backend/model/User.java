package com.pepper.backend.model;

import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.Set;

@Data
@Entity
@Table(name = "users")
public class User implements UserDetails {
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

    @CreatedBy
    private String createdBy;
    private boolean accountNonExpired = true;
    private boolean accountNonLocked = true;
    private boolean credentialsNonExpired = true;
    private boolean enabled = true;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority(getRole().getRoleName()));
    }
}

// TODO Admin Create Admin
// TODO Admin Site -> Delete User, Ban User, Delete Post, Delete Comment,
// TODO VALIDATION
// TODO VOTESYSTEM
// TODO PICTRUES for users and posts,
// TODO USER PROFILE