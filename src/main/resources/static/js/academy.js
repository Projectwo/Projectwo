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
				if(rs === null) {
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
				if(rs === null) {
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
            "</div>" +
        "</div>";
		})
	}
	$("#allStudentList").empty().append(tag);
}

// by 안준언, Academy 계정에서 강의 생성시 강의실 관련 정보 출력
document.getElementById("addCourseBtn").addEventListener("click", getRoom);
document.getElementById("addCourseBtn").addEventListener("click", getTeacher);
document.getElementById("roomSelect").addEventListener("change",  showMaxSeat);


function getRoom() {
	commonAjax('/getAllRoom', null, 'get', function(result){
		//console.log(result);
		getAllRoom(result);
	})
}

function getAllRoom(res) {
	let tag = "";
	if(res != null) {
		res.forEach(function(room){
			tag += "<option id='selectedRoomName'>" + room.name + "</option>";
		})
	}
	$("#roomSelect").empty().append(tag);
}

function getTeacher() {
	commonAjax('/getAllTeacher', null, 'get', function(result){
		selectTeacher(result);
	})
}

function selectTeacher(res) {
	let tag = "";
	if(res != null) {
		res.forEach(function(member){
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
	commonAjax('/getRoomByName', msg, 'get', function(result){
		getRoomByName(result);
	})
}

function getRoomByName(room) {
	//console.log(room);
	let tag = "";
	if(room != null) {
		tag += room.maxSeat;
	}
	$('#roomMaxSeat').empty().append(tag);
}

// by 안준언, 학생 등록 
document.getElementById('studentAddBtn').addEventListener("click", addStudent)

function addStudent(){
	if(document.getElementById('studentName').value == '') {
		alert("이름을 입력하세요.");
		return;
	} else if(document.getElementById('studentBirth_date').value == '') {
		alert("생년월일을 입력하세요.");
		return;
	} else if(document.getElementById('studentTel').value == '') {
		alert("연락처를 입력하세요.");
		return;
	} else if(document.getElementById('studentEmail').value == '') {
		alert("이메일을 입력하세요.");
		return;
	} else if(document.getElementById('studentAddress').value == '') {
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
	
	commonAjax('/createStudent', msg, 'post', function(){
		getStudentInfo();
	})
	
	document.getElementById('studentName').value = null;
	document.getElementById('studentBirth_date').value = null;
	document.getElementById('studentTel').value = null;
	document.getElementById('studentEmail').value = null;
	document.getElementById('studentAddress').value = null;
	
	document.getElementById('studentAddBtn').addEventListener("click", closeModal('add'));

}


// by 안준언, 강사 등록
document.getElementById('teacherAddBtn').addEventListener("click", addTeacher)

function addTeacher(){
	if(document.getElementById('teacherName').value == '') {
		alert("이름을 입력하세요.");
		return;
	} else if(document.getElementById('teacherBirth_date').value == '') {
		alert("생년월일을 입력하세요.");
		return;
	} else if(document.getElementById('teacherTel').value == '') {
		alert("연락처를 입력하세요.");
		return;
	} else if(document.getElementById('teacherEmail').value == '') {
		alert("이메일을 입력하세요.");
		return;
	} else if(document.getElementById('teacherAddress').value == '') {
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
	
	commonAjax('/createTeacher', msg, 'post', function(){
		getTeacherInfo();
	})
	
	document.getElementById('teacherName').value = null;
	document.getElementById('teacherBirth_date').value = null;
	document.getElementById('teacherTel').value = null;
	document.getElementById('teacherEmail').value = null;
	document.getElementById('teacherAddress').value = null;
	
	document.getElementById('teacherAddBtn').addEventListener("click", closeModal('add'));
}


// by 안준언 수업 등록
