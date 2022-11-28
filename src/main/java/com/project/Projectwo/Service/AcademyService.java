package com.project.Projectwo.Service;

<<<<<<< HEAD
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.project.Projectwo.Entity.AcademyNotice;
import com.project.Projectwo.Repository.AcademyNoticeRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AcademyService {

	private final AcademyNoticeRepository academyNoticeRepository;
	
	// by 장유란, notice 목록 Controller에 전달
	public List<AcademyNotice> getList() {
		List<AcademyNotice> noticeList = academyNoticeRepository.findAll();
		return noticeList;
	}
	
    public AcademyNotice getNotice(Integer id) {  
        Optional<AcademyNotice> notice = this.academyNoticeRepository.findById(id);
        if (notice.isPresent()) {
            return notice.get();
        } else {
            throw new DataNotFoundException("공지를 찾을 수 없습니다.");
        }
    }
	
    // by 장유란, 새 Notice 생성
    public AcademyNotice create(String title, String content) {
		AcademyNotice notice = new AcademyNotice();
		notice.setTitle(title);
		notice.setCreateDate(LocalDateTime.now());
		notice.setContent(content);
		academyNoticeRepository.save(notice);
		
		return notice;
    }
	
	/*
	관리자
	학원정보 수정
	전체 공지 작성/수정
	강의실 CRUD
	강의 리스트 CRUD
	강의 CRUD
	강의 시간표 CRUD
	선생님 관리
	수강생 관리
	등 ?
	 */
=======
import org.springframework.stereotype.Service;

@Service
public class AcademyService {

>>>>>>> main
}
