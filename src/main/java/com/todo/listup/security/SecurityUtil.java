package com.todo.listup.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

import static com.todo.listup.security.CryptoUtil.encryptBase64;
import static com.todo.listup.security.CryptoUtil.encryptSha256;
import static io.jsonwebtoken.SignatureAlgorithm.HS512;
import static java.nio.charset.StandardCharsets.UTF_8;

@Component
@Slf4j
public class SecurityUtil {

  public static String jwtSecret = "KEYSECRETS";
  public static final String JWT_SHA = encryptSha256(jwtSecret);
  public static final byte[] JWT_BYTE = encryptBase64(JWT_SHA).getBytes(UTF_8); // JWT 암호화 키 Byte
  public static final Key JWT_KEY = new SecretKeySpec(JWT_BYTE, HS512.getJcaName());

  @Value("${jwt.expire.time}")
  private int jwtExpire;

  public String createJwtToken(String userId) {

    return Jwts.builder()
        .setSubject(userId)
        .setIssuedAt(new Date())
        .setExpiration(new Date((new Date()).getTime() + jwtExpire))
        .signWith(JWT_KEY)
        .compact();
  }

  public String getUserIdFromToken (String token) {
    return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJwt(token).getBody().getSubject();
  }

  public static Claims get(String jwt) {
    return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJwt(jwt).getBody();
  }

  public static boolean checkExpireation(String jwt) {
    boolean flag = false;
    try {
      get(jwt);
    } catch (ExpiredJwtException e) {
      flag = true;
    }
    return false;
  }


}
