window.onload = function() {
	getClassInfo();
	getTeacherInfo();
	getStudentInfo();
}

// by 안준언, Academy 계정에서 수업 정보 출력
function getClassInfo() {

	commonAjax('/getAllCourse', null, 'get', function(result) {
		//console.log(result);
		getAllCourse(result);
		modifyCourseBtnEvent();
	})
}

function modifyCourseBtnEvent() {
	let ex = document.querySelectorAll(".lecture-modify-button");
	ex.forEach(function(course) {
		course.addEventListener('click', function() {
			let parent = course.parentNode;
			let children = parent.childNodes;
			let courseId = children[8].textContent;

			let msg = {
				courseId: courseId
			}

			//document.getElementById("courseModifyBtn").addEventListener("click", getRoom);
			//document.getElementById("courseModifyBtn").addEventListener("click", getTeacher);
			//document.getElementById("roomSelect").addEventListener("change", showMaxSeat);

			commonAjax('/getCourseById', msg, 'post', function(course) {
				//console.log(course);
				
				let mon = (course.mon) ? "checked" : "";
				let tue = (course.tue) ? "checked" : "";
				let wed = (course.wed) ? "checked" : "";
				let thu = (course.thu) ? "checked" : "";
				let fri = (course.fri) ? "checked" : "";
				let sat = (course.sat) ? "checked" : "";
				let sun = (course.sun) ? "checked" : "";
				
				let tag = "";
				tag += 	"<div class=\"add-title\">" +
                		"과정명<br>" +
                		"<input type=\"text\" id=\"modifyTitle\" value='" + course.title +"' />" +
            			"</div>" +
            			"<div class=\"add-teacher-name\">강사<br>" +
                		"<select id=\"modifyTeacherSelect\">" +
                		"<option> 1 </option>" +
                		"</select>" +
            			"</div>" +
            			"<div class=\"check-lecture-week\">" +
                		"강의요일<br>" +
                		"<div class=\"check-day-button\">" +
                    	"<input type=\"checkbox\" class=\"day-button mon\" id=\"modifyMon\" " + mon + "/>월" +
                    	"<input type=\"checkbox\" class=\"day-button tue\" id=\"modifyTue\" " + tue + "/>화" +
                    	"<input type=\"checkbox\" class=\"day-button wed\" id=\"modifyWed\" " + wed + "/>수" +
                    	"<input type=\"checkbox\" class=\"day-button thu\" id=\"modifyThu\" " + thu + "/>목" +
                    	"<input type=\"checkbox\" class=\"day-button fri\" id=\"modifyFri\" " + fri + "/>금" +
                    	"<input type=\"checkbox\" class=\"day-button sat\" id=\"modifySat\" " + sat + "/>토" +
                    	"<input type=\"checkbox\" class=\"day-button sun\" id=\"modifySun\" " + sun + "/>일" +
                		"</div>" +
            			"</div>" +
            			"<div class=\"add-lecture-period\">" +
                		"강의기간<br>" +
                		"<input class=\"lecture-period-start\" type=\"date\" data-placeholder=\"강의 시작일\" required aria-required=\"ture\" id=\"modifyStartDate\" value='" + course.startDate +"'/>" +
                		"<input class=\"lecture-period-end\" type=\"date\" data-placeholder=\"강의 종료일\" required aria-required=\"ture\" id=\"modifyEndDate\" value='" + course.endDate + "'/>" +
            			"</div>" +
            			"<div class=\"add-lecture-time\">" +
                		"강의시간<br>" +
                		"<input class=\"lecture-time-start\" type=\"time\" data-placeholder=\"강의 시작시간\" required aria-required=\"ture\" id=\"modifyStartTime\" value='" + course.startTime + "' />" +
                		"<input class=\"lecture-time-end\" type=\"time\" data-placeholder=\"강의 종료시간\" required aria-required=\"ture\" id=\"modifyEndTime\" value ='" + course.endTime + "'/>" +
            			"</div>" +
            			"<div class=\"add-lecture-room\">" +
                		"강의실<br>" +
                		"<select id=\"modifyRoomSelect\">" +
                		"<option> 1 </option>" +
                		"</select>" +
            			"</div>" +
            			"<div class=\"add-lecture-count\">" +
                		"수강인원<br>" +
                		"<div id=\"modifyRoomMaxSeat\">" +
                		"<input type=\"text\" />" +
                		"</div>" +
                		"<div id='modifyCourseId'>" + course.id + "</div>" +
            			"</div>" +
            			"<div class=\"add-button-section\">" +
                		"<div class=\"add-confirm\">" +
                    	"<button type=\"button\" class=\"add-confirm-button\" id=\"modifyCourseBtn\">등록</button>" +
                		"</div>" +
                		"<div class=\"add-close\">" +
                    	"<button type=\"button\" onclick=\"closeModal('modify')\" class=\"modal-close-button\">취소</button>" +
                		"</div>" +
            			"</div>" ;

				$("#modifyCourse").empty().append(tag); 
				modifyGetRoom();
				modifyGetTeacher();
				document.getElementById("modifyRoomSelect").addEventListener("change", modifyShowMaxSeat);
				document.getElementById('modifyCourseBtn').addEventListener("click", modifyCourse);
			}, false)
		});
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
				"<button onclick=\"clickModifyButton('lecture')\" class=\"lecture-modify-button\" id=\"courseModifyBtn\">" +
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
				course.room.name + "</div>" +
				"<div id='modifyCourseId'>" + course.id + "</div>" +
				"</div>" + "</div>";
		});

		$("#classListId").empty().append(tag);
	}
}

