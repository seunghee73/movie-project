function showReviewList(review) {
    const reviewListContainer = document.querySelector("#review-view-list");
    if (!reviewListContainer) {
        return;
    }

    const date = review['date'].substring(0, 10);

    let block = `
            <th scope="row" class="text-center h3">${review['content']}</th>
            <td class="text-center h3">${review['reviewer']}</td>
            <td class="text-center h3">${date}</td>
            <td class="text-center h3">${review['score']}</td>
            
            <td>
                <a data-pk="${review['id']}" onclick="removeReview(this)" type="button" class="me-2 h3">
                    DELETE
                </a>
            </td>
            `;

            // <div class="d-flex justify-content-end">
            //     <a data-pk="${review['id']}" onclick="removeReview(this)" type="button" class="me-2 h3">
            //     DELETE
            //     </a>
            // </div>`;

    const reviewContainer = document.createElement("tr");
    reviewContainer.innerHTML = block;

    reviewListContainer.appendChild(reviewContainer);
}


function removeReview(ths) {
    const token = localStorage.getItem('Authorization');
    const id = ths.getAttribute('data-pk');
    console.log(id);

    $.ajax({
        type: 'DELETE',
        url: '/reviews/' + id,
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
        url: '/reviews/movies/' + id,
        contentType: 'application/json; charset=utf-8',
        dataType: 'json',
        beforeSend: function (xhr) {
            xhr.setRequestHeader("Authorization", token);
        },
        success: function (json) {
            for (let i = 0; i < json.length; i++) {
                showReviewList(json[i]);
            }
        },
        error: function (json) {
        }
    });
});

