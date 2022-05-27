$(function () {
    $("#journal-submit").on("click", function () {
        const token = localStorage.getItem('Authorization');

        let data = {
            quote: $('#journal-quote').val(),
            emotion: $('#journal-emotion').val(),
            imageUrl: $('#journal-imageUrl').val(),
            date: $('#journal-date').val()
        }

        $.ajax({
            type: 'POST',
            url: '/journals',
            data: JSON.stringify(data),
            contentType: 'application/json; charset=utf-8',
            dataType: 'json',
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Authorization", token);
            },
            success: function (json) {
                window.location.href = "/view/journals";
            },
            error: function (json) {
            }
        });
    });
});