package com.daldal.schetalk.service.member;

import com.daldal.schetalk.domain.member.Member;
import com.daldal.schetalk.repository.member.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public Long join(String username,String password){
        return memberRepository.save(
                Member.builder()
                        .username(username)
                        .password(password)
                        .build()
        ).getMemberId();
    }

    public boolean duplicate(String username) {
        return memberRepository.findByUsername(username).isEmpty();
    }

    public Optional<Member> getMember(String username){
        return memberRepository.findByUsername(username);
    }


}
