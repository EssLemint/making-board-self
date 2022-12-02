package com.todo.listup.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.todo.listup.dto.board.request.BoardPostRequest;
import com.todo.listup.dto.board.request.BoardUpdateRequest;
import com.todo.listup.service.BoardService;
import com.todo.listup.vo.Search;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@AutoConfigureMockMvc
@Transactional
@SpringBootTest
class BoardControllerTest {

  @Autowired
  private MockMvc mvc;

  @Autowired
  protected ObjectMapper mapper;

  @Autowired
  private BoardService boardService;

  @Test
  @DisplayName("게시글 생성")
  void createBoard() throws Exception {
    log.info("===== 게시글 생성 =====");

    mvc.perform(post("/create/board")
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(BoardPostRequest
                .builder()
                .title("test-title")
                .contents("test-contents")
                .build())))
        .andExpect(status().isOk())
        .andDo(print());

    log.info("===== END =====");
  }

  @Test
  @DisplayName("게시글 조회 By Id")
  void getBoardById() throws Exception {

    log.info("======== 게시글 조회 =========");

    Long boardId = boardService.createBoard(BoardPostRequest.builder()
        .contents("TEST-CONTENTS")
        .title("TEST-TITLE")
        .build());

    mvc.perform(get("/get/board/{id}", boardId)
            .contentType(MediaType.APPLICATION_JSON))
        .andDo(print());

    log.info("========== 게시글 조회 ==========");
  }

  @Test
  @DisplayName("게시판 조회")
  void getBoard() throws Exception {
    for (int i = 1; i < 11; i++) {
      boardService.createBoard(BoardPostRequest.builder()
          .contents("TEST-CONTENTS" + i)
          .title("TEST-TITLE" + i)
          .build());
    }

    Search search = new Search();
      search.setPage(3);
      search.setSize(2);
      search.setSearchType("title");
      search.setSearchWord("T");

//      mvc.perform(get("/get/board", search)
//              .contentType(MediaType.APPLICATION_JSON))
//          .andDo(print());

    mvc.perform(get("/get/board")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .param("page", "3")
            .param("size", "2")
            .param("searchWord", "T")
            .param("searchType", "title"))
        .andDo(print());
  }

  @Test
  @DisplayName("게시글 수정")
  void updateBoard() throws Exception {

    log.info("======== 게시글 수정 =========");

    Long boardId = boardService.createBoard(BoardPostRequest.builder()
        .contents("TEST-CONTENTS")
        .title("TEST-TITLE")
        .build());

    mvc.perform(put("/update/board/{id}", boardId)
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(BoardUpdateRequest.builder()
                .title("TEST_UPDATE-TITLE")
                .contents("TEST_UPDATE-CONTENTS")
                .build())))
        .andExpect(status().isOk())
        .andDo(print());

    log.info("======== 게시글 수정 =========");
  }

  @Test
  @DisplayName("게시글 삭제")
  void deleteBoard() throws Exception{
    Long boardId = boardService.createBoard(BoardPostRequest.builder()
        .contents("TEST-CONTENTS")
        .title("TEST-TITLE")
        .build());

    mvc.perform(delete("/delete/board/{id}", boardId)
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andDo(print());
  }

}