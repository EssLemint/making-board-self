package com.todo.listup.security;

import com.todo.listup.entity.Role;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.todo.listup.entity.Role.ROLE_USER;
import static java.util.Optional.ofNullable;
import static org.springframework.util.StringUtils.hasLength;

@Slf4j
public class SecurityFilter extends OncePerRequestFilter {

  @Autowired
  private SecurityUtil securityUtil;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                  FilterChain filterChain) throws ServletException, IOException {
    log.info("*** Security Filter ***");

    try {
      String header = request.getHeader("Authorization");
      String jwt = ofNullable(header).orElse(null);
      Role role = ROLE_USER;

      if (hasLength(jwt)) {
        String acessToken = jwt.replace("Bearer ", "");
      }

      filterChain.doFilter(request, response);

    } catch (ExpiredJwtException e) {
      log.error("*** Security Filter *** Error");
    }
  }
}
