package com.offer.entity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;

@Data
@MappedSuperclass
public class BaseEntity implements Serializable {
    @Column(name = "created_at")
    @Temporal(TemporalType.DATE)
    private Date createdDate;
}
