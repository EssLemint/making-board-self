package com.todo.listup.signIn;

import com.todo.listup.security.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SignInService {

  private final SignInRepository repository;
  ModelMapper modelMapper = new ModelMapper();
  private final SecurityUtil utils;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;

  @Transactional
  public ResponseEntity<?> signIn(SignInRequest request) {
    Sign signByUserId = repository.findSignByUserId(request.getUserId());
    if (Objects.isNull(signByUserId)) {
      String jwtToken = utils.createJwtToken(request.getUserId());
      Sign sign = new Sign(request.getUserId(), bCryptPasswordEncoder.encode(request.password));
      repository.save(sign);

      SignInResponse response = SignInResponse
          .builder()
          .userId(request.getUserId())
          .token(jwtToken).build();

      return ResponseEntity.ok(response);
    } else {
      signByUserId.updateSignDate();
    }

    return ResponseEntity.ok("TOKEN UPDATE");
  }

}
