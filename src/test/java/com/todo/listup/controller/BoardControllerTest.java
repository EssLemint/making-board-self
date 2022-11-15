package com.todo.listup.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.todo.listup.entity.Board;
import com.todo.listup.repository.BoardRepository;
import com.todo.listup.request.BoardPostRequest;
import com.todo.listup.response.BoardGetResponse;
import com.todo.listup.response.BoardPostResponse;
import com.todo.listup.service.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Slf4j
@WebMvcTest(controllers = BoardController.class)
@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class BoardControllerTest {

  @Autowired
  MockMvc mvc;

  @Autowired
  private ObjectMapper mapper;

  @Mock
  private BoardRepository boardRepository;
  @Mock
  private BoardService boardService;
  @InjectMocks
  private BoardController boardController;

  @BeforeAll
  public void setUp() {
    mvc = MockMvcBuilders.standaloneSetup(boardController).build();
  }

  private BoardPostResponse boardPostResponse() {
    BoardPostResponse response = new BoardPostResponse();
    response.setId(1L);

    return response;
  }

  private BoardPostRequest boardPostRequest() {
    BoardPostRequest request = new BoardPostRequest();
    request.setTitle("title-test");
    request.setContents("contents-test");

    return request;
  }

  @Test
  @DisplayName("게시글 생성")
  void createBoard() throws Exception {
    BoardPostRequest request = boardPostRequest();
    BoardPostResponse response = boardPostResponse();

    doReturn(ResponseEntity.ok(response)).when(boardService)
        .createBoard(any(BoardPostRequest.class));

    mvc.perform(post("/create/board")
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(request)))
        .andExpect(status().isOk())
        .andDo(print());
  }

  @Test
  @DisplayName("게시글 조회 By Id")
  void getBoardById() throws Exception {

    Board board = new Board("test-title", "test-contents");
    boardRepository.save(board);

    mvc.perform(get("/get/board/{id}", 1)
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andDo(MockMvcResultHandlers.log());
  }

  @Test
  @DisplayName("게시글 수정")
  void updateBoard() {

  }


}