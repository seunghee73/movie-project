$(function () {
    $("#posting-submit").on("click", function () {
        const token = localStorage.getItem('Authorization');

        let data = {
            title: $('#posting-title').val(),
            content: $('#posting-content').val()
        }

        $.ajax({
            type: 'POST',
            url: '/postings',
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