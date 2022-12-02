package com.todo.listup.dto.member.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberGetResponse {
  private String userId;
  private String userPassword;
  private String userName;
  private String email;
}