//by 안준언, Academy 계정에서 강사 정보 출력
function getTeacherInfo() {
	commonAjax('/getAllTeacher', null, 'get', function(result) {
		//console.log(result);
		getAllTeacher(result);
		modifyTeacherBtnEvent();
	})
}

function modifyTeacherBtnEvent() {
	let ex = document.querySelectorAll(".teacher-modify-button");
	ex.forEach(function(teacher) {
		teacher.addEventListener('click', function() {
			let parent = teacher.parentNode;
			let children = parent.childNodes;
			console.log(children)
			let memderId = children[5].textContent;

			let msg = {
				memberId: memderId
			}

			commonAjax('/getMemberById', msg, 'post', function(member) {
				let tag = "";
				tag += "<div class='add-name'>" +
					"이름<br>" +
					"<input type='text' value='" + member.name + "' id=\"teacherModifyName\"/>" +
					"</div>" +
					"<div class='add-birthday'>" +
					"생년월일<br>" +
					"<input type='date' value='" + member.birth_date + "' id=\"teacherModifyBirth_date\"/>" +
					"</div>" +
					"<div class='add-tel'>" +
					"연락처<br>" +
					"<input type='text' value='" + member.tel + "' id=\"teacherModifyTel\"/>" +
					"</div>" +
					"<div class='add-mail'>" +
					"메일<br>" +
					"<input type='email' value='" + member.email + "' id=\"teacherModifyEmail\"/>" +
					"</div>" +
					"<div class='add-address'>" +
					"거주지<br>" +
					"<input type='text' value='" + member.address + "' id=\"teacherModifyAddress\"/>" +
					"</div>" +
					"<div id='modifyTeacherId'>" + member.id + "</div>" +
					"<div class='add-button-section'>" +
					"<div class='add-confirm'>" +
					"<button type=\"button\" class=\"add-confirm-button\" id=\"teacherModifyBtn\">등록</button>" +
					"</div>" +
					"<div class='add-close'>" +
					"<button type=\"button\" onclick=\"closeModal('modify')\" class=\"modal-close-button\">취소</button>" +
					"</div>" +
					"</div>";

				$("#modifyTeacher").empty().append(tag);
			}, false)
			modifyTeacherEvent();
		});
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
				if (rs === null) {
					classListOfTeacher = "0";
				}
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
				"<div class='teacher-id'>" + member.id +
				"</div>" +
				"</div>" +
				"</div>";
		})
	}

	$("#allTeacherList").empty().append(tag);
}

// by 안준언, Academy 계정에서 학생 정보 출력
function getStudentInfo() {
	commonAjax('/getAllStudent', null, 'get', function(result) {
		//console.log(result);
		getAllStudent(result);
		modifyStudentBtnEvent();
	})
}


