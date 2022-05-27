function showPosting(posting) {
    const postingTitle = document.querySelector("#posting-title");
    const postingWriter = document.querySelector("#posting-writer");
    const postingContent = document.querySelector("#posting-content");
    if (!postingTitle || !postingWriter || !postingContent) {
        return;
    }

    postingTitle.innerText = posting['title'];
    postingWriter.innerText = posting['writer'];
    let contentBlock = `
    <div style="word-wrap: break-word;">${posting['content']}</div>
    `;
    // postingContent.innerText = posting['content'];
    postingContent.innerHTML = contentBlock;

    const modifyBtn = document.querySelector("#posting-modify-btn");
    modifyBtn.href = "/view/posting-modify-form/" + posting["id"];
}

$(function () {
    const token = localStorage.getItem('Authorization');
    const pathname = window.location.pathname.split("/");
    const id = pathname[3];
    console.log(id);

    $.ajax({
        type: 'GET',
        url: '/postings/' + id,
        contentType: 'application/json; charset=utf-8',
        dataType: 'json',
        beforeSend: function (xhr) {
            xhr.setRequestHeader("Authorization", token);
        },
        success: function (json) {
            showPosting(json);
        },
        error: function (json) {
        }
    });
});

