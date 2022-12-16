package com.project.Projectwo.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoField;
import java.util.List;
import java.util.Optional;
import java.util.Timer;
import java.util.TimerTask;

import org.springframework.stereotype.Service;

import com.google.firebase.messaging.FirebaseMessagingException;
import com.project.Projectwo.Entity.Attendance;
import com.project.Projectwo.Entity.Course;
import com.project.Projectwo.Entity.Member;
import com.project.Projectwo.Entity.Student;
import com.project.Projectwo.Repository.AttendanceRepository;
import com.project.Projectwo.Repository.CourseRepository;
import com.project.Projectwo.Repository.StudentRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class AttendanceService {
	
	private final AttendanceRepository attendanceRepository;
	private final CourseRepository courseRepository;
	private final StudentRepository studentRepository;
	private final FCMService fcmService;
<<<<<<< HEAD
	
	//by 박은영
	//선생님 권한으로 입실자 수 조회
	public int[] getAttendanceNum(Course course, LocalDate localDate) {

		int[] attendTF = new int[2];
		
		int attendInt = 0;
		int notAttendInt = 0;	
		
		List<Student> studentList = studentRepository.findByCourse(course);
		
		for(int i=0; i<studentList.size(); i++) {
			Student student = studentList.get(i);

			Optional<Attendance> oAttendance = attendanceRepository.findByStudentAndToday(student, localDate);	

			if(oAttendance.isPresent()) {
				Attendance attendance = oAttendance.get();	
				
				if(attendance.getStatus().equals("입실") || attendance.getStatus().equals("지각")) {
					attendInt++;
				}else {
					notAttendInt++;
				}
			}
			
		}
		
		attendTF[0] = attendInt;
		attendTF[1] = notAttendInt;

		return attendTF;
	}
=======
	private final AcademyService academyService;
	
//	//by 박은영
//	//선생님 권한으로 입실자 수 조회
//	public int[] getAttendanceNum(Course course, LocalDate localDate) {
//
//		int[] attendTF = new int[2];
//		
//		int attendInt = 0;
//		int notAttendInt = 0;	
//		
//		List<Student> studentList = studentRepository.findByCourse(course);
//		
//		for(int i=0; i<studentList.size(); i++) {
//			Student student = studentList.get(i);
//
//			Optional<Attendance> oAttendance = attendanceRepository.findByStudentAndToday(student, localDate);	
//
//			if(oAttendance.isPresent()) {
//				Attendance attendance = oAttendance.get();	
//				
//				if(attendance.getStatus().equals("입실") || attendance.getStatus().equals("지각")) {
//					attendInt++;
//				}else {
//					notAttendInt++;
//				}
//			}
//			
//		}
//		
//		attendTF[0] = attendInt;
//		attendTF[1] = notAttendInt;
//
//		return attendTF;
//	}
>>>>>>> yuran

	
	//by 박은영
	//'당일' 학생 출결 정보 가져오기
<<<<<<< HEAD
	public Attendance getTodayAttendance(Student student, LocalDate localDate) {
		Optional<Attendance> oAttendance = attendanceRepository.findByStudentAndToday(student, localDate);
		
		if(oAttendance.isEmpty()) {
			return null;
		}
		
		Attendance todayAttendance = oAttendance.get();
		return todayAttendance;
	}

	//입실
	public void regAttendance(Course course, Student student, LocalDate localDate) {
		Attendance attendance = new Attendance();
=======
//	public Attendance getTodayAttendance(Student student, LocalDate localDate) {
//		Optional<Attendance> oAttendance = attendanceRepository.findByStudentAndToday(student, localDate);
//		
//		if(oAttendance.isEmpty()) {
//			return null;
//		}
//		
//		Attendance todayAttendance = oAttendance.get();
//		return todayAttendance;
//	}

	//입실
	public void regAttendance(Course course, Student student, LocalDate localDate) {
		Optional<Attendance> attendanceTmp = attendanceRepository.findByStudentAndToday(student, localDate);
		Attendance attendance = attendanceTmp.get();
>>>>>>> yuran
		attendance.setStudent(student);
		attendance.setToday(localDate);
		attendance.setInTime(LocalTime.now());
		
<<<<<<< HEAD
		
		//course 시작시간과 비교
		attendance.setStatus("입실");
		
		if(course.getStartTime().isBefore(attendance.getInTime())) {
			attendance.setStatus("지각");
			
			if(course.getEndTime().isBefore(attendance.getInTime())) {
				attendance.setStatus("결석");
			}
		}
		
		attendanceRepository.save(attendance);
=======
			//course 시작시간과 비교
			attendance.setStatus("입실");
			if(attendance.getInTime() != null) {
				if(course.getStartTime().isBefore(attendance.getInTime())) {
					attendance.setStatus("지각");
					
					if(course.getEndTime().isBefore(attendance.getInTime())) {
						attendance.setStatus("결석");
					}
				}
			}
			
			attendanceRepository.save(attendance);
	
>>>>>>> yuran
	}
	
	//퇴실
	public void regLeave(Course course, Student student, LocalDate localDate) {
<<<<<<< HEAD
		Attendance attendance = this.getTodayAttendance(student, localDate);
		attendance.setOutTime(LocalTime.now());
		
		attendance.setStatus("출석");
		
		if(course.getStartTime().isBefore(attendance.getInTime())) {
			attendance.setStatus("지각");	
		}
		
		if(course.getEndTime().isAfter(attendance.getOutTime())) {
			attendance.setStatus("조퇴");	
		}
		
		if(course.getEndTime().isBefore(attendance.getInTime())) {
			attendance.setStatus("결석");
		}
		if(attendance.getOutTime().isAfter(course.getEndTime().plusHours(1))) {
			attendance.setStatus("결석");
		}
		
=======
		//Attendance attendance = this.getTodayAttendance(student, localDate);
		Optional<Attendance> attendanceTmp = attendanceRepository.findByStudentAndToday(student, localDate);
		Attendance attendance = attendanceTmp.get();
		attendance.setOutTime(LocalTime.now());
		
		attendance.setStatus("출석");
		if(attendance.getInTime() != null) {
			if(course.getStartTime().isBefore(attendance.getInTime())) {
				attendance.setStatus("지각");	
			}
			
			if(course.getEndTime().isAfter(attendance.getOutTime())) {
				attendance.setStatus("조퇴");	
			}
			
			if(course.getEndTime().isBefore(attendance.getInTime())) {
				attendance.setStatus("결석");
			}
			if(attendance.getOutTime().isAfter(course.getEndTime().plusHours(1))) {
				attendance.setStatus("결석");
			}
		}
>>>>>>> yuran
		log.info("####attendance's status" + attendance.getStatus());
		
		attendanceRepository.save(attendance);
		
	}	

	
	
	//푸시알림 타이머
	public void pushNotificationTimer(Member member, Course course, Attendance attendance) {
		
		String token = member.getToken();
		log.info("##############push timer's token=" + token);
		
<<<<<<< HEAD
		//강의 종료 시간 - 강의 시작 시간
		long startTime = course.getStartTime().getLong(ChronoField.MILLI_OF_DAY);
		long endTime = course.getEndTime().getLong(ChronoField.MILLI_OF_DAY);
		long courseTime = endTime - startTime;
		
		//강의 시작 시간 - 입실 시간
		long checkInTime = attendance.getInTime().getLong(ChronoField.MILLI_OF_DAY);
		long gap = startTime - checkInTime;
		long delay = courseTime + gap;
		
		log.info("####startTime=" + startTime);
		log.info("####endTime=" + endTime);
		log.info("####courseTime=" + courseTime);
		log.info("####checkInTime=" + checkInTime);
		log.info("####gap=" + gap);
		log.info("####delay=" + delay);
		
		//2분 간격
		long period = 120000;

		TimerTask timerTask = new TimerTask() {

			@Override
			public void run() {
				
				//데이터베이스 비교해서 입실 상태이면 "알림 보내기"
				if(attendance.getStatus().equals("입실") || attendance.getStatus().equals("지각")) {
=======
		if(course != null) {
			
			//강의 종료 시간 - 강의 시작 시간
			long startTime = course.getStartTime().getLong(ChronoField.MILLI_OF_DAY);
			long endTime = course.getEndTime().getLong(ChronoField.MILLI_OF_DAY);
			long courseTime = endTime - startTime;
			
			//강의 시작 시간 - 입실 시간
			long checkInTime = attendance.getInTime().getLong(ChronoField.MILLI_OF_DAY);
			long gap = startTime - checkInTime;
			long delay = courseTime + gap;
			
			log.info("####startTime=" + startTime);
			log.info("####endTime=" + endTime);
			log.info("####courseTime=" + courseTime);
			log.info("####checkInTime=" + checkInTime);
			log.info("####gap=" + gap);
			log.info("####delay=" + delay);
			
			//2분 간격
			long period = 120000;
			
			TimerTask timerTask = new TimerTask() {
				
				@Override
				public void run() {
					
					//데이터베이스 비교해서 입실 상태이면 "알림 보내기"
					if(attendance.getStatus().equals("입실") || attendance.getStatus().equals("지각")) {
>>>>>>> yuran
						log.info("####푸시알림####");
						
						String response = "";
						try {
							response = fcmService.sendMessage(token);
<<<<<<< HEAD
=======
							
>>>>>>> yuran
						} catch (FirebaseMessagingException e) {
							
							e.printStackTrace();
						}
						
						log.info("#######FirebaseMessaging=" + response);
<<<<<<< HEAD
				}
			}
		};
		
		Timer timer = new Timer();
		timer.schedule(timerTask, delay, period);
=======
					}
				}
			};
			
			Timer timer = new Timer();
			timer.schedule(timerTask, delay, period);
		}
>>>>>>> yuran
	}
}
