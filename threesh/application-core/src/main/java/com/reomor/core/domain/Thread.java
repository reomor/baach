package com.reomor.core.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class Thread {
    private String text;
    private LocalDateTime dateTime;
    private List<Post> posts;
}
