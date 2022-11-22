package com.todo.listup.repository;

import com.todo.listup.entity.Board;
import com.todo.listup.entity.Images;
import com.todo.listup.vo.Search;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BoardRepositoryCustom{
  Page<Board> getPage(Search search);
}
