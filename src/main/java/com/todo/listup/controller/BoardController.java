package com.todo.listup.controller;

import com.todo.listup.request.BoardPostRequest;
import com.todo.listup.request.BoardUpdateRequest;
import com.todo.listup.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class BoardController {

  private final BoardService boardService;

  @GetMapping("/get/board/{id}")
  public ResponseEntity<?> getBoardById(@PathVariable Long id) {
    return boardService.getBoardById(id);
  }

  @PostMapping("/create/board")
  public ResponseEntity<?> createBoard(@RequestBody BoardPostRequest request) {
    return boardService.createBoard(request);
  }

  @PutMapping("/update/board/{id}")
  public ResponseEntity<?> updateBoard(@PathVariable Long id,@RequestBody BoardUpdateRequest request) {
    return boardService.updateBoard(id, request);
  }

  @DeleteMapping("/delete/board/{id}")
  public ResponseEntity<?> deleteBoard(@PathVariable Long id) {
    return boardService.deleteBoard(id);
  }
}
