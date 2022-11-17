package com.todo.listup.service;

import com.todo.listup.entity.Board;
import com.todo.listup.repository.BoardRepository;
import com.todo.listup.request.BoardPostRequest;
import com.todo.listup.request.BoardUpdateRequest;
import com.todo.listup.response.BoardGetResponse;
import com.todo.listup.response.BoardPostResponse;
import com.todo.listup.response.BoardUpdateResponse;
import com.todo.listup.vo.Search;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardService {

  private final BoardRepository boardRepository;
  ModelMapper modelMapper = new ModelMapper();

  public BoardGetResponse getBoardById(Long id) {
    Board board = boardRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    BoardGetResponse response = modelMapper.map(board, BoardGetResponse.class);
    return response;
  }

  @Transactional
  public Long createBoard(BoardPostRequest request) {
    Board board = new Board(request.getTitle(), request.getContents());
    Long id = boardRepository.save(board).getId();
    return id;
  }

  @Transactional
  public Long updateBoard(Long id, BoardUpdateRequest request) {
    Board board = boardRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    board.updateBoard(request.getTitle(), request.getContents());
    return id;
  }

  @Transactional
  public Long deleteBoard(Long id) {
    Board board = boardRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    board.deleteBoard();

    return id;
  }

  public List<BoardGetResponse> getPage(Search search) {
    Page<Board> boardPage = boardRepository.getPage(search);

    List<BoardGetResponse> responseList = boardPage.getContent().stream().map(board -> BoardGetResponse.builder()
        .title(board.getTitle())
        .contents(board.getContents())
        .id(board.getId())
        .build()).collect(Collectors.toList());

    return responseList;

  }
}
