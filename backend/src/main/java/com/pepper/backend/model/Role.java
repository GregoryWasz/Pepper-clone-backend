package com.pepper.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
@Entity
@Table(name = "roles")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","user"})
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="roleId", updatable = false, nullable = false)
    private Long roleId;
    @NotBlank(message = "RoleName is mandatory")
    private String roleName;
    @OneToMany(mappedBy="role")
    private Set<User> user;
}