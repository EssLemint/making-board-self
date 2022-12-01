package com.todo.listup.member;

import com.todo.listup.request.member.request.MemberPutRequest;
import com.todo.listup.request.member.response.MemberGetResponse;
import com.todo.listup.request.member.request.MemberPostRequest;
import com.todo.listup.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class MemberController {
  private final MemberService memberService;

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

  @DeleteMapping("delete/member")
  public ResponseEntity<?> deleteMember(@PathVariable Long id) {
    Long memberId = memberService.deleteMember(id);
    return ResponseEntity.ok(memberId);
  }

}
