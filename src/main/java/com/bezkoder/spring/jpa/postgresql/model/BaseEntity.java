package com.bezkoder.spring.jpa.postgresql.model;

import com.bezkoder.spring.jpa.postgresql.common.Constant;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Data
@SuperBuilder
@NoArgsConstructor
public abstract class BaseEntity {

    @Column(updatable = false)
    @CreatedBy
    private String createdBy;

    @Column(updatable = false)
    @JsonFormat(pattern=Constant.FORMAT_DATE.DATE_TIME_DEFAULT)
    @CreatedDate
    private LocalDate createdAt;

    @LastModifiedBy
    private String modifiedBy;

    @LastModifiedDate
    @JsonFormat(pattern=Constant.FORMAT_DATE.DATE_TIME_DEFAULT)
    private LocalDate modifiedAt;

    private Boolean isDeleted;
}