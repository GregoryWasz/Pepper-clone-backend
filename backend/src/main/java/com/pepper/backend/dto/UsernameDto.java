package com.pepper.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UsernameDto {
    private final Long userId;
    private final String username;
}
