package com.todo.listup.security;

import com.todo.listup.entity.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Slf4j
@Component
public class SecurityUtil {

  public static final String JWT_STRING = "5f57bb1b29cc240d42d575006dc04689e4f23833b7cd7b5dd28ab60105f039cbb5b347cb6e13e98966f7eea88121b34dd5ba08272f7f537d87a0e709ff3bd7c1";
  public static final SecretKey key = Keys.hmacShaKeyFor(JWT_STRING.getBytes(StandardCharsets.UTF_8));

  public static String createJwt(final Long id, final Role role) {
    JwtBuilder jwtBuilder = Jwts.builder()
        .setIssuer("LIST_UP_PRZ")
        .claim("userId", id)
        .claim("role", role)
        .setIssuedAt(new Date())
        .setExpiration(new Date(new Date().getTime() + 360000))
        .signWith(key);
    return jwtBuilder.compact();
  }

  public static Claims checkJwt(final String jwts) {
    return Jwts.parser()
        .setSigningKey(key)
        .parseClaimsJws(jwts
            .replace("Bearer ", "")
            .trim())
        .getBody();
  }
}
