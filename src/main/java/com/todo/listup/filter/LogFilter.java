package com.todo.listup.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.UUID;

@Slf4j
public class LogFilter implements Filter{
  /**
   * Http 요청 시  doFilter 호출
   * */

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    log.info("log filter init");
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
  /**
   * ServletRequest는 http 요청이 아닌경우도 포함 되어있다 http를 사용하면 아래와 같이 cast해주면 됨
   * */
    HttpServletRequest httpRequest = (HttpServletRequest) request;
    String requestURI = httpRequest.getRequestURI();

    String uuid = UUID.randomUUID().toString();
    try {
      log.info("REQUEST [{}][{}]", uuid, requestURI);
      chain.doFilter(request, response);
    } catch (Exception e) {
      throw e;
    } finally {
      log.info("RESPONSE [{}][{}]", uuid, requestURI);
    }

  }

  @Override
  public void destroy() {
    /**
     * 가장 중요한 부분 다음 필터가 있다면 호출 없으면 서블릿
     * */
    log.info("log filter destroy");
  }
}
