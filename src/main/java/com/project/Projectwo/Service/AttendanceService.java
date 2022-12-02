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
	public Attendance getTodayAttendance(Student student, LocalDate localDate) {
		Optional<Attendance> oAttendance = attendanceRepository.findByStudentAndToday(student, localDate);
		
		if(oAttendance.isEmpty()) {
			log.info("####oAttendance is null");
			return null;
		}
		
		Attendance todayAttendance = oAttendance.get();
		
		log.info("####todayAttendance=" + todayAttendance.getStatus());

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
			attendance.setStatus("입실");
		}
		
		attendanceRepository.save(attendance);
	}
	
	//퇴실
	public void regLeave(Course course, Student student, LocalDate localDate) {
		Attendance attendance = this.getTodayAttendance(student, localDate);
		attendance.setOutTime(LocalTime.now());
		
		if(attendance.getStatus().equals("지각")) {
			if(course.getEndTime().isAfter(attendance.getOutTime())) {
				attendance.setStatus("조퇴");
			}
		}
		if(course.getEndTime().isAfter(attendance.getOutTime())) {
			attendance.setStatus("조퇴");
			
		}else {
			attendance.setStatus("출석");
		}
		
		attendanceRepository.save(attendance);
		
	}
	
}
