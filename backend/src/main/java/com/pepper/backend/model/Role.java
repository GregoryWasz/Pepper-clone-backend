package com.pepper.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "roles")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","user"})
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="roleId")
    private int roleId;
    private String roleName;
    @OneToMany(mappedBy="role")
    private Set<User> user;
}
