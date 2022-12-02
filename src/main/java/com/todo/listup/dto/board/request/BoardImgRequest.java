package com.todo.listup.dto.board.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BoardImgRequest {
  private String uploadFileName;
  private String storedFileName;
  private Integer size;
  private String extension;
}
