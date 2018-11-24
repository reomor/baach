package com.reomor.core.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Calendar;
import java.util.Date;

@Data
@AllArgsConstructor
@EqualsAndHashCode
public class VerificationToken {

    private static final int EXPIRATION = 60 * 24;

    private Long id;
    private String token;
    private User user;
    private Date expiryDate;

    public VerificationToken(String token) {
        this.id = null;
        this.token = token;
        this.expiryDate = calculateExpiryDate(EXPIRATION);
    }

    public VerificationToken(String token, User user) {
        this.id = null;
        this.token = token;
        this.user = user;
        this.expiryDate = calculateExpiryDate(EXPIRATION);
    }

    public boolean isExpired() {
        Calendar calendar = Calendar.getInstance();
        return this.expiryDate.getTime() - calendar.getTime().getTime() <= 0;
    }

    private Date calculateExpiryDate(int expiryTimeInMinutes) {
        final Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(new Date().getTime());
        cal.add(Calendar.MINUTE, expiryTimeInMinutes);
        return new Date(cal.getTime().getTime());
    }
}
