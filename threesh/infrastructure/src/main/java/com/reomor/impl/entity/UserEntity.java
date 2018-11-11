package com.reomor.impl.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
public class UserEntity {
    @Id
    private Long id;
    private String userName;
}
