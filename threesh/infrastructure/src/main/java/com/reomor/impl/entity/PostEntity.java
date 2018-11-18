package com.reomor.impl.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostEntity {
    @Id
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "thread_id")
    private ThreadEntity thread;

    private String message;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "post_id")
    private ImageEntity image;

    private LocalDateTime dateTime;

    public PostEntity(Long id, String message, ImageEntity image, LocalDateTime dateTime) {
        this.id = id;
        this.message = message;
        this.image = image;
        this.dateTime = dateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PostEntity that = (PostEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
