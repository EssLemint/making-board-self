package com.todo.listup.service;

import com.todo.listup.entity.Board;
import com.todo.listup.repository.BoardRepository;
import com.todo.listup.request.BoardPostRequest;
import com.todo.listup.request.BoardUpdateRequest;
import com.todo.listup.response.BoardGetResponse;
import com.todo.listup.response.BoardPostResponse;
import com.todo.listup.response.BoardUpdateResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardService {

  private final BoardRepository boardRepository;
  ModelMapper modelMapper = new ModelMapper();

  public ResponseEntity<?> getBoardById(Long id) {
    Board board = boardRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    BoardGetResponse response = modelMapper.map(board, BoardGetResponse.class);

    return ResponseEntity.ok(response);
  }

  @Transactional
  public ResponseEntity<?> createBoard(BoardPostRequest request) {
    Board board = new Board(request.getTitle(), request.getContents());
    Board savedBoard = boardRepository.save(board);
    BoardPostResponse response = modelMapper.map(savedBoard, BoardPostResponse.class);

    return ResponseEntity.ok(response);
  }

  @Transactional
  public ResponseEntity<?> updateBoard(Long id, BoardUpdateRequest request) {
    Board board = boardRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    board.updateBoard(request.getTitle(), request.getContents());
    BoardUpdateResponse response = modelMapper.map(id, BoardUpdateResponse.class);

    return ResponseEntity.ok(response);
  }

  @Transactional
  public ResponseEntity<?> deleteBoard(Long id) {
    Board board = boardRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    board.deleteBoard();

    return ResponseEntity.ok(id);
  }
}
