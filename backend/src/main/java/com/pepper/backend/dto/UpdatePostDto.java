package com.pepper.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpdatePostDto {
    private final String title;
    private final String content;
    private final double priceBefore;
    private final double priceAfter;
    private final Boolean active;
    private final long tagId;
}
