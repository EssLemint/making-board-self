package com.todo.listup.signIn;

import com.todo.listup.entity.Role;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.*;

import jakarta.persistence.*;
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

  @Enumerated(EnumType.STRING)
  @Comment("권한")
  private Role role;

  @CreationTimestamp
  @Comment("최근 접근 날짜")
  private LocalDateTime signCreateDate;

  @UpdateTimestamp
  @Comment("최근 접근 날짜")
  private LocalDateTime signUpdateDate;

  public Sign(String userId, String password, Role role) {
    this.userId = userId;
    this.password = password;
    this.role = role;
  }

  public void updatePassword(String password) {
    this.password = password;
  }

  public void updateSignDate() {
    this.signUpdateDate = now();
  }

}
