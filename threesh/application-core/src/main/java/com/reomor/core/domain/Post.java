package com.reomor.core.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class Post {
    private String text;
    private List<Image> images;
    private LocalDateTime dateTime;
}
