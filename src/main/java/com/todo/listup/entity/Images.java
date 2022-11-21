package com.todo.listup.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Table(name = "TB_IMAGES")
@Getter
@DynamicUpdate
@DynamicInsert
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Images extends BaseEntity{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "IMAGES_ID")
  private Long Id;

  @Column(nullable = false)
  @Comment("사용자 지정 파일 이름")
  private String uploadFileName;

  @Column(nullable = false)
  @Comment("저장된 파일 이름")
  private String storedFileName;

  @Column(nullable = false)
  @Comment("파일 저장 경로")
  private String fullPath;

  @Comment("파일 사이즈")
  private Long size;

  @Comment("확장자")
  private String extension;

  @ManyToOne
  @JoinColumn(name = "boardId")
  private Board board;

  public Images(String uploadFileName, String storedFileName, String fullPath,
                Long size, String extension) {
    this.uploadFileName = uploadFileName;
    this.storedFileName = storedFileName;
    this.fullPath = fullPath;
    this.size = size;
    this.extension = extension;
  }

  public void setBoard(Board board) {
    this.board = board;
    if (!board.getUploadImages().contains(this)) {
      board.getUploadImages().add(this);
    }
  }
}
