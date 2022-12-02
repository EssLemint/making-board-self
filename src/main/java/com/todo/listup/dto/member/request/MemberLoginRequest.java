package com.todo.listup.dto.member.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberLoginRequest {

  @NotEmpty
  private String userId;
  @NotEmpty
  private String userPassword;
}
