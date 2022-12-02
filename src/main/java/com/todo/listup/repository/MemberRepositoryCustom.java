package com.todo.listup.repository;

import com.todo.listup.entity.Member;

import java.util.Optional;

public interface MemberRepositoryCustom {
  Member findMemberByIdAndPassword(String id, String password);
}
