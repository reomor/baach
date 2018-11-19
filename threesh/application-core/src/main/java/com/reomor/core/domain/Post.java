package com.reomor.core.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    private Long id;
    private String message;
    private LocalDateTime dateTime;
    private Image image;
}
