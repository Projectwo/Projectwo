package com.project.Projectwo.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.project.Projectwo.Entity.Attendance;
import com.project.Projectwo.Entity.Course;
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
	
	//by 박은영
	//'당일' 학생 출결 정보 가져오기
	public Attendance getTodayAttendance(Student student, LocalDate localDate) {
		Optional<Attendance> oAttendance = attendanceRepository.findByStudentAndToday(student, localDate);
		
		if(oAttendance.isEmpty()) {
			log.info("####oAttendance is null");
			return null;
		}
		
		Attendance todayAttendance = oAttendance.get();
		
		return todayAttendance;
	}
	
	//입실
	public void regAttendance(Course course, Student student, LocalDate localDate) {
		Attendance attendance = new Attendance();
		attendance.setStudent(student);
		attendance.setToday(localDate);
		attendance.setInTime(LocalTime.now());
		
		
		//course 시작시간과 비교
		attendance.setStatus("입실");
		
		if(course.getStartTime().isBefore(attendance.getInTime())) {
			attendance.setStatus("지각");
			
			if(course.getEndTime().isBefore(attendance.getInTime())) {
				attendance.setStatus("결석");
			}
		}
		
		attendanceRepository.save(attendance);
	}
	
	//퇴실
	public void regLeave(Course course, Student student, LocalDate localDate) {
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
		
		log.info("####attendance's status" + attendance.getStatus());
		
		attendanceRepository.save(attendance);
		
	}	
	
	

}
