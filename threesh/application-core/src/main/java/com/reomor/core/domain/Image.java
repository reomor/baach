package com.reomor.core.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Image {
    private String directory;
    private String fileName;
}
