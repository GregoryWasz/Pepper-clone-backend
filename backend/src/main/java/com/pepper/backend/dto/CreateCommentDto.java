package com.pepper.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateCommentDto {
    private final String content;
    private final long postId;
}
