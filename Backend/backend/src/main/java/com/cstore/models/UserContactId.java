package com.cstore.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class UserContactId implements Serializable {
    private static final long serialVersionUID = 4535620079918483570L;
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "telephone_number", nullable = false)
    private Integer telephoneNumber;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(Integer telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        UserContactId entity = (UserContactId) o;
        return Objects.equals(this.telephoneNumber, entity.telephoneNumber) &&
                Objects.equals(this.userId, entity.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(telephoneNumber, userId);
    }
}
