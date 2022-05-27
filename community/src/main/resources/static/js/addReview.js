$(function () {
    $("#review-submit").on("click", function () {
        const token = localStorage.getItem('Authorization');
        const pathname = window.location.pathname.split("/");
        const id = pathname[3];

        let data = {
            content: $('#review-content').val(),
            score: $('#review-score').val()
        }

        $.ajax({
            type: 'POST',
            url: '/reviews/movies/' + id,
            data: JSON.stringify(data),
            contentType: 'application/json; charset=utf-8',
            dataType: 'json',
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Authorization", token);
            },
            success: function (json) {
                location.reload();
                // console.log(json);
            },
            error: function (json) {
            }
        });
    });
});