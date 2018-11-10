package com.reomor.core.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class PostThread {
    private String text;
    private LocalDateTime dateTime;
}
