package com.project.Projectwo.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

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
	
	// by 안준언, 유저 생성 (test 코드)
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
	
	public Member getMember(Integer memberId) {
		Optional<Member> _member = this.memberRepository.findById(memberId);
		Member member = _member.get();
		
		return member;
	}
	
	// by 안준언, 유저(teacher) 생성
	public void createTeacher(String name, String birth_date, String tel,
								String email, String address) {
		Member member = new Member();
		member.setRole("teacher");
		member.setIdentity(name+tel);
		member.setPassword(passwordEncoder.encode(name+tel));
		member.setName(name);
		member.setBirth_date(LocalDate.parse(birth_date));
		member.setTel(tel);
		member.setEmail(email);
		member.setAddress(address);
		
		this.memberRepository.save(member);
	}
	
	// by 안준언, 유저(student) 생성
	public void createStudent(String name, String birth_date, String tel,
									String email, String address) {
			Member member = new Member();
			member.setRole("student");
			member.setIdentity(name+tel);
			member.setPassword(passwordEncoder.encode(name+tel));
			member.setName(name);
			member.setBirth_date(LocalDate.parse(birth_date));
			member.setTel(tel);
			member.setEmail(email);
			member.setAddress(address);
			
			this.memberRepository.save(member);
	}
	
	// by 안준언, 유저(teacher) 수정
	public void modifyMember(String memberId, String name, String birth_date, String tel,
								String email, String address) {
		Integer _memberId = Integer.parseInt(memberId);
		
		Optional<Member> _member = this.memberRepository.findById(_memberId);

		Member member = _member.get();
		member.setName(name);
		member.setBirth_date(LocalDate.parse(birth_date));
		member.setTel(tel);
		member.setEmail(email);
		member.setAddress(address);
		
		this.memberRepository.save(member);
		
	}
	
	// by 안준언, 유저(teacher&student) 삭제
	public void deleteMember(Integer memberId) {
		Optional<Member> _member = this.memberRepository.findById(memberId);
		Member member = _member.get();
		this.memberRepository.delete(member);
	}
		
		
	
	// by 안준언, 새 강의 생성
	public void createCourseAndTeacher(String title, String mon, String tue, String wed,
								String thu, String fri, String sat, String sun,
								String startDate, String endDate, String startTime,
								String endTime, String roomName, String teacherName) {
		Optional<Room> _room = this.roomRepository.findByName(roomName);
		Room room = _room.get();
		
		Course course = new Course();
		course.setTitle(title);
		course.setDescription("please write description");
		course.setMon(Boolean.parseBoolean(mon));
		course.setTue(Boolean.parseBoolean(tue));
		course.setWed(Boolean.parseBoolean(wed));
		course.setThu(Boolean.parseBoolean(thu));
		course.setFri(Boolean.parseBoolean(fri));
		course.setSat(Boolean.parseBoolean(sat));
		course.setSun(Boolean.parseBoolean(sun));
		course.setStartDate(LocalDate.parse(startDate));
		course.setEndDate(LocalDate.parse(endDate));
		course.setStartTime(LocalTime.parse(startTime));
		course.setEndTime(LocalTime.parse(endTime));
		course.setRoom(room);
		
		this.courseRepository.save(course);
		
		Optional<Member> _member = this.memberRepository.findByName(teacherName);
		Member member = _member.get();
		
		Teacher teacher = new Teacher();
		teacher.setCourse(course);
		teacher.setTeacher(member);
		
		this.teacherRepository.save(teacher);
		
	}
	
	public void modifyCourseAndTeacher(String id, String title, String mon, String tue, String wed,
			String thu, String fri, String sat, String sun,
			String startDate, String endDate, String startTime,
			String endTime, String roomName, String teacherName) {
		Optional<Room> _room = this.roomRepository.findByName(roomName);
		Room room = _room.get();
		
		Integer courseId = (Integer.parseInt(id));
		Optional<Course> _course = this.courseRepository.findById(courseId);
		Course course = _course.get();
		course.setTitle(title);
		course.setMon(Boolean.parseBoolean(mon));
		course.setTue(Boolean.parseBoolean(tue));
		course.setWed(Boolean.parseBoolean(wed));
		course.setThu(Boolean.parseBoolean(thu));
		course.setFri(Boolean.parseBoolean(fri));
		course.setSat(Boolean.parseBoolean(sat));
		course.setSun(Boolean.parseBoolean(sun));
		course.setStartDate(LocalDate.parse(startDate));
		course.setEndDate(LocalDate.parse(endDate));
		course.setStartTime(LocalTime.parse(startTime));
		course.setEndTime(LocalTime.parse(endTime));
		course.setRoom(room);
		
		this.courseRepository.save(course);
		
		Optional<Member> _member = this.memberRepository.findByName(teacherName);
		Member member = _member.get();
		
		List<Teacher> _teacher = this.teacherRepository.findByCourse(course);
		Teacher teacher = _teacher.get(0);
		teacher.setTeacher(member);
		this.teacherRepository.save(teacher);
		
	}
	// by 안준언, 강의삭제
	public void deleteCourse(Integer courseId) {
		Optional<Course> _course = this.courseRepository.findById(courseId);
		Course course = _course.get();
		this.courseRepository.delete(course);
	}
	
	// by 안준언, 전체 강의 리스트 반환
	public List<Course> getAllCourse(){
		
		List<Course> courseList = this.courseRepository.findAll();
		
		return courseList;
	}
	
	// by 안준언, 한 수업의 담당 강사 리스트 반환
	public List<Teacher> getTeacherList(Course course) {
		List<Teacher> teacherList = this.teacherRepository.findByCourse(course);
		return teacherList;
	}
	
	// by 안준언, 한 강사의 담당 수업 리스트 반환
	public List<Teacher> getAllClassOfTeacher(Member member) {
		List<Teacher> classList = this.teacherRepository.findByTeacher(member);
		return classList;
	}
	
	// by 안준언, 특정 강사의 특정 수업 반환
	public Teacher getTeacher(Course course, Member member) {
		Teacher teacher = this.teacherRepository.findByCourseAndTeacher(course, member);
		return teacher;
	}
	
	// by 안준언, 한 수업의 수강 학생 리스트 반환
	public List<Student> getStudentList(Course course) {
		List<Student> studentList = this.studentRepository.findByCourse(course);
		return studentList;
	}
	
	// by 안준언, 한 학생의 수강 수업 리스트 반환
	public List<Student> getAllClassOfStudent(Member member) {
		List<Student> classList = this.studentRepository.findByStudent(member);
		return classList;
	}
	
	// by 안준언, 특정 수강생의 특정 수업 반환
	public Student getStudent(Course course, Member member) {
		Student student = this.studentRepository.findByCourseAndStudent(course, member);
		return student;
	}
	
	// by 안준언, pk(id)로 해당 수업 반환
	public Course getCourse(Integer courseId) {
		Optional<Course> _course = this.courseRepository.findById(courseId);
		if(_course.isEmpty()) {
			return null;
		}
		Course course = _course.get();
		return course;
	}
	
	public Course getCourse(String title) {
		Optional<Course> _course = this.courseRepository.findByTitle(title);
		Course course = _course.get();
		
		return course;
	}
	
	// by 안준언, 전체 강의실 리스트 반환
	public List<Room> getAllRoom(){
		List<Room> RoomList = this.roomRepository.findAll();
		return RoomList;
	}
	
	// by 안준언, name 필드로 특정 강의실 반환
	public Room getRoom(String name) {
		Optional<Room> _room = this.roomRepository.findByName(name);
		if(_room.isEmpty()) {
			return null;
		}
		Room room = _room.get();
		return room;
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
	
	// by 안준언, 수강 등록 (수강정보 생성) + 출결 정보 함께 생성
	public void addStudent(Member student, Course course) {
		Student classMember = new Student();
		classMember.setStudent(student);
		classMember.setCourse(course);
		
		this.studentRepository.save(classMember);
		
		for(LocalDate date = course.getStartDate(); date.isBefore(course.getEndDate().plusDays(1)); date = date.plusDays(1)) {

			Attendance attendance = new Attendance();
			attendance.setStudent(classMember);
			attendance.setToday(date);
			attendance.setStatus("미출결");
			
			DayOfWeek dayOfWeek = date.getDayOfWeek();
			
			switch(dayOfWeek) {
			case SUNDAY :
				if(course.isSun()) {
					this.attendanceRepository.save(attendance);
				}
				break;
			case MONDAY :
				if(course.isMon()) {
					this.attendanceRepository.save(attendance);
				}
				break;
			case TUESDAY :
				if(course.isTue()) {
					this.attendanceRepository.save(attendance);
				}
				break;
			case WEDNESDAY :
				if(course.isWed()) {
					this.attendanceRepository.save(attendance);
				}
				break;
			case THURSDAY :
				if(course.isThu()) {
					this.attendanceRepository.save(attendance);
				}
				break;
			case FRIDAY :
				if(course.isFri()) {
					this.attendanceRepository.save(attendance);
				}
				break;
			case SATURDAY :
				if(course.isSat()) {
					this.attendanceRepository.save(attendance);
				}
				break;
			}
		}
	}
		
	// by 안준언, 강사 등록
	public void addTeacher(Member teacher_, Course course) {
		Teacher teacher = new Teacher();
		teacher.setTeacher(teacher_);
		teacher.setCourse(course);
		
		this.teacherRepository.save(teacher);
	}
	
	// by 안준언, 특정 학생, 특정 수업의 오늘 출결 정보 반환
	public Attendance getTodayAttendance(Student student) {
		LocalDate today = LocalDate.now();
		Optional<Attendance> _todayAttendace = this.attendanceRepository.findByStudentAndToday(student, today);
		if(_todayAttendace.isPresent()) {
			Attendance todayAttendance = _todayAttendace.get();
			return todayAttendance;
		} else {
			return null;
		}
	}
	
	// by 장유란, 전체 강의 반환
	public List<AcademyNotice> getAllAcademyNotice(){			
		List<AcademyNotice> AcademyNoticeList = this.academyNoticeRepository.findAll();
		return AcademyNoticeList;
	}
	
	// by 안준언, 특정 학생, 특정 수업의 오늘 기준 이전 6개 출결정보 반환
	public List<Attendance> getBefore6Attendance(Student student) {
		LocalDate today = LocalDate.now();
		Optional<Attendance> _todayAttendace = this.attendanceRepository.findByStudentAndToday(student, today);
		if(_todayAttendace.isPresent()) {
			
			Attendance todayAttendance = _todayAttendace.get();
			
			List<Attendance> attList = student.getAttendanceList();
			List<Attendance> sixAttList = new ArrayList<Attendance>();
			
			int cnt = 0;
			for(int i = attList.indexOf(todayAttendance)-1; 0<=i; i--) {
				Attendance att = attList.get(i);
				sixAttList.add(att);
				cnt++;
				if(cnt == 6) {
					break;
				}
			}
			sixAttList.sort((attendance1, attendance2) -> 
				attendance2.getToday().compareTo(attendance1.getToday()));
			
			return sixAttList;
			
		} else {
			Attendance at = new Attendance();
			at.setToday(today);
			
			List<Attendance> attList = student.getAttendanceList();
			List<Attendance> sixAttList = new ArrayList<Attendance>();
			attList.add(at);
			attList.sort((attendance1, attendance2) -> 
				attendance1.getToday().compareTo(attendance2.getToday()));
			
			int cnt = 0;
			
			for(int i=attList.indexOf(at)-1; 0<=i; i--) {
				Attendance att = attList.get(i);
				sixAttList.add(att);
				cnt++;
				if(cnt == 6) {
					break;
				}
			}
			sixAttList.sort((attendance1, attendance2) -> 
				attendance2.getToday().compareTo(attendance1.getToday()));
			return sixAttList;
		}
	}
	
	// by 안준언, 출석 반환
	public Attendance getAttendance(Integer attendanceId) {
		Optional<Attendance> _attendance = this.attendanceRepository.findById(attendanceId);
		if(_attendance.isPresent()) {
			Attendance attendance = _attendance.get();
			return attendance;
		}
		return null;
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
