package com.reomor.core.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class Post {
    private String text;
    private Image[] images;
    private LocalDateTime dateTime;
}
