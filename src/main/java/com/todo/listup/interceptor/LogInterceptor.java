package com.todo.listup.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;
import java.util.UUID;

@Slf4j
public class LogInterceptor implements HandlerInterceptor {
  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    String requestURI = request.getRequestURI();
    String uuid = UUID.randomUUID().toString();

    request.setAttribute("logId", uuid);
    //@RequestMapping : HandlerMethod
    //정적 리소스 : ResourceHttpRequestHandler
    if (handler instanceof HandlerMethod) {
      HandlerMethod hm = (HandlerMethod) handler;
    }
    log.info("REQUEST [{}][{}][{}]", uuid, requestURI, handler);
    return true;
  }

  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    log.info("postHandler [{}]", modelAndView);
  }

  @Override
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    String requestURI = request.getRequestURI();
    String logId = request.getAttribute("logId").toString();
    log.info("RESPONSE [{}][{}][{}]", logId, requestURI, handler);

    if (!Objects.isNull(ex)) {
      log.error("error", ex);
    }
  }
}
