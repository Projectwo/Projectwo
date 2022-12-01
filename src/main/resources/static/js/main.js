// commonAjax
function commonAjax(url, parameter, type, calbak) {
    $.ajax({
        url: url,
        data: parameter,
        type: type,
        contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
        success: function (res) {
            calbak(res);
        },
        error: function (err) {
            console.log('error');
            calbak(err);
        }
    });
}

// by 안준언, Academy 계정에서 수업 정보 출력
function getClassInfo(){
	let msg = {
		fan: "fst"
	}
	commonAjax('/getCourse', msg, 'get', function(result) {
		console.log(result);
		createCourseList(result);
	})
}

function createCourseList(res){
	let tag = "";
	if(res != null) {
		res.forEach(function(course) {
			console.log(course);
			
			let cs = {
				courseId: course.id
			}
			
			let teacherName;
			
			commonAjax('/getTeacher', cs, 'get', function(result){
				console.log(result);
				teacherName = result[0].teacher.name;
				console.log(teacherName);
			})
			
			let currentStudentCount;
			
			commonAjax('/getStudent', cs, 'get', function(rs){
				console.log(rs.length);
				currentStudentCount = rs.length;
				console.log(currentStudentCount);
			})
			
						
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
					"<li>금</li>" + 	"<li>토</li>" + "<li>일</li>" + "</ul>" +
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