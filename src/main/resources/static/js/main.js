// commonAjax
function commonAjax(url, parameter, type, calbak, asy) {
	$.ajax({
		url: url,
		data: parameter,
		type: type,
		async: asy,
		contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
		success: function(res) {
			calbak(res);
		},
		error: function(err) {
			console.log('error');
			calbak(err);
		}
	});
}

// by 안준언, Academy 계정에서 수업 정보 출력
function getClassInfo() {

	commonAjax('/getAllCourse', null, 'get', function(result) {
		//console.log(result);
		getAllCourse(result);
	})
}

function getAllCourse(res) {
	let tag = "";
	if (res != null) {
		res.forEach(function(course) {
			//console.log(course);

			let cs = {
				courseId: course.id
			}

			let teacherName;

			commonAjax('/getClassTeacher', cs, 'get', function(result) {
				//console.log(result);
				teacherName = result[0].teacher.name;
				//console.log(teacherName);
			}, false)

			var currentStudentCount;

			commonAjax('/getClassStudent', cs, 'get', function(rs) {
				//console.log(rs.length);
				currentStudentCount = rs.length;
				//console.log(currentStudentCount);
			}, false)



			tag += "<div class='main-lecture academy-list'>" +
				"<div class='lecture-info'>" +
				"<button onclick=\"clickModifyButton('lecture')\" class=\"lecture-modify-button\">" +
				"수정" +
				"</button>" +
				"<div class='lecture-title'>" +
				"<div class='lecture-name'>" +
				"<b>" + course.title + "</b>" +
				"</div>" +
				"<div class='lecture-teacher'>" +
				"<b>" + teacherName + "</b>" +
				"</div>" + "</div>" + "<ul class='lecture-info-schedule'>" +
				"<li>월</li>" + "<li>화</li>" + "<li>수</li>" + "<li>목</li>" +
				"<li>금</li>" + "<li>토</li>" + "<li>일</li>" + "</ul>" +
				"<ul class='lecture-info-schedule-checked'>" +
				"<li><span>" + course.mon + "</span></li>" +
				"<li><span>" + course.tue + "</span></li>" +
				"<li><span>" + course.wed + "</span></li>" +
				"<li><span>" + course.thu + "</span></li>" +
				"<li><span>" + course.fri + "</span></li>" +
				"<li><span>" + course.sat + "</span></li>" +
				"<li><span>" + course.sun + "</span></li>" + "</ul>" +
				"<div class='lecture-info-schedule-period'>" +
				"<span class='period-start'>" +
				course.startDate + "</span>" + "~" +
				"<span class='period-end'>" +
				course.endDate + "</span>" + "</div>" +
				"<div class='lecture-info-schedule-time'>" +
				course.startTime + "~" + course.endTime +
				"</div>" +
				"<div class='lecture-info-count'>" +
				"수강인원" + "<span>" + currentStudentCount +
				"</span>" + "/" + course.room.maxSeat +
				"</div>" + "<div class='lecture-info-room'>" +
				course.room.name + "</div>" + "</div>" + "</div>";
		});

		$("#classListId").empty().append(tag);
	}
}

//by 안준언, Academy 계정에서 강사 정보 출력
function getTeacherInfo() {
	commonAjax('/getAllTeacher', null, 'get', function(result) {
		//console.log(result);
		getAllTeacher(result);
	})
}

function getAllTeacher(res) {
	let tag = "";
	if (res != null) {
		res.forEach(function(member) {

			let mb = {
				memberId: member.id
			}

			let classListOfTeacher;

			commonAjax('/getClassListOfTeacher', mb, 'get', function(rs) {
				//console.log(rs.length);
				classListOfTeacher = rs.length;
				//console.log(classListOfTeacher);
			}, false)

			tag += "<div class='academy-teacher'>" +
				"<div class='teacher-info'>" +
				"<button onclick=\"clickModifyButton('teacher')\" class=\"teacher-modify-button\">" +
				"수정" +
				"</button>" +
				"<div class='teacher-title'>" +
				"<div class='teacher-name'>" +
				"<b>" + member.name + "</b>" +
				"</div>" +
				"<div class='teacher-class'>" +
				"<b>" + classListOfTeacher + " Class" + "</b>" +
				"</div>" +
				"</div>" +
				"<div class='teacher-birthday'>" +
				member.birth_date + "<span>" + "/" + "</span>" +
				"</div>" +
				"<div class='teacher-tel'>" +
				member.tel +
				"</div>" +
				"<div class='teacher-address'>" +
				member.address +
				"</div>" +
				"</div>" +
				"</div>";
		})
	}

	$("#allTeacherList").empty().append(tag);
}

// by 안준언, Academy 계정에서 학생 정보 출력
function getStudentInfo() {
	commonAjax('/getAllStudent', null, 'get', function(result){
		//console.log(result);
		getAllStudent(result);
	})
}

function getAllStudent(res) {
	let tag = "";
	if(res != null) {
		res.forEach(function(member) {
			
			let mb = {
				memberId: member.id
			}
			
			let classListOfStudent;
			
			commonAjax('/getClassListOfStudent', mb, 'get', function(rs){
				//console.log(rs.length);
				classListOfStudent = rs.length;
				//console.log(classListOfStudent);
			}, false)
			
			tag += "<div class='academy-student'>" +
            		"<div class='student-info'>" +
                "<button onclick=\"clickModifyButton('student')\" class=\"student-modify-button\">" +
                    "수정" +
                "</button>" +
                "<div class='student-title'>" +
                    "<div class='student-name'>" +
                        "<b>" + member.name + "</b>" +
                    "</div>" +
                    "<div class='student-class'>" +
                        "<b>" + classListOfStudent + " Class" + "</b>" +
                    "</div>" +
                "</div>" +
                "<div class='student-birthday'>" +
                    member.birth_date + "<span>" + "/" + "</span>" +
                "</div>" +
                "<div class='student-tel'>" +
                    member.tel +
                "</div>" +
                "<div class='student-address'>" +
                    member.address +
                "</div>" +
            "</div>" +
        "</div>";
		})
	}
	$("#allStudentList").empty().append(tag);
}