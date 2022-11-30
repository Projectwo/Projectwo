package com.project.Projectwo.Service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.project.Projectwo.Entity.Member;
import com.project.Projectwo.Repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MemberService {

	private final MemberRepository memberRepository;
	
	public Member getMember(String identity) {
		Optional<Member> mb = this.memberRepository.findByIdentity(identity);
		if(mb.isEmpty()) {
			return null;
		} else {
			Member member = mb.get();
			return member;
		}
	}
	/*
	 공통
	 회원가입
	 */
	
	/*
	학생
	내 강의 목록
	강의 시간표 출력
	강의 게시판 출력
	출결 !!!!!!! 제일 중요함 따로 설명 필요
	 */
	
	/*
	선생님
	내 수업 목록
	강의 시간표 출력
	강의 게시판 출력
	공지글 작성/수정
	 */
	
}
