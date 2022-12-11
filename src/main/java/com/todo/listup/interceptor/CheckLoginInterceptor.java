package com.todo.listup.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Objects;

@Slf4j
public class CheckLoginInterceptor implements HandlerInterceptor {

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    HttpSession session = request.getSession();
    String uri = request.getRequestURI();
    log.info("session = {}, uri = {}", session, uri);
    if (Objects.isNull(session) || Objects.isNull(session.getAttribute("memberId"))) {
      log.info("인증 확인 필요");

      response.sendError(499, "로그인이 필요합니다.");
      return false;
    }

    return true;
  }
}
