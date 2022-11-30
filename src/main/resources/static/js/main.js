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
			console.log(course.teacherList[0]);
			let teacherName = course.teacherList[0].teacher.name;
			
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
					"</div>"
		});
		$("#classListId").empty().append(tag);
	}
}