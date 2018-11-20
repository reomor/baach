package com.reomor.dto;

import com.reomor.core.domain.Image;
import com.reomor.core.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {

    private Integer threadId;
    private String message;

    public Post toPost(Image image) {
        return new Post(null, this.message, LocalDateTime.now(), null);
    }
}
