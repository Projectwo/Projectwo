package com.project.Projectwo.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.project.Projectwo.Entity.Course;
import com.project.Projectwo.Entity.Member;
import com.project.Projectwo.Entity.Student;
import com.project.Projectwo.Repository.MemberRepository;


import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MemberService {

	private final MemberRepository memberRepository;
	
	// by 안준언, identity로 특정 멤버 반환
	public Member getMember(String identity) {
		Optional<Member> mb = this.memberRepository.findByIdentity(identity);
		if(mb.isEmpty()) {
			return null;
		} else {
			Member member = mb.get();
			return member;
		}
	}

	// by 안준언, pk(id)로 특정 멤버 반환 (오버로딩)
	public Member getMember(int memberId) {
		Optional<Member> mb = this.memberRepository.findById(memberId);
		if(mb.isEmpty()) {
			return null;
		} else {
			Member member = mb.get();
			return member;
		}
	}

	// by 안준언, 같은 role 필드 값을 가진 멤버 반환 ("teacher" or "student" or "admin")
	public List<Member> getAllMemberByRole(String role) {
		List<Member> allMember = this.memberRepository.findByRole(role);
		return allMember;
	}

	public Student getStudent(Member member, Course course) {
		// TODO Auto-generated method stub
		return null;
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
