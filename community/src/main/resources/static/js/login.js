$(function() {
  $("#login").on("click", function() {
    let data = {
      account: $('#login-account').val(),
      password: $('#login-password').val()
    }

    $.ajax({
      type: 'POST',
      url: '/login',
      data: JSON.stringify(data),
      contentType: 'application/json; charset=utf-8',
      dataType: 'json',
      success: function(data, textStatus, request) {
        // alert(request.getResponseHeader('Authorization'));

      },
      error: function(request, textStatus, errorThrown) {
        // alert(request.getResponseHeader('Authorization'));
        alert("Login success");
        localStorage.setItem('Authorization', request.getResponseHeader('Authorization'));
        location.reload();
      }
    });
  });

  $("#logout").on("click", function() {
    localStorage.clear();
    location.reload();
  });
});
