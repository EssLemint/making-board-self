package com.todo.listup.service;

import com.todo.listup.entity.Board;
import com.todo.listup.entity.Images;
import com.todo.listup.repository.BoardRepository;
import com.todo.listup.repository.ImageRepository;
import com.todo.listup.dto.board.request.BoardPostRequest;
import com.todo.listup.dto.board.request.BoardUpdateRequest;
import com.todo.listup.dto.board.response.BoardGetResponse;
import com.todo.listup.dto.board.response.BoardImgResponse;
import com.todo.listup.vo.Search;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriUtils;


import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardService {

  private final BoardRepository boardRepository;
  private final ImageRepository imageRepository;

  ModelMapper modelMapper = new ModelMapper();

  @Value("${spring.servlet.multipart.location}")
  private String fileDir;


  public BoardGetResponse getBoardById(Long id) {
    Board board = boardRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    BoardGetResponse response = modelMapper.map(board, BoardGetResponse.class);
    return response;
  }

  @Transactional
  public Long createBoard(BoardPostRequest request,
                          MultipartFile[] multipartFile) throws IOException {
    Board board = new Board(request.getTitle(), request.getContents());
    Long id = boardRepository.save(board).getId();

    log.info("multipartFile = {}", (Object) multipartFile);
    if (!Objects.isNull(multipartFile)) {
      for (MultipartFile file : multipartFile) {
        String originalFilename = file.getOriginalFilename();
        log.info("originalFilename = {}", originalFilename);

        long size = file.getSize();
        log.info("size = {}", size);

        String contentType = file.getContentType();
        log.info("contentType = {}", contentType);

        String fullPath = fileDir + originalFilename;
        log.info("fullPath = {}", fullPath);

        String uuid = UUID.randomUUID().toString();
        String uploadFileName = uuid + contentType;

        Images images = new Images(originalFilename, uploadFileName, fullPath, size, contentType);
        images.setBoard(board);
        imageRepository.save(images);

        file.transferTo(new File(fullPath));
      }
    }
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

  public BoardImgResponse downloadImages(Long id) throws MalformedURLException {
    Images images = imageRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    BoardImgResponse response = new BoardImgResponse();

    if (!Objects.isNull(images)) {
      String storedFileName = images.getStoredFileName();
      String uploadFileName = images.getUploadFileName();

      UrlResource urlResource = new UrlResource("file:" + fileDir + storedFileName);
      log.info("urlResource = {}", urlResource);

      String encodingUploadFileName = UriUtils.encode(uploadFileName, StandardCharsets.UTF_8);
      String contentDisposition = "attachment; filename=\"" + encodingUploadFileName + "\"";
      log.info("contentDisposition = {}", contentDisposition);

      response.setContents(contentDisposition);
      response.setUrlResources(urlResource);
    }
    return response;
  }
}
