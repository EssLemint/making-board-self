package com.todo.listup.signIn;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Slf4j
@RequiredArgsConstructor
public class SigInController {
  private final SignInService service;

  @GetMapping("/sigin/user")
  public ResponseEntity<?> signIn(@RequestBody @Valid SignInRequest request) {
    return service.signIn(request);
  }
}
