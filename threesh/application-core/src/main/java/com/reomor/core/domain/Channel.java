package com.reomor.core.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Channel {
    private Long id;
    private String name;
    private List<Thread> threads;
}
