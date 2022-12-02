package com.todo.listup.dto.member.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberPutRequest {
  private String userId;
  private String userPassword;
  private String userName;
  private String email;
}
