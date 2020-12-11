package com.pepper.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UsernameAndEmailDto {
    private String username;
    private String email;
}
