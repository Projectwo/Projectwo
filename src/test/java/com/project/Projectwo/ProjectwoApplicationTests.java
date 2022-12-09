package com.project.Projectwo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.project.Projectwo.Repository.AttendanceRepository;
import com.project.Projectwo.Repository.CourseRepository;
import com.project.Projectwo.Repository.MemberRepository;
import com.project.Projectwo.Repository.RoomRepository;
import com.project.Projectwo.Repository.StudentRepository;
import com.project.Projectwo.Service.AcademyService;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@SpringBootTest
class ProjectwoApplicationTests {
	
	@Autowired
	private AcademyService academyService;
	
	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	private CourseRepository courseRepository;
	
	@Autowired
	private RoomRepository roomRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired 
	private AttendanceRepository attendanceRepository;
	
	@Autowired
	private StudentRepository studentRepository;
	
//	@Test
//	public void PeriodicNotificationJson() throws JSONException {
//			
//			LocalDate localDate = LocalDate.now();
//			
//			String sampleData[] = {"device token value 1", "device token value 2", "device token value 3"};
//			
//			JSONObject body = new JSONObject();
//			
//			List<String> tokenList = new ArrayList<String>();
//			
//			for(int i=0; i<sampleData.length; i++) {
//				tokenList.add(sampleData[i]);
//				
//			}
//			
//			JSONArray jsonArray = new JSONArray();
//			
//			for(int i=0; i<tokenList.size(); i++) {
//				jsonArray.put(tokenList.get(i));
//	
//				System.out.println("####jsonArray[" + i + "]= " + jsonArray.getString(i));
//			}
//			
//			body.put("registration_ids", jsonArray);
//			
//			JSONObject notification = new JSONObject();
//			notification.put("title", "ProjectApp");
//			notification.put("body", localDate.toString() + " 퇴실 미완료");
//			
//			body.put("notification", notification);
//			
//			System.out.println("####JSONObject body= " + body.toString());
//
//		}
//	@Test
//	void test() {
//
//		Optional<Course> oCourse = courseRepository.findById(1);
//		Course course = oCourse.get();
//
//		int attendInt = 0;
//		int notAttendInt = 0;
//		List<Student> studentList = studentRepository.findByCourse(course);
//		
//		
//		for(int i=0; i<studentList.size(); i++) {
//			Student student = studentList.get(i);
//			
//			try {
//				Optional<Attendance> oAttendance = attendanceRepository.findByStudentAndToday(student, LocalDate.of(2022, 12, 05));
//			}catch(Exception e) {
//				log.info("####Exception e=" + e.toString());
//			}
//			
////			Attendance attendance = oAttendance.get();
////			
////			if(attendance.getStatus().equals("입실") || attendance.getStatus().equals("지각")) {
////				attendInt++;
////			}else {
////				notAttendInt++;
////			}
////			
//		}
//
//		log.info("####attendInt=" + attendInt);
//		log.info("####notAttendInt=" + notAttendInt);
//		
//	}

//	// 계정 등록
//	@Test
//	void createMember() {
//		Member member1 = new Member();
//		member1.setIdentity("aaa");
//		member1.setPassword(passwordEncoder.encode("aaa"));
//		member1.setName("aaa");
//		member1.setEmail("aaa@aaa");
//		member1.setBirth_date(LocalDate.of(1991, 01, 01));
//		member1.setAddress("aaa");
//		member1.setTel("aaa");
//		member1.setRole("student");
//		
//		this.memberRepository.save(member1);
//		
//		Member member2 = new Member();
//		member2.setIdentity("bbb");
//		member2.setPassword(passwordEncoder.encode("bbb"));
//		member2.setName("bbb");
//		member2.setEmail("bbb@bbb");
//		member2.setBirth_date(LocalDate.of(1991, 01, 01));
//		member2.setAddress("bbb");
//		member2.setTel("bbb");
//		member2.setRole("student");
//		
//		this.memberRepository.save(member2);
//		
//		Member member3 = new Member();
//		member3.setIdentity("ccc");
//		member3.setPassword(passwordEncoder.encode("ccc"));
//		member3.setName("ccc");
//		member3.setEmail("ccc@ccc");
//		member3.setBirth_date(LocalDate.of(1991, 01, 01));
//		member3.setAddress("ccc");
//		member3.setTel("ccc");
//		member3.setRole("teacher");
//		
//		this.memberRepository.save(member3);
//		
//		Member member4 = new Member();
//		member4.setIdentity("ddd");
//		member4.setPassword(passwordEncoder.encode("ddd"));
//		member4.setName("ddd");
//		member4.setEmail("ddd@ddd");
//		member4.setBirth_date(LocalDate.of(1991, 01, 01));
//		member4.setAddress("ddd");
//		member4.setTel("ddd");
//		member4.setRole("teacher");
//		
//		this.memberRepository.save(member4);
//		
//		Member member5 = new Member();
//		member5.setIdentity("admin");
//		member5.setPassword(passwordEncoder.encode("admin"));
//		member5.setName("admin");
//		member5.setEmail("admin@admin");
//		member5.setBirth_date(LocalDate.of(1991, 01, 01));
//		member5.setAddress("admin");
//		member5.setTel("admin");
//		member5.setRole("admin");
//		
//		this.memberRepository.save(member5);
//	}
//	// 강의실 생성
//	@Test
//	void createRoom() {
//		Room room = new Room();
//		room.setName("427호");
//		room.setMaxSeat(50);
//		
//		this.roomRepository.save(room);
//	}
//	// 강의 개설
//	@Test
//	void createCourse() {
//		Optional<Room> _room = this.roomRepository.findById(1);
//		Room room = _room.get();
//		
//		Course course = new Course();
//		course.setTitle("java");
//		course.setDescription("study basic java");
//		course.setStartDate(LocalDate.of(2022, 11, 01));
//		course.setEndDate(LocalDate.of(2022, 11, 30));
//		course.setStartTime(LocalTime.of(10, 00));
//		course.setEndTime(LocalTime.of(11, 00));
//		course.setSat(true);
//		course.setSun(true);
//		course.setRoom(room);
//		
//		this.courseRepository.save(course);
//		
//		Course course1 = new Course();
//		course1.setTitle("data");
//		course1.setDescription("study database");
//		course1.setStartDate(LocalDate.of(2022, 10, 01));
//		course1.setEndDate(LocalDate.of(2022, 10, 30));
//		course1.setStartTime(LocalTime.of(10, 00));
//		course1.setEndTime(LocalTime.of(11, 00));
//		course1.setSat(true);
//		course1.setSun(true);
//		course1.setRoom(room);
//		
//		this.courseRepository.save(course1);
//	}
//	// 강사, 학생 등록
//	@Test
//	void createStudentAndTeacher() {
//		Optional<Course> cs1 = this.courseRepository.findById(1);
//		Course course1 = cs1.get();
//		
//		Optional<Course> cs2 = this.courseRepository.findById(2);
//		Course course2 = cs2.get();
//		
//		Optional<Member> mb1 = this.memberRepository.findByIdentity("aaa");
//		Member member1 = mb1.get();
//		
//		Optional<Member> mb2 = this.memberRepository.findByIdentity("bbb");
//		Member member2 = mb2.get();
//
//		Optional<Member> mb3 = this.memberRepository.findByIdentity("ccc");
//		Member member3 = mb3.get();
//		
//		Optional<Member> mb4 = this.memberRepository.findByIdentity("ddd");
//		Member member4 = mb4.get();
//		
//		this.academyService.createStudent(member1, course1);
//		this.academyService.createStudent(member1, course2);
//		this.academyService.createStudent(member2, course1);
//		this.academyService.createStudent(member2, course2);
//		
//		this.academyService.createTeacher(member3, course1);
//		this.academyService.createTeacher(member3, course2);
//		this.academyService.createTeacher(member4, course1);
//		this.academyService.createTeacher(member4, course2);
//		
//	}
//	// 수업,강의 리스트 확인
//	@Transactional
//	@Test
//	void checkclassList() {
//		Optional<Member> mb1 = this.memberRepository.findById(1);
//		Member member1 = mb1.get();
//		List<Student> studentClassList = member1.getStudentClassList();
//		assertEquals(studentClassList.size(), 2);
//		
//		Optional<Member> mb3 = this.memberRepository.findById(3);
//		Member member3 = mb3.get();
//		List<Teacher> teacherClassList = member3.getTeacherClassList();
//		assertEquals(teacherClassList.size(), 2);
//	}
}
