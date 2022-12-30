package com.todo.listup.signIn;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

import static java.time.LocalDateTime.now;

@Entity
@Table(name = "TB_SIGN")
@Getter
@DynamicUpdate
@DynamicInsert
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Sign {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  @Comment("유저 id")
  private String userId;

  @Column(nullable = false)
  @Comment("비밀 번호")
  private String password;

  @CreationTimestamp
  @Comment("최근 접근 날짜")
  private LocalDateTime signCreateDate;

  @UpdateTimestamp
  @Comment("최근 접근 날짜")
  private LocalDateTime signUpdateDate;

  public Sign(String userId, String password) {
    this.userId = userId;
    this.password = password;
  }

  public void updatePassword(String password) {
    this.password = password;
  }

  public void updateSignDate() {
    this.signUpdateDate = now();
  }

}
