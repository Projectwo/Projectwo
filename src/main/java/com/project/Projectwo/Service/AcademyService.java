package com.project.Projectwo.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.project.Projectwo.Entity.AcademyNotice;
import com.project.Projectwo.Entity.AcademyNoticeCheck;
import com.project.Projectwo.Entity.Attendance;
import com.project.Projectwo.Entity.ClassMember;
import com.project.Projectwo.Entity.ClassNotice;
import com.project.Projectwo.Entity.ClassNoticeCheck;
import com.project.Projectwo.Entity.ClassTeacher;
import com.project.Projectwo.Entity.Lecture;
import com.project.Projectwo.Entity.Member;
import com.project.Projectwo.Entity.Room;
import com.project.Projectwo.Entity.Schedule;
import com.project.Projectwo.Entity.Teacher;
import com.project.Projectwo.Repository.AcademyNoticeCheckRepository;
import com.project.Projectwo.Repository.AcademyNoticeRepository;
import com.project.Projectwo.Repository.AttendanceRepository;
import com.project.Projectwo.Repository.ClassMemberRepository;
import com.project.Projectwo.Repository.ClassNoticeCheckRepository;
import com.project.Projectwo.Repository.ClassNoticeRepository;
import com.project.Projectwo.Repository.ClassTeacherRepository;
import com.project.Projectwo.Repository.LectureRepository;
import com.project.Projectwo.Repository.MemberRepository;
import com.project.Projectwo.Repository.RoomRepository;
import com.project.Projectwo.Repository.ScheduleRepository;
import com.project.Projectwo.Repository.TeacherRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AcademyService {

	private final MemberRepository memberRepository;
	private final AcademyNoticeRepository academyNoticeRepository;
	private final RoomRepository roomRepository;
	private final LectureRepository lectureRepository;
	private final ClassMemberRepository classMemberRepository;
	private final ScheduleRepository scheduleRepository;
	private final TeacherRepository teacherRepository;
	private final ClassTeacherRepository classTeacherRepository;
	private final ClassNoticeCheckRepository classNoticeCheckRepository;
	private final ClassNoticeRepository classNoticeRepository;
	private final AttendanceRepository attendanceRepository;
	private final AcademyNoticeCheckRepository academyNoticeCheckRepository;
	
	// by 안준언, 유저 생성
	public void createMember(String member_id, String member_pw, String email,
								String name, int age, String address, String tel) {
		Member member = new Member();
		member.setMember_id(member_id);
		member.setMember_pw(member_pw);
		member.setEmail(email);
		member.setName(name);
		member.setAge(age);
		member.setAddress(address);
		member.setTel(tel);
		
		this.memberRepository.save(member);
	}
	
	// by 안준언, 전체 공지 생성
	public void createAcademyNotice(String title, String Content) {
		AcademyNotice academyNotice = new AcademyNotice();
		academyNotice.setTitle(title);
		academyNotice.setContent(Content);
		academyNotice.setCreateDate(LocalDateTime.now());
		
		this.academyNoticeRepository.save(academyNotice);
	}
	
	// by 안준언, 강의실 생성
	public void createRoom(String roomName, int MaxSeat) {
		Room room = new Room();
		room.setRoomName(roomName);
		room.setMaxSeat(MaxSeat);
		
		this.roomRepository.save(room);
	}
	
	// by 안준언, 학원 강의(반) 생성
	public void createLecture(String lectureName, String LectureDesc,
								LocalDate startDate, LocalDate endDate) {
		Lecture lecture = new Lecture();
		lecture.setLectureName(lectureName);
		lecture.setLectureDesc(LectureDesc);
		lecture.setStartDate(startDate);
		lecture.setEndDate(endDate);
		
		this.lectureRepository.save(lecture);
	}
	
	// by 안준언, 수강 등록 (수강정보 생성)
	public void createClassMember(Lecture lecture, Member member) {
		ClassMember classMember = new ClassMember();
		classMember.setLecture(lecture);
		classMember.setMember(member);
		
		this.classMemberRepository.save(classMember);
	}
	
	// by 안준언, 스케쥴 생성
	public void createSchedule(String dayOfWeek, LocalDateTime startTime,
								LocalDateTime endTime, Lecture lecture,
								Room room, ClassTeacher classTeacher) {
		Schedule schedule = new Schedule();
		schedule.setDayOfWeek(dayOfWeek);
		schedule.setStartTime(startTime);
		schedule.setEndTime(endTime);
		schedule.setLecture(lecture);
		schedule.setRoom(room);
		schedule.setClassTeacher(classTeacher);
		
		this.scheduleRepository.save(schedule);
	}
	
	// by 안준언, 강사 등록
	public void createTeacher(Member member, String career) {
		Teacher teacher = new Teacher();
		teacher.setMember(member);
		teacher.setCareer(career);
		
		this.teacherRepository.save(teacher);
	}
	
	// by 안준언, class Teacher 등록
	public void createClassTeacher(Teacher teacher, Lecture lecture) {
		ClassTeacher classTeacher = new ClassTeacher();
		classTeacher.setTeacher(teacher);
		classTeacher.setLecture(lecture);
		
		this.classTeacherRepository.save(classTeacher);
	}
	
	// by 안준언, 강의 공지사항 읽음 여부 생성
	public void createClassNoticeCheck(ClassMember classMember, ClassNotice classNotice) {
		ClassNoticeCheck classNoticeCheck = new ClassNoticeCheck();
		classNoticeCheck.setClassMember(classMember);
		classNoticeCheck.setClassNotice(classNotice);
		classNoticeCheck.setChecked(false);
		
		this.classNoticeCheckRepository.save(classNoticeCheck);
	}
	
	// by 안준언, 강의 공지사항 생성
	public void createClassNotice(String title, String content, LocalDateTime createDate,
									LocalDateTime modifyDate, Lecture lecture, ClassTeacher classTeacher) {
		ClassNotice classNotice = new ClassNotice();
		classNotice.setTitle(title);
		classNotice.setContent(content);
		classNotice.setCreateDate(createDate);
		classNotice.setModifyDate(modifyDate);
		classNotice.setLecture(lecture);
		classNotice.setClassTeacher(classTeacher);
		
		this.classNoticeRepository.save(classNotice);
	}
	
	// by 안준언, 출석 정보 생성
	public void createAttendance(LocalDateTime inTime, LocalDateTime outTime, String status,
									ClassMember classMember, Schedule schedule) {
		Attendance attendance = new Attendance();
		attendance.setInTime(inTime);
		attendance.setOutTime(outTime);
		attendance.setStatus(status);
		attendance.setClassMember(classMember);
		attendance.setSchedule(schedule);
		
		this.attendanceRepository.save(attendance);
	}
	
	// by 안준언, 전체 공지 읽음 여부 생성
	public void createAcademyNoticeCheck(Member member, AcademyNotice academyNotice) {
		AcademyNoticeCheck academyNoticeCheck = new AcademyNoticeCheck();
		academyNoticeCheck.setMember(member);
		academyNoticeCheck.setAcademyNotice(academyNotice);
		academyNoticeCheck.setChecked(false);
		
		this.academyNoticeCheckRepository.save(academyNoticeCheck);
	}
	
	/*
	관리자
	학원정보 수정
	전체 공지 작성/수정
	강의실 CRUD
	강의 CRUD
	강의 시간표 CRUD
	선생님 관리
	수강생 관리
	등 ?
	 */
}
