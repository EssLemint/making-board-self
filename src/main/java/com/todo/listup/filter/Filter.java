package com.todo.listup.filter;

import javax.servlet.*;
import java.io.IOException;

public interface Filter {
  /**
   * 필터 인터페이스, 싱글톤 객체로 생서으 관리
   * */

  public default void init(FilterConfig filterConfig) throws ServletException {

  }

  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException;

  public default void destroy() {

  }
}