function modifyStudentBtnEvent() {
	let ex = document.querySelectorAll(".student-modify-button");
	ex.forEach(function(student) {
		student.addEventListener('click', function() {
			let parent = student.parentNode;
			let children = parent.childNodes;
			console.log(children)
			let memderId = children[5].textContent;

			let msg = {
				memberId: memderId
			}

			commonAjax('/getMemberById', msg, 'post', function(member) {
				let tag = "";
				tag += "<div class='add-name'>" +
					"이름<br>" +
					"<input type='text' value='" + member.name + "'/>" +
					"</div>" +
					"<div class='add-birthday'>" +
					"생년월일<br>" +
					"<input type='date' value='" + member.birth_date + "'/>" +
					"</div>" +
					"<div class='add-tel'>" +
					"연락처<br>" +
					"<input type='text' value='" + member.tel + "' />" +
					"</div>" +
					"<div class='add-mail'>" +
					"메일<br>" +
					"<input type='email' value='" + member.email + "' />" +
					"</div>" +
					"<div class='add-address'>" +
					"거주지<br>" +
					"<input type='text' value='" + member.address + "' />" +
					"</div>" +
					"<div id='modifyStudentId'>" + member.id + "</div>" +
					"<div class='add-button-section'>" +
					"<div class='add-confirm'>" +
					"<button type=\"submit\" class=\"add-confirm-button\" id=\"studentModifyBtn\">등록</button>" +
					"</div>" +
					"<div class='add-close'>" +
					"<button type=\"button\" onclick=\"closeModal('modify')\" class=\"modal-close-button\">취소</button>" +
					"</div>" +
					"</div>";

				$("#modifyStudent").empty().append(tag);
			}, false)
			modifyStudentEvent();
		});
	})
}

