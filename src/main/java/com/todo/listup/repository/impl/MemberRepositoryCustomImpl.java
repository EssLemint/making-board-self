package com.todo.listup.repository.impl;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.todo.listup.entity.Member;
import com.todo.listup.entity.QMember;
import com.todo.listup.repository.MemberRepositoryCustom;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MemberRepositoryCustomImpl implements MemberRepositoryCustom {

  private final JPAQueryFactory queryFactory;

  @Override
  public Member findMemberByIdAndPassword(String id, String password)  {
    JPAQuery<Member> query = queryFactory.selectFrom(QMember.member)
        .where(QMember.member.userId.eq(id)).where(QMember.member.password.eq(password));
    Member member = query.fetchOne();

    return member;
  }

  @Override
  public Member findMemberByUsername(String username) {
    JPAQuery<Member> query = queryFactory.selectFrom(QMember.member)
        .where(QMember.member.username.eq(username));
    Member member = query.fetchOne();

    return member;
  }

  @Override
  public Member findByUserId(String userId) {

    JPAQuery<Member> query = queryFactory.selectFrom(QMember.member)
        .where(QMember.member.userId.eq(userId));
    Member member = query.fetchOne();

    return member;
  }
}
