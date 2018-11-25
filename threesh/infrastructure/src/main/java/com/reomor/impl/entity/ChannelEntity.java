package com.reomor.impl.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "CHANNEL")
@Data
@NoArgsConstructor
@AllArgsConstructor
@NamedEntityGraph(name = "channel.threads", attributeNodes = { @NamedAttributeNode("threads") })
public class ChannelEntity {

    @Id
    @Column(name = "channel_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "channel", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @OrderBy("priority DESC")
    private List<ThreadEntity> threads;
}
