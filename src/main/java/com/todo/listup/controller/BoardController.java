package com.todo.listup.controller;

import com.todo.listup.request.BoardPostRequest;
import com.todo.listup.request.BoardUpdateRequest;
import com.todo.listup.response.BoardGetResponse;
import com.todo.listup.response.BoardPostResponse;
import com.todo.listup.response.BoardUpdateResponse;
import com.todo.listup.service.BoardService;
import com.todo.listup.vo.Search;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class BoardController {

  private final BoardService boardService;

  @GetMapping("/get/board/{id}")
  public ResponseEntity<?> getBoardById(@PathVariable Long id) {
    BoardGetResponse response = boardService.getBoardById(id);
    return ResponseEntity.ok(Map.of("data", response));
  }

  @GetMapping("/get/board")
  public ResponseEntity<?> getBoard(Search search) {
    List<BoardGetResponse> responseList = boardService.getPage(search);
    return ResponseEntity.ok(responseList);
  }

  @PostMapping("/create/board")
  public ResponseEntity<?> createBoard(@RequestBody BoardPostRequest request) {
    Long boardId = boardService.createBoard(request);
    return ResponseEntity.ok(boardId);
  }

  @PutMapping("/update/board/{id}")
  public ResponseEntity<?> updateBoard(@PathVariable Long id, @RequestBody BoardUpdateRequest request) {
    Long boardId = boardService.updateBoard(id, request);
    return ResponseEntity.ok(boardId);
  }

  @DeleteMapping("/delete/board/{id}")
  public ResponseEntity<?> deleteBoard(@PathVariable Long id) {
    Long boardId = boardService.deleteBoard(id);
    return ResponseEntity.ok(boardId);
  }



}
