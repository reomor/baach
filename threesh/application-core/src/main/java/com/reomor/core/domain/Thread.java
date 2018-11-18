package com.reomor.core.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class Thread {
    private Long id;
    private String message;
    private LocalDateTime dateTime;
    private Image image;
    private List<Post> posts;
}
