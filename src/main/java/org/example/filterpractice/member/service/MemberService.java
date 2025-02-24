package org.example.filterpractice.member.service;


import lombok.RequiredArgsConstructor;
import org.example.filterpractice.member.dto.MemberResponseDto;
import org.example.filterpractice.member.dto.MemberUpdateRequestDto;
import org.example.filterpractice.member.entity.Member;
import org.example.filterpractice.member.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional(readOnly = true)
    public List<MemberResponseDto> findAll() {
        List<Member> members = memberRepository.findAll();

// 기존 방식
//        List<MemberResponseDto> dto = new ArrayList<>();
//        for (Member member : members) {
//            dto.add(new MemberResponseDto(
//                    member.getId(),
//                    member.getEmail()
//            ));
//        }
//        return dto;
//
//    // stream 사용
        return members.stream().map(member -> new MemberResponseDto(member.getId(), member.getEmail())).toList();
    }

    @Transactional(readOnly = true)
    public MemberResponseDto findById (Long memberId){
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new IllegalArgumentException("해당 Id 없음"));
        return new MemberResponseDto(
                member.getId(),
                member.getEmail()
        );
    }

    @Transactional
    public void update(Long memberId, MemberUpdateRequestDto dto){
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new IllegalArgumentException("해당 Id 없음"));
        member.update(dto.getEmail());
    }

    @Transactional
    public void deleteById(Long memberId) {
        memberRepository.deleteById(memberId);
    }

}
