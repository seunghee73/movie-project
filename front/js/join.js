$('#join--submit').on('click', function() {
	let data = {
		name : $('#name').val(),
		account : $('#account').val(),
		password : $('#password').val(),
		age : $('#age').val(),
		rank : $('#rank').val(),
		roles : $('#roles').val(),
	};

	$.ajax({
		type : 'POST',
		url : '/members/sign-up',
		data : JSON.stringify(data),
		contentType : 'application/json; charset=utf-8',
		dataType : 'json'
	}).done(function(r) {
		if (r.statusCode == 200) {
			console.log(r);
			alert('회원가입 성공');
			location.href = '/user/login';
		} else {
			if (r.msg == '아이디중복') {
				console.log(r);
				alert('아이디가 중복되었습니다.');
			} else {
				console.log(r);
				alert('회원가입 실패');
			}
		}
	}).fail(function(r) {
		var message = JSON.parse(r.responseText);
		console.log((message));
		alert('서버 오류');
	});
});
