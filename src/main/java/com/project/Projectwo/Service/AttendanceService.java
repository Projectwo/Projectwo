package com.project.Projectwo.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.project.Projectwo.Entity.Attendance;
import com.project.Projectwo.Entity.Course;
import com.project.Projectwo.Entity.Student;
import com.project.Projectwo.Repository.AttendanceRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class AttendanceService {
	
	private final AttendanceRepository attendanceRepository;
	
	//by 박은영
	//'당일' 학생 출결 정보 가져오기
	public Attendance getTodayAttendance(Student student) {
		Optional<Attendance> oAttendance = attendanceRepository.findByStudentAndToday(student, LocalDate.now());
		
		if(oAttendance.isEmpty()) {
			log.info("####oAttendance is null");
			return null;
		}
		
		Attendance todayAttendance = oAttendance.get();

		return todayAttendance;
	}
	
	//입실
	public void regAttendance(Course course, Student student) {
		Attendance attendance = new Attendance();
		attendance.setStudent(student);
		attendance.setToday(LocalDate.now());
		attendance.setInTime(LocalTime.now());
		
		
		//course 시작시간과 비교
		if(course.getStartTime().isBefore(attendance.getInTime())) {//정상 출결
			attendance.setStatus("지각");
		}else {
			attendance.setStatus("");
		}
		
	}
	
	public void regLeave() {
		
		
	}
	
}
