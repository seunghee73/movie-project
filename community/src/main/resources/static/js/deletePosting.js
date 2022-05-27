$(function () {
    $("#posting-delete-btn").on("click", function () {
        const token = localStorage.getItem('Authorization');
        const pathname = window.location.pathname.split("/");
        const id = pathname[3];
        console.log(id);

        $.ajax({
            type: 'DELETE',
            url: '/postings/' + id,
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Authorization", token);
            },
            success: function (json) {
                window.location.href = "/";
            },
            error: function (json) {
            }
        });
    });
});