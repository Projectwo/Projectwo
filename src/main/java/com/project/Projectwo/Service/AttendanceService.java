package com.project.Projectwo.Service;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.stereotype.Service;

import com.project.Projectwo.Entity.Attendance;
import com.project.Projectwo.Entity.Course;
import com.project.Projectwo.Entity.Student;

@Service
public class AttendanceService {
	
	//by 박은영
	//입실
	public void setAttendance(Course course, Student student) {
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
	
	public void setLeave() {
		
		
	}
	
}
