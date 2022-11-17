package com.todo.listup.repository.impl;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.todo.listup.entity.Board;
import com.todo.listup.entity.QBoard;
import com.todo.listup.repository.BoardRepositoryCustom;
import com.todo.listup.vo.Search;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
public class BoardRepositoryCustomImpl implements BoardRepositoryCustom {

  private final JPAQueryFactory jpaQueryFactory;

  @Override
  public Page<Board> getPage(Search search) {
    JPAQuery<Board> query = jpaQueryFactory.selectFrom(QBoard.board);

    if (!Objects.isNull(search.getSearchType()) && search.getSearchType().equalsIgnoreCase("title")) {
      query.where(QBoard.board.title.contains(search.getSearchWord()));
    }
    if (!Objects.isNull(search.getSearchType()) && search.getSearchType().equalsIgnoreCase("contents")) {
      query.where(QBoard.board.contents.contains(search.getSearchWord()));
    }

    query.orderBy(QBoard.board.id.desc());

    PageRequest pageRequest = PageRequest.of(search.getPage(), search.getSize());

    query.offset(pageRequest.getOffset())
        .limit(pageRequest.getPageSize());

    List<Board> boardList = query.fetch();

    return new PageImpl<>(boardList, pageRequest, boardList.size());
  }
}
