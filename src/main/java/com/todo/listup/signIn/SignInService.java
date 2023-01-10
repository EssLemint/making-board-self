package com.todo.listup.signIn;

import com.todo.listup.entity.Member;
import com.todo.listup.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

import static com.todo.listup.security.SecurityUtil.createJwt;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SignInService {

  private final SignInRepository repository;
  private final MemberRepository memberRepository;
  private final PasswordEncoder passwordEncoder;

  @Transactional
  public ResponseEntity<?> signIn(SignInRequest request) {
    //로그인 - signin에 있는지 확인
    Sign signByUserId = repository.findSignByUserId(request.getUserId());
    if (!Objects.isNull(signByUserId)) {
      signByUserId.updateSignDate();
      return ResponseEntity.ok("Token Update");
    } else {
      Member member = memberRepository.findByUserId(request.getUserId());
      if (passwordEncoder.matches(request.password, member.getPassword())) {
        String jwt = createJwt(member.getId(), member.getRole());
        Sign entity = new Sign(request.userId, passwordEncoder.encode(request.password), member.getRole());
        repository.save(entity);
        return ResponseEntity.ok(jwt);
      }

      return ResponseEntity.badRequest().build();
    }
  }
}
