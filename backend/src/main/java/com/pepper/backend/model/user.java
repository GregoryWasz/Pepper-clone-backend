package com.pepper.backend.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class user {
    @Id
    @GeneratedValue
    private int user_id;
    private String user_name;
    private String password;
    private String email;
}

