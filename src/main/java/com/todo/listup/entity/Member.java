package com.todo.listup.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Table(name = "TB_MEMBER")
@Getter
@DynamicUpdate
@DynamicInsert
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Comment("멤버 번호")
  private Long id;

  @Column(nullable = false)
  @Comment("아이디")
  private String userId;

  @Column(nullable = false)
  @Comment("비밀 번호")
  private String userPassword;

  @Column(nullable = false)
  @Comment("이름")
  private String userName;

  @Column(nullable = false)
  @Comment("이메일")
  private String email;

  public Member(String userId, String userPassword, String userName, String email) {
    this.userId = userId;
    this.userPassword = userPassword;
    this.userName = userName;
    this.email = email;
  }

  public void updateMember(String userName, String email, String userPassword) {
    this.userPassword = userPassword;
    this.userName = userName;
    this.email = email;
  }

  public void deleteMember() {
    this.useFlag = false;
    this.delFlag = true;
  }
}
