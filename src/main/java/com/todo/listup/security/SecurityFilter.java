package com.todo.listup.security;

import com.todo.listup.entity.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import static com.todo.listup.entity.Role.ROLE_ANONYMOUS;
import static com.todo.listup.security.SecurityUtil.*;
import static java.util.Optional.ofNullable;
import static org.springframework.util.StringUtils.hasLength;


@Slf4j
public class SecurityFilter extends OncePerRequestFilter {

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    log.info("======== SecurityFilter START ========");

    String authorization = request.getHeader("Authorization");
    String jwt = ofNullable(authorization).orElse(null); //Bearer Token
    Long id = 0L;
    Role role = ROLE_ANONYMOUS;

    if (hasLength(jwt)) {
      Claims claims = checkJwt(jwt);
      id = Long.parseLong(String.valueOf(claims.get("userId")));
      role = Role.valueOf(String.valueOf(claims.get("role")));
    }

    SecurityContextHolder.getContext()
        .setAuthentication(new UsernamePasswordAuthenticationToken(
            id, null, AuthorityUtils.createAuthorityList(role.name()))
        );


    log.info("id = {}", id);
    log.info("role = {}", role);
    log.info("======== SecurityFilter END ========");

    filterChain.doFilter(request, response);
  }
}
