$(function () {
    $("#comment-submit").on("click", function () {
        const token = localStorage.getItem('Authorization');
        const pathname = window.location.pathname.split("/");
        const id = pathname[3];

        let data = {
            comment: $('#comment-add').val()
        }

        $.ajax({
            type: 'POST',
            url: '/comments/postings/' + id,
            data: JSON.stringify(data),
            contentType: 'application/json; charset=utf-8',
            dataType: 'json',
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Authorization", token);
            },
            success: function (json) {
                location.reload();
            },
            error: function (json) {
            }
        });
    });
});
