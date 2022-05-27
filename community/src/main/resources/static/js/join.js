// sign-up page에 쓰이는 회원가입을 위한 코드
$(function() {
	$("#join--submit").on("click", function() {
		let data = {
			name: $('#name').val(),
			account: $('#account').val(),
			password: $('#password').val(),
			age: $('#age').val(),
			roles: $('#roles').val(),
		}

		$.ajax({
			type: 'POST',
			url: '/members/sign-up',
			data: JSON.stringify(data),
			contentType: 'application/json; charset=utf-8',
			dataType: 'json'
		}).done(function(r) {
			alert("Sign up success");
			window.location.href = "/"; // home으로 이동하게 함
		}).fail(function(r) {
			alert('서버 오류');
		});
	});
});
