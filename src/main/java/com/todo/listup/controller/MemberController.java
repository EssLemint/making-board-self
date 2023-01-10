package com.todo.listup.controller;

import com.todo.listup.dto.member.request.MemberLoginRequest;
import com.todo.listup.dto.member.request.MemberPutRequest;
import com.todo.listup.dto.member.response.MemberGetResponse;
import com.todo.listup.dto.member.request.MemberPostRequest;
import com.todo.listup.service.MemberService;
import com.todo.listup.signIn.SignInRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import java.util.Objects;

@RestController
@RequiredArgsConstructor
public class MemberController {
  private final MemberService memberService;

  @Secured("ROLE_USER")
  @GetMapping("/get/member/{id}")
  public ResponseEntity<?> getMemberById(@PathVariable Long id) {
    MemberGetResponse response = memberService.getMemberById(id);
    return ResponseEntity.ok(response);
  }

  @PostMapping("/new/member")
  public ResponseEntity<?> createMember(@RequestBody @Valid MemberPostRequest request) {
    Long id = memberService.createMember(request);
    return ResponseEntity.ok(id);
  }

  @PutMapping("/update/member")
  public ResponseEntity<?> updateMember(@PathVariable Long id, @RequestBody MemberPutRequest request) {
    Long memberId = memberService.updateMember(id, request);
    return ResponseEntity.ok(memberId);
  }

  @DeleteMapping("/delete/member")
  public ResponseEntity<?> deleteMember(@PathVariable Long id) {
    Long memberId = memberService.deleteMember(id);
    return ResponseEntity.ok(memberId);
  }

  @GetMapping("/login/member")
  public ResponseEntity<?> loginMember(@RequestBody @Valid MemberLoginRequest request,
                                       BindingResult bindingResult,
                                       HttpServletRequest httpRequest) throws Exception {
    if (bindingResult.hasErrors()) {
      return ResponseEntity.badRequest().body(bindingResult);
    }
    Long memberId = memberService.loginMember(request);
    HttpSession session = httpRequest.getSession();
    session.setAttribute("memberId", memberId);
    return ResponseEntity.ok(200);
  }

  @GetMapping("/logout/member")
  public ResponseEntity<?> logoutMember(HttpServletRequest httpRequest) {
    HttpSession session = httpRequest.getSession();
    if (!Objects.isNull(session)) {
      session.removeAttribute("sessionId");
    }
    return ResponseEntity.ok(200);
  }
}
