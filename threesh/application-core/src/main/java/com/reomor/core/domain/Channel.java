package com.reomor.core.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Channel {
    private Long id;
    private String name;
    private String description;
    private String rules;
    private List<Thread> threads;
}
