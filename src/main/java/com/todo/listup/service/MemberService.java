package com.todo.listup.service;

import com.todo.listup.entity.Member;
import com.todo.listup.repository.MemberRepository;
import com.todo.listup.request.member.response.MemberGetResponse;
import com.todo.listup.request.member.request.MemberPostRequest;
import com.todo.listup.request.member.request.MemberPutRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

  private final MemberRepository memberRepository;
  ModelMapper modelMapper = new ModelMapper();

  @Transactional
  public Long createMember(MemberPostRequest request) {
    Member member = new Member(request.getUserId(), request.getUserName(), request.getUserPassword(), request.getUserName());
    Long id = memberRepository.save(member).getId();

    return id;
  }

  public MemberGetResponse getMemberById(Long id) {
    Member member = memberRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    MemberGetResponse response = modelMapper.map(member, MemberGetResponse.class);

    return response;
  }

  @Transactional
  public Long updateMember(Long id, MemberPutRequest request) {
    Member member = memberRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    member.updateMember(request.getUserName(), request.getEmail(), request.getUserPassword());

    return id;
  }

  @Transactional
  public Long deleteMember(Long id) {
    Member member = memberRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    member.deleteMember();

    return id;
  }

}
