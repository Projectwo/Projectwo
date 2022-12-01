package com.project.Projectwo.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.Projectwo.Entity.AcademyNotice;
import com.project.Projectwo.Entity.AcademyNoticeCheck;
import com.project.Projectwo.Entity.Attendance;
import com.project.Projectwo.Entity.Student;
import com.project.Projectwo.Entity.ClassNotice;
import com.project.Projectwo.Entity.ClassNoticeCheck;
import com.project.Projectwo.Entity.Course;
import com.project.Projectwo.Entity.Member;
import com.project.Projectwo.Entity.Room;
import com.project.Projectwo.Entity.Teacher;
import com.project.Projectwo.Repository.AcademyNoticeCheckRepository;
import com.project.Projectwo.Repository.AcademyNoticeRepository;
import com.project.Projectwo.Repository.AttendanceRepository;
import com.project.Projectwo.Repository.StudentRepository;
import com.project.Projectwo.Repository.ClassNoticeCheckRepository;
import com.project.Projectwo.Repository.ClassNoticeRepository;
import com.project.Projectwo.Repository.CourseRepository;
import com.project.Projectwo.Repository.MemberRepository;
import com.project.Projectwo.Repository.RoomRepository;
import com.project.Projectwo.Repository.TeacherRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AcademyService {

	private final AcademyNoticeRepository academyNoticeRepository;
	private final AcademyNoticeCheckRepository academyNoticeCheckRepository;
	private final AttendanceRepository attendanceRepository;
	private final ClassNoticeRepository classNoticeRepository;
	private final ClassNoticeCheckRepository classNoticeCheckRepository;
	private final CourseRepository courseRepository;
	private final MemberRepository memberRepository;
	private final RoomRepository roomRepository;
	private final StudentRepository studentRepository;
	private final TeacherRepository teacherRepository;
	
	private final PasswordEncoder passwordEncoder;

	// by 안준언, 전체 강의 리스트 반환
	public List<Course> getAllCourse(){
		
		List<Course> courseList = this.courseRepository.findAll();
		
		return courseList;
	}
	
	// by 안준언, 수업 담당 교수 리스트 반환
	public List<Teacher> getTeacherList(Course course){
		List<Teacher> teacherList = this.teacherRepository.findByCourse(course);
		return teacherList;
	}
	
	public List<Student> getStudentList(Course course) {
		List<Student> studentList = this.studentRepository.findByCourse(course);
		return studentList;
	}

	// by 안준언, 유저 생성
	public void createMember(String identity, String password, String email,
								String name, LocalDate birth_date, String address, String tel) {
		Member member = new Member();
		member.setIdentity(identity);
		member.setPassword(passwordEncoder.encode(password));
		member.setEmail(email);
		member.setName(name);
		member.setBirth_date(birth_date);
		member.setAddress(address);
		member.setTel(tel);
		
		this.memberRepository.save(member);
	}

	// 안준언, 수업 반환
	public Course getCourse(int courseId) {
		Optional<Course> _course = this.courseRepository.findById(courseId);
		if(_course.isEmpty()) {
			return null;
		}
		Course course = _course.get();
		return course;
	}
	
//	// by 안준언, 전체 공지 생성
//	public void createAcademyNotice(String title, String Content) {
//		AcademyNotice academyNotice = new AcademyNotice();
//		academyNotice.setTitle(title);
//		academyNotice.setContent(Content);
//		academyNotice.setCreateDate(LocalDateTime.now());
//		
//		this.academyNoticeRepository.save(academyNotice);
//	}
//	
//	// by 안준언, 강의실 생성
//	public void createRoom(String name, int MaxSeat) {
//		Room room = new Room();
//		room.setName(name);
//		room.setMaxSeat(MaxSeat);
//		
//		this.roomRepository.save(room);
//	}
//	
//	// by 안준언, 학원 강의 생성
//	public void createCourse(String title, String description,
//								LocalDate startDate, LocalDate endDate) {
//		Course lecture = new Course();
//		lecture.setTitle(title);
//		lecture.setDescription(description);
//		lecture.setStartDate(startDate);
//		lecture.setEndDate(endDate);
//		
//		this.courseRepository.save(lecture);
//	}
//	
	// by 안준언, 수강 등록 (수강정보 생성)
	public void createStudent(Member student, Course course) {
		Student classMember = new Student();
		classMember.setStudent(student);
		classMember.setCourse(course);
		
		this.studentRepository.save(classMember);
	}

	// by 안준언, 강사 등록
	public void createTeacher(Member teacher_, Course course) {
		Teacher teacher = new Teacher();
		teacher.setTeacher(teacher_);
		teacher.setCourse(course);
		
		this.teacherRepository.save(teacher);
	}

//	// by 안준언, 강의 공지사항 읽음 여부 생성
//	public void createClassNoticeCheck(Student student, ClassNotice classNotice) {
//		ClassNoticeCheck classNoticeCheck = new ClassNoticeCheck();
//		classNoticeCheck.setStudent(student);
//		classNoticeCheck.setClassNotice(classNotice);
//		classNoticeCheck.setChecked(false);
//		
//		this.classNoticeCheckRepository.save(classNoticeCheck);
//	}
//	
//	// by 안준언, 강의 공지사항 생성
//	public void createClassNotice(String title, String content, LocalDateTime createDate,
//									LocalDateTime modifyDate, Course course) {
//		ClassNotice classNotice = new ClassNotice();
//		classNotice.setTitle(title);
//		classNotice.setContent(content);
//		classNotice.setCreateDate(createDate);
//		classNotice.setModifyDate(modifyDate);
//		classNotice.setCourse(course);
//		
//		this.classNoticeRepository.save(classNotice);
//	}
//	
//	// by 안준언, 출석 정보 생성
//	public void createAttendance(LocalTime inTime, LocalTime outTime, String status,
//									Student student) {
//		Attendance attendance = new Attendance();
//		attendance.setInTime(inTime);
//		attendance.setOutTime(outTime);
//		attendance.setStatus(status);
//		attendance.setStudent(student);
//		
//		this.attendanceRepository.save(attendance);
//	}
//	
//	// by 안준언, 전체 공지 읽음 여부 생성
//	public void createAcademyNoticeCheck(Member member, AcademyNotice academyNotice) {
//		AcademyNoticeCheck academyNoticeCheck = new AcademyNoticeCheck();
//		academyNoticeCheck.setMember(member);
//		academyNoticeCheck.setAcademyNotice(academyNotice);
//		academyNoticeCheck.setChecked(false);
//		
//		this.academyNoticeCheckRepository.save(academyNoticeCheck);
//	}
	
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
