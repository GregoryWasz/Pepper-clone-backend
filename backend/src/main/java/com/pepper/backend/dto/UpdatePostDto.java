package com.pepper.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePostDto {
    private String title;
    private String content;
    private double priceBefore;
    private double priceAfter;
    private String dealLink;
    private Boolean active;
    private long tagId;
}
