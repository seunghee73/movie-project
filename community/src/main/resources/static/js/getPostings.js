function showPostingList (posting) {
    const postingListContainer = document.querySelector("#posting-list");
    if (!postingListContainer) {
        return;
    }

    const postingContainer = document.createElement("tr");
    const postingContainerTitle = document.createElement("th");
    const postingContainerMemberName = document.createElement("td");
    const anchor = document.createElement("a");

    postingContainerTitle.classList.add("text-center");
    postingContainerTitle.scope = "row";
    postingContainerMemberName.classList.add("text-center");

    anchor.innerText = posting['title'];
    anchor.classList.add("h4");
    anchor.href = "/view/postings/" + posting["id"];
    postingContainerTitle.appendChild(anchor);
    // postingContainerTitle.innerText = posting['title'];
    postingContainerMemberName.innerText = posting['writer']
    postingContainerMemberName.classList.add("h4");

    postingContainer.appendChild(postingContainerTitle);
    postingContainer.appendChild(postingContainerMemberName);

    postingListContainer.appendChild(postingContainer);
}

$(function() {
    const token = localStorage.getItem('Authorization');

    $.ajax({
        type: 'GET',
        url: '/postings',
        contentType: 'application/json; charset=utf-8',
        dataType: 'json',
        beforeSend: function (xhr) {
            xhr.setRequestHeader("Authorization", token);
        },
        success: function (json) {
            for (let i = 0; i < json.length; i++) {
                showPostingList(json[i]);
                // console.log(json[i])
            }
        },
        error: function (json) {
        }
    });
});

