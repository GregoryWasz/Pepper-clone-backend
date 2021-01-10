package com.pepper.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CurrentUserDto {
    private final long userId;
    private final String username;
}
