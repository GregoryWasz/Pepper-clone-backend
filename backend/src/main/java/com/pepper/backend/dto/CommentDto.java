package com.pepper.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class CommentDto {
    private final Long commentId;
    private final String content;
    private final LocalDateTime postDate;
    private final String Username;
}
