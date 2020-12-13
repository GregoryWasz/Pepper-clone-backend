package com.pepper.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CreatePostDto {
    private final String title;
    private final String content;
    private final double priceBefore;
    private final double priceAfter;
    private final long tagId;
    private final long userId;
}
