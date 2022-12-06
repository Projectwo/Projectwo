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