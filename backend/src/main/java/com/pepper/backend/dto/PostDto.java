package com.pepper.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class PostDto {
    private final long postId;
    private final String title;
    private final String content;
    private final LocalDateTime postDate;
    private final double priceBefore;
    private final double priceAfter;
    private final double votes;
    private final String dealLink;
    private final long tagId;
    private final String Username;
}
