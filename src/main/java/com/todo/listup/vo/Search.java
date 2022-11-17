package com.todo.listup.vo;

import lombok.Data;

@Data
public class Search {
  protected Integer page;
  protected Integer size;

  protected String searchType;
  protected String searchWord;
}
