package com.reomor.impl.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "IMAGE")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImageEntity {

    @Id
    private Long id;

    private String directory;

    private String fileName;
}
