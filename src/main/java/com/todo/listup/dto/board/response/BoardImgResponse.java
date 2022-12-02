package com.todo.listup.dto.board.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.core.io.UrlResource;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardImgResponse {
  private String contents;
  private UrlResource urlResources;
}
