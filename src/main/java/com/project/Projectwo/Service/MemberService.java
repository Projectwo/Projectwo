package com.project.Projectwo.Service;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.Projectwo.Entity.Lecture;
import com.project.Projectwo.Entity.Member;
import com.project.Projectwo.Repository.LectureRepository;
import com.project.Projectwo.Repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MemberService {

	private final MemberRepository memberRepository;
	private final PasswordEncoder passwordEncoder;
	private final LectureRepository lectureRepository;
	// by 안준언, 회원가입
	public void create(String member_id, String member_pw,
								String email, String name, int age,
								String address, String tel) {
		
		Member member = new Member();
		member.setMember_id(member_id);
		member.setMember_pw(passwordEncoder.encode(member_pw));
		member.setEmail(email);
		member.setName(name);
		member.setAge(age);
		member.setAddress(address);
		member.setTel(tel);
		member.setAuth(1);
		this.memberRepository.save(member);
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

	
	
	//학생
	//박은영
	//내 강의 목록
	public List<Lecture> getLectureList(){ //HttpSession session
		
		List<Lecture> lectureList = lectureRepository.findAll();
		
		return lectureList;
	}
	
}