function getAllStudent(res) {
	let tag = "";
	if (res != null) {
		res.forEach(function(member) {

			let mb = {
				memberId: member.id
			}

			let classListOfStudent;

			commonAjax('/getClassListOfStudent', mb, 'get', function(rs) {
				//console.log(rs.length);
				classListOfStudent = rs.length;
				if (rs === null) {
					classListOfStudent = "0";
				}
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
				"<div class='student-id'>" + member.id +
				"</div>" +
				"</div>" +
				"</div>";
		})
	}
	$("#allStudentList").empty().append(tag);
}

// by 안준언, Academy 계정에서 강의 생성시 강의실 관련 정보 출력
document.getElementById("addCourseBtn").addEventListener("click", getRoom);
document.getElementById("addCourseBtn").addEventListener("click", getTeacher);
document.getElementById("roomSelect").addEventListener("change", showMaxSeat);


function getRoom() {
	commonAjax('/getAllRoom', null, 'get', function(result) {
		//console.log(result);
		getAllRoom(result);
	})
}

function getAllRoom(res) {
	let tag = "";
	if (res != null) {
		res.forEach(function(room) {
			tag += "<option id='selectedRoomName'>" + room.name + "</option>";
		})
	}
	$("#roomSelect").empty().append(tag);
}

function getTeacher() {
	commonAjax('/getAllTeacher', null, 'get', function(result) {
		selectTeacher(result);
	})
}

function selectTeacher(res) {
	let tag = "";
	if (res != null) {
		res.forEach(function(member) {
			tag += "<option id='selectedTeacherName' name=\"memberName\" id=\"memberName\">" + member.name + "</option>";
		})
	}
	$("#teacherSelect").empty().append(tag);
}

function showMaxSeat() {
	let roomName = document.getElementById("roomSelect");
	let selectedRoomName = roomName.options[roomName.selectedIndex].text;
	//console.log(selectedRoomName);
	//console.log(roomName);
	let msg = {
		roomName: selectedRoomName
	}
	commonAjax('/getRoomByName', msg, 'get', function(result) {
		getRoomByName(result);
	})
}

function getRoomByName(room) {
	//console.log(room);
	let tag = "";
	if (room != null) {
		tag += room.maxSeat;
	}
	$('#roomMaxSeat').empty().append(tag);
}
/////////// modify
//document.getElementById("courseModifyBtn").addEventListener("click", modifyGetRoom);
//document.getElementById("courseModifyBtn").addEventListener("click", modifyGetTeacher);
//document.getElementById("roomSelect").addEventListener("change", modifyShowMaxSeat);


function modifyGetRoom() {
	commonAjax('/getAllRoom', null, 'get', function(result) {
		//console.log(result);
		modifyGetAllRoom(result);
	})
}

function modifyGetAllRoom(res) {
	let tag = "";
	if (res != null) {
		res.forEach(function(room) {
			tag += "<option id='modifySelectedRoomName'>" + room.name + "</option>";
		})
	}
	$("#modifyRoomSelect").empty().append(tag);
}

function modifyGetTeacher() {
	commonAjax('/getAllTeacher', null, 'get', function(result) {
		modifySelectTeacher(result);
	})
}

function modifySelectTeacher(res) {
	let tag = "";
	if (res != null) {
		res.forEach(function(member) {
			tag += "<option id='modifySelectedTeacherName' name=\"memberName\" id=\"memberName\">" + member.name + "</option>";
		})
	}
	$("#modifyTeacherSelect").empty().append(tag);
}

function modifyShowMaxSeat() {
	let roomName = document.getElementById("modifyRoomSelect");
	let selectedRoomName = roomName.options[roomName.selectedIndex].text;
	//console.log(selectedRoomName);
	//console.log(roomName);
	let msg = {
		roomName: selectedRoomName
	}
	commonAjax('/getRoomByName', msg, 'get', function(result) {
		modifyGetRoomByName(result);
	})
}

function modifyGetRoomByName(room) {
	//console.log(room);
	let tag = "";
	if (room != null) {
		tag += room.maxSeat;
	}
	$('#modifyRoomMaxSeat').empty().append(tag);
}

// by 안준언, 학생 등록 
document.getElementById('studentAddBtn').addEventListener("click", addStudent)

function addStudent() {
	if (document.getElementById('studentName').value == '') {
		alert("이름을 입력하세요.");
		return;
	} else if (document.getElementById('studentBirth_date').value == '') {
		alert("생년월일을 입력하세요.");
		return;
	} else if (document.getElementById('studentTel').value == '') {
		alert("연락처를 입력하세요.");
		return;
	} else if (document.getElementById('studentEmail').value == '') {
		alert("이메일을 입력하세요.");
		return;
	} else if (document.getElementById('studentAddress').value == '') {
		alert("주소를 입력하세요.");
		return;
	}

	let msg = {
		name: document.getElementById('studentName').value,
		birth_date: document.getElementById('studentBirth_date').value,
		tel: document.getElementById('studentTel').value,
		email: document.getElementById('studentEmail').value,
		address: document.getElementById('studentAddress').value
	}

	commonAjax('/createStudent', msg, 'post', function() {
		getStudentInfo();
	})

	document.getElementById('studentName').value = null;
	document.getElementById('studentBirth_date').value = null;
	document.getElementById('studentTel').value = null;
	document.getElementById('studentEmail').value = null;
	document.getElementById('studentAddress').value = null;

	document.getElementById('studentAddBtn').addEventListener("click", closeModal('add'));

}

// by 안준언, 수업 등록
document.getElementById('courseAddBtn').addEventListener("click", addCourse)

function addCourse() {
	if (document.getElementById('courseTitle').value == '') {
		alert("강의명을 입력하세요.");
		return;
	} else if (document.getElementById('courseStartDate').value == '') {
		alert("강의 시작일을 입력하세요.");
		return;
	} else if (document.getElementById('courseEndDate').value == '') {
		alert("강의 종료일을 입력하세요.");
		return;
	} else if (document.getElementById('courseStartTime').value == '') {
		alert("강의 시작 시간을 입력하세요.");
		return;
	} else if (document.getElementById('courseEndTime').value == '') {
		alert("강의 종료 시간을 입력하세요.");
		return;
	} else if ((document.getElementById('courseMon').checked == false) &&
		(document.getElementById('courseTue').checked == false) &&
		(document.getElementById('courseWed').checked == false) &&
		(document.getElementById('courseThu').checked == false) &&
		(document.getElementById('courseFri').checked == false) &&
		(document.getElementById('courseSat').checked == false) &&
		(document.getElementById('courseSun').checked == false)) {
		alert("강의 요일이 모두 비었습니다. 다시 확인해주세요.")
		return;
	}

	let room = document.getElementById("roomSelect");
	let roomName = room.options[room.selectedIndex].text;
	let teacher = document.getElementById("teacherSelect");
	let teacherName = teacher.options[teacher.selectedIndex].text;

	console.log(roomName);
	console.log(teacherName);

	let msg = {
		title: document.getElementById('courseTitle').value,
		startDate: document.getElementById('courseStartDate').value,
		endDate: document.getElementById('courseEndDate').value,
		startTime: document.getElementById('courseStartTime').value,
		endTime: document.getElementById('courseEndTime').value,
		mon: document.getElementById('courseMon').checked,
		tue: document.getElementById('courseTue').checked,
		wed: document.getElementById('courseWed').checked,
		thu: document.getElementById('courseThu').checked,
		fri: document.getElementById('courseFri').checked,
		sat: document.getElementById('courseSat').checked,
		sun: document.getElementById('courseSun').checked,
		roomName: roomName,
		teacherName: teacherName
	}


	commonAjax('/createCourse', msg, 'post', function() {
		getClassInfo();
	})


	document.getElementById('courseTitle').value = null;
	document.getElementById('courseStartDate').value = null;
	document.getElementById('courseEndDate').value = null;
	document.getElementById('courseStartTime').value = null;
	document.getElementById('courseEndTime').value = null;
	document.getElementById('courseMon').checked = false;
	document.getElementById('courseTue').checked = false;
	document.getElementById('courseWed').checked = false;
	document.getElementById('courseThu').checked = false;
	document.getElementById('courseFri').checked = false;
	document.getElementById('courseSat').checked = false;
	document.getElementById('courseSun').checked = false;

	document.getElementById('courseAddBtn').addEventListener("click", closeModal('add'));

}


// by 안준언, 강사 등록
document.getElementById('teacherAddBtn').addEventListener("click", addTeacher)

function addTeacher() {
	if (document.getElementById('teacherName').value == '') {
		alert("이름을 입력하세요.");
		return;
	} else if (document.getElementById('teacherBirth_date').value == '') {
		alert("생년월일을 입력하세요.");
		return;
	} else if (document.getElementById('teacherTel').value == '') {
		alert("연락처를 입력하세요.");
		return;
	} else if (document.getElementById('teacherEmail').value == '') {
		alert("이메일을 입력하세요.");
		return;
	} else if (document.getElementById('teacherAddress').value == '') {
		alert("주소를 입력하세요.");
		return;
	}

	let msg = {
		name: document.getElementById('teacherName').value,
		birth_date: document.getElementById('teacherBirth_date').value,
		tel: document.getElementById('teacherTel').value,
		email: document.getElementById('teacherEmail').value,
		address: document.getElementById('teacherAddress').value
	}

	commonAjax('/createTeacher', msg, 'post', function() {
		getTeacherInfo();
	})

	document.getElementById('teacherName').value = null;
	document.getElementById('teacherBirth_date').value = null;
	document.getElementById('teacherTel').value = null;
	document.getElementById('teacherEmail').value = null;
	document.getElementById('teacherAddress').value = null;

	document.getElementById('teacherAddBtn').addEventListener("click", closeModal('add'));
}

// 강의 수정
//document.getElementById('modifyCourseBtn').addEventListener("click", modifyCourse)

function modifyCourse() {
	if (document.getElementById('modifyTitle').value == '') {
		alert("강의명을 입력하세요.");
		return;
	} else if (document.getElementById('modifyStartDate').value == '') {
		alert("강의 시작일을 입력하세요.");
		return;
	} else if (document.getElementById('modifyEndDate').value == '') {
		alert("강의 종료일을 입력하세요.");
		return;
	} else if (document.getElementById('modifyStartTime').value == '') {
		alert("강의 시작 시간을 입력하세요.");
		return;
	} else if (document.getElementById('modifyEndTime').value == '') {
		alert("강의 종료 시간을 입력하세요.");
		return;
	} else if ((document.getElementById('modifyMon').checked == false) &&
		(document.getElementById('modifyTue').checked == false) &&
		(document.getElementById('modifyWed').checked == false) &&
		(document.getElementById('modifyThu').checked == false) &&
		(document.getElementById('modifyFri').checked == false) &&
		(document.getElementById('modifySat').checked == false) &&
		(document.getElementById('modifySun').checked == false)) {
		alert("강의 요일이 모두 비었습니다. 다시 확인해주세요.")
		return;
	}

	let room = document.getElementById("modifyRoomSelect");
	let roomName = room.options[room.selectedIndex].text;
	let teacher = document.getElementById("modifyTeacherSelect");
	let teacherName = teacher.options[teacher.selectedIndex].text;

	console.log(roomName);
	console.log(teacherName);

	let msg = {
		id: document.getElementById('modifyCourseId').innerText,
		title: document.getElementById('modifyTitle').value,
		startDate: document.getElementById('modifyStartDate').value,
		endDate: document.getElementById('modifyEndDate').value,
		startTime: document.getElementById('modifyStartTime').value,
		endTime: document.getElementById('modifyEndTime').value,
		mon: document.getElementById('modifyMon').checked,
		tue: document.getElementById('modifyTue').checked,
		wed: document.getElementById('modifyWed').checked,
		thu: document.getElementById('modifyThu').checked,
		fri: document.getElementById('modifyFri').checked,
		sat: document.getElementById('modifySat').checked,
		sun: document.getElementById('modifySun').checked,
		roomName: roomName,
		teacherName: teacherName
	}
console.log(msg)

	commonAjax('/modifyCourse', msg, 'post', function() {
		getClassInfo();
	})


	document.getElementById('modifyTitle').value = null;
	document.getElementById('modifyStartDate').value = null;
	document.getElementById('modifyEndDate').value = null;
	document.getElementById('modifyStartTime').value = null;
	document.getElementById('modifyEndTime').value = null;
	document.getElementById('modifyMon').checked = false;
	document.getElementById('modifyTue').checked = false;
	document.getElementById('modifyWed').checked = false;
	document.getElementById('modifyThu').checked = false;
	document.getElementById('modifyFri').checked = false;
	document.getElementById('modifySat').checked = false;
	document.getElementById('modifySun').checked = false;

	document.getElementById('modifyCourseBtn').addEventListener("click", closeModal('modify'));

}


// by 안준언, 강사정보 수정
////////

function modifyTeacherEvent() {

	document.getElementById('teacherModifyBtn').addEventListener("click", modifyTeacher)

	function modifyTeacher() {
		if (document.getElementById('teacherModifyName').value == '') {
			alert("이름을 입력하세요.");
			return;
		} else if (document.getElementById('teacherModifyBirth_date').value == '') {
			alert("생년월일을 입력하세요.");
			return;
		} else if (document.getElementById('teacherModifyTel').value == '') {
			alert("연락처를 입력하세요.");
			return;
		} else if (document.getElementById('teacherModifyEmail').value == '') {
			alert("이메일을 입력하세요.");
			return;
		} else if (document.getElementById('teacherModifyAddress').value == '') {
			alert("주소를 입력하세요.");
			return;
		}

		let msg = {
			memberId: document.getElementById('modifyTeacherId').innerText,
			name: document.getElementById('teacherModifyName').value,
			birth_date: document.getElementById('teacherModifyBirth_date').value,
			tel: document.getElementById('teacherModifyTel').value,
			email: document.getElementById('teacherModifyEmail').value,
			address: document.getElementById('teacherModifyAddress').value
		}

		//console.log(msg);

		commonAjax('/modifyMember', msg, 'post', function() {
			getTeacherInfo();
		})

		document.getElementById('teacherModifyName').value = null;
		document.getElementById('teacherModifyBirth_date').value = null;
		document.getElementById('teacherModifyTel').value = null;
		document.getElementById('teacherModifyEmail').value = null;
		document.getElementById('teacherModifyAddress').value = null;

		document.getElementById('teacherModifyBtn').addEventListener("click", closeModal('modify'));
	}
}

/////// 학생 정보 수정
function modifyStudentEvent() {

	document.getElementById('studentModifyBtn').addEventListener("click", modifyStudent)

	function modifyStudent() {
		if (document.getElementById('studentModifyName').value == '') {
			alert("이름을 입력하세요.");
			return;
		} else if (document.getElementById('studentModifyBirth_date').value == '') {
			alert("생년월일을 입력하세요.");
			return;
		} else if (document.getElementById('studentModifyTel').value == '') {
			alert("연락처를 입력하세요.");
			return;
		} else if (document.getElementById('studentModifyEmail').value == '') {
			alert("이메일을 입력하세요.");
			return;
		} else if (document.getElementById('studentModifyAddress').value == '') {
			alert("주소를 입력하세요.");
			return;
		}

		let msg = {
			memberId: document.getElementById('modifyStudentId').innerText,
			name: document.getElementById('studentModifyName').value,
			birth_date: document.getElementById('studentModifyBirth_date').value,
			tel: document.getElementById('studentModifyTel').value,
			email: document.getElementById('studentModifyEmail').value,
			address: document.getElementById('studentModifyAddress').value
		}

		//console.log(msg);

		commonAjax('/modifyMember', msg, 'post', function() {
			getTeacherInfo();
		})

		document.getElementById('studentModifyName').value = null;
		document.getElementById('studentModifyBirth_date').value = null;
		document.getElementById('studentModifyTel').value = null;
		document.getElementById('studentModifyEmail').value = null;
		document.getElementById('studentModifyAddress').value = null;

		document.getElementById('studentModifyBtn').addEventListener("click", closeModal('modify'));
	}
}

