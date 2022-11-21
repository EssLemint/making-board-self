package com.todo.listup.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TB_BOARD")
@Getter
@DynamicUpdate
@DynamicInsert
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Comment("게시글 번호")
  @Column(name = "BOARD_ID")
  private Long id;

  @Column(nullable = false)
  @Comment("게시글 제목")
  private String title;

  @Column(nullable = false, columnDefinition="TEXT")
  @Comment("게시들 내용")
  private String contents;

  @OneToMany(mappedBy = "board")
  private List<Images> uploadImages = new ArrayList<>();

  public Board(String title, String contents) {
    this.title = title;
    this.contents = contents;
  }

  public void updateBoard(String title, String contents) {
    this.title = title;
    this.contents = contents;
  }

  public void deleteBoard() {
    this.useFlag = false;
    this.delFlag = true;
  }

  public void createImages(Images images) {
    this.uploadImages.add(images);
    if (images.getBoard() != this) {
      images.setBoard(this);
    }
  }
}
