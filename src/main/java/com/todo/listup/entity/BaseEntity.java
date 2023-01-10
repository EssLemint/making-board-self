package com.todo.listup.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.*;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@DynamicUpdate
@DynamicInsert
@NoArgsConstructor
public class BaseEntity {

  @CreationTimestamp
  @Column(name = "CREATED_DATE")
  protected LocalDateTime createDate;

  @UpdateTimestamp
  @Column(name = "MODIFIED_DATE")
  protected LocalDateTime modifiedDate;

  @Column(columnDefinition = "boolean default true")
  protected Boolean useFlag;

  @Column(columnDefinition = "boolean default false")
  protected Boolean delFlag;
}
