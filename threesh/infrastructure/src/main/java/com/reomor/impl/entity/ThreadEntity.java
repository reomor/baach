package com.reomor.impl.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "THREAD")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ThreadEntity {
    @Id
    @Column(name = "thread_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "channel_id")
    private ChannelEntity channel;

    @Column(name = "message")
    private String message;

    @Column(name = "priority")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "thread_priority_seq")
    private Long priority;

    @Column(name = "date_time")
    private LocalDateTime dateTime;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "image_id")
    private ImageEntity image;

    @OneToMany(mappedBy = "thread", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @OrderBy("id DESC")
    private List<PostEntity> posts;

    public ThreadEntity(Long id, String message, LocalDateTime dateTime, ImageEntity image, List<PostEntity> posts) {
        this.id = id;
        this.message = message;
        this.dateTime = dateTime;
        this.image = image;
        this.posts = posts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ThreadEntity that = (ThreadEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
