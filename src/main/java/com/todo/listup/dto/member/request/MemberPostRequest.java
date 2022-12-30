package com.todo.listup.dto.member.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberPostRequest {

  @NotEmpty
  private String userId;
  @NotEmpty
  private String password;
  @NotEmpty
  private String userName;
  @NotEmpty
  @Email
  private String email;
}
