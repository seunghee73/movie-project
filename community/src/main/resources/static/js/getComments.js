function showCommentList(comment) {
    const commentListContainer = document.querySelector("#comment-list-container");

    let block = `
                <div class="d-flex justify-content-between">
                    <h2 class="my-2 mx-5 text-secondary">${comment['commenter']} : ${comment['comment']}</h2>
                    <a data-pk="${comment['id']}" onclick="removeComment(this)" type="button" class="d-flex justify-content-end my-2 mx-5" style="color:black; text-decoration: none;">
                        <h3>DELETE</h3>
                    </a>
                </div>
                `;

    // let block = `
    //             <h3 class="my-2 mx-5 text-secondary">${comment['commenter']} : ${comment['comment']}</h3>
    //             <div class="d-flex justify-content-end">
    //                 <button data-pk="${comment['id']}" onclick="removeComment(this)"
    //                 type="button" class="btn btn-secondary me-2">삭제</button>
    //             </div>
    //             `;
    const div = document.createElement("div");
    div.innerHTML = block;
    commentListContainer.appendChild(div);
}


function removeComment(ths) {
    const token = localStorage.getItem('Authorization');
    const id = ths.getAttribute('data-pk');
    console.log(id);

    $.ajax({
        type: 'DELETE',
        url: '/comments/' + id,
        beforeSend: function (xhr) {
            xhr.setRequestHeader("Authorization", token);
        },
        success: function (json) {
            location.reload();
        },
        error: function (json) {
        }
    });
}


$(function () {
    const token = localStorage.getItem('Authorization');
    const pathname = window.location.pathname.split("/");
    const id = pathname[3];
    console.log(id);

    $.ajax({
        type: 'GET',
        url: '/comments/postings/' + id,
        contentType: 'application/json; charset=utf-8',
        dataType: 'json',
        beforeSend: function (xhr) {
            xhr.setRequestHeader("Authorization", token);
        },
        success: function (json) {
            for (let i = 0; i < json.length; i++) {
                showCommentList(json[i]);
                console.log(json[i]);
            }
        },
        error: function (json) {
        }
    });
});

