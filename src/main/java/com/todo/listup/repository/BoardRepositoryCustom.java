package com.todo.listup.repository;

import com.todo.listup.entity.Board;
import com.todo.listup.vo.Search;
import org.springframework.data.domain.Page;

public interface BoardRepositoryCustom{
  Page<Board> getPage(Search search);
}
