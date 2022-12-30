package com.todo.listup.signIn;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignInRequest {

  @NotEmpty
  String userId;

  @NotEmpty
  String password;
}
