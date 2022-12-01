package com.todo.listup.login;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
public class LonginArgumentResolver implements HandlerMethodArgumentResolver {
  @Override
  public boolean supportsParameter(MethodParameter parameter) {
    /**
     * Controller 파라미터 값을 검사하는 콜백 함수
     * parameter = 클라이언트의 파라미터
     * return = 적용 여부
     * */
    log.info("supportsParameter");
    boolean hasLoginAnnotation = parameter.hasParameterAnnotation(Login.class);
    log.info("hasLoginAnnotation = {}", hasLoginAnnotation);
    boolean equalsToString = parameter.getParameterType().equals(String.class);

    return hasLoginAnnotation && equalsToString;
  }

  @Override
  public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
    /**
     * supportsParameter에서 true을 반환 했을 경우 호출 되는 함수
     * */

    HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
    HttpSession session = request.getSession(false);

    if (session == null) {
      return null;
    }
    return session.getAttribute("Login-User");
  }
}
