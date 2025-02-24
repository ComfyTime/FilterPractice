package org.example.filterpractice.auth.service;

import lombok.RequiredArgsConstructor;
import org.example.filterpractice.auth.dto.AuthLoginRequestDto;
import org.example.filterpractice.auth.dto.AuthLoginResponseDto;
import org.example.filterpractice.auth.dto.AuthSignupRequestDto;
import org.example.filterpractice.auth.dto.AuthSignupResponseDto;
import org.example.filterpractice.member.dto.MemberSaveResponseDto;
import org.example.filterpractice.member.entity.Member;
import org.example.filterpractice.member.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final MemberRepository memberRepository;

    @Transactional
    public void signup(AuthSignupRequestDto dto) {
        Member member = new Member(dto.getEmail());
        memberRepository.save(member);
    }

    public AuthLoginResponseDto login(AuthLoginRequestDto dto) {
        Member member = memberRepository.findByEmail(dto.getEmail()).orElseThrow(
                () -> new IllegalStateException("해당 이메일 없음")
        );
        return new AuthLoginResponseDto(member.getId());
    }
}
