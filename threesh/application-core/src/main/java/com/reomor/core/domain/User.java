package com.reomor.core.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
    private String displayName;
    private String email;
    private String passwordHash;
    private String passwordSalt;
}
