package com.todo.listup.service;

import com.todo.listup.dto.member.request.MemberLoginRequest;
import com.todo.listup.dto.member.request.MemberPostRequest;
import com.todo.listup.dto.member.request.MemberPutRequest;
import com.todo.listup.dto.member.response.MemberGetResponse;
import com.todo.listup.entity.Member;
import com.todo.listup.entity.Role;
import com.todo.listup.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

  private final MemberRepository memberRepository;
  ModelMapper modelMapper = new ModelMapper();

  private final BCryptPasswordEncoder bCryptPasswordEncoder;
  private final AuthenticationManager authenticationManager;

  @Transactional
  public Long createMember(MemberPostRequest request) {
    Member member = new Member(request.getUserId(), bCryptPasswordEncoder.encode(request.getPassword())
        , request.getUserName(), request.getEmail(), Role.ROLE_USER.name());
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
    member.updateMember(request.getUserName(), request.getEmail(), request.getPassword());

    return id;
  }

  @Transactional
  public Long deleteMember(Long id) {
    Member member = memberRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    member.deleteMember();

    return id;
  }

  public Long loginMember(MemberLoginRequest request) throws Exception {
    Member member = memberRepository.findMemberByIdAndPassword(request.getUserId(), request.getPassword());
    Long memberId = 0L;
    if (!Objects.isNull(member)) {
      memberId = member.getId();
    }
    return memberId;
  }
}
