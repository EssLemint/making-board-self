package com.todo.listup.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.todo.listup.request.BoardPostRequest;
import com.todo.listup.request.BoardUpdateRequest;
import com.todo.listup.service.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
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

  @BeforeAll
  public void setUp() {

  }


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

    MvcResult mvcResult = mvc.perform(post("/create/board")
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(BoardPostRequest
                .builder()
                .title("test-title")
                .contents("test-contents")
                .build())))
        .andExpect(status().isOk())
        .andReturn();

    log.info("mvcResult = {}", mvcResult.getResponse().getContentAsString());
    JsonNode jsonNode = mapper.readTree(mvcResult.getResponse().getContentAsString());
    long id = jsonNode.get("id").asLong();

    mvc.perform(get("/get/board/{id}", id)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
        .andDo(print());

    log.info("========== 게시글 조회 ==========");
  }

  @Test
  @DisplayName("게시글 수정")
  void updateBoard() throws Exception {

    log.info("======== 게시글 수정 =========");

    MvcResult mvcResult = mvc.perform(post("/create/board")
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(BoardPostRequest
                .builder()
                .title("test-title")
                .contents("test-contents")
                .build())))
        .andExpect(status().isOk())
        .andReturn();

    JsonNode jsonNode = mapper.readTree(mvcResult.getResponse().getContentAsString());
    long id = jsonNode.get("id").asLong();

    mvc.perform(put("/update/board/{id}", id)
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(BoardUpdateRequest
                .builder()
                .title("TITLE-UPDATE")
                .contents("CONTENTS-UPDATE")
                .build())))
        .andExpect(status().isOk())
        .andDo(print());

    log.info("======== 게시글 수정 =========");
  }

  @Test
  @DisplayName("게시글 삭제")
  void deleteBoard() throws Exception{
    MvcResult mvcResult = mvc.perform(post("/create/board")
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(BoardPostRequest
                .builder()
                .title("test-title")
                .contents("test-contents")
                .build())))
        .andExpect(status().isOk())
        .andReturn();

    JsonNode jsonNode = mapper.readTree(mvcResult.getResponse().getContentAsString());
    long id = jsonNode.get("id").asLong();

    mvc.perform(delete("/delete/board/{id}", id)
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andDo(print());
  }

}