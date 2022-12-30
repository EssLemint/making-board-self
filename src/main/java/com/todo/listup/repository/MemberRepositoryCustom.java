package com.todo.listup.repository;

import com.todo.listup.entity.Member;

public interface MemberRepositoryCustom {
  Member findMemberByIdAndPassword(String id, String password);

  Member findMemberByUsername(String username);
}
