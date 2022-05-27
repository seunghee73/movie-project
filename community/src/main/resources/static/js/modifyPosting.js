$(function () {
    $("#update-posting-submit").on("click", function () {
        const token = localStorage.getItem('Authorization');
        const pathname = window.location.pathname.split("/");
        const id = pathname[3];
        console.log(id);

        let data = {
            title: $('#update-posting-title').val(),
            content: $('#update-posting-content').val()
        }

        $.ajax({
            type: 'PATCH',
            url: '/postings/' + id,
            data: JSON.stringify(data),
            contentType: 'application/json; charset=utf-8',
            dataType: 'json',
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Authorization", token);
            },
            success: function (json) {
                window.location.href = "/view/postings";
            },
            error: function (json) {
            }
        });
    });
});