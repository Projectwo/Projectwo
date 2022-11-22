package com.project.Projectwo.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.Projectwo.Entity.Lecture;
import com.project.Projectwo.Repository.LectureRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MemberService {
	
	private final LectureRepository lectureRepository;
	
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
