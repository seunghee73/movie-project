function showHolidayMovieList(movie) {
    const movieListContainer = document.querySelector("#holiday-movie-list-container");

    // <tr>
    //     <th scope="row" className="text-center">Great Movie title</th>
    // </tr>

    const movieContainer = document.createElement("tr");

    let block = `
                <th scope="row" class="text-center">
                    <h3>
                        <a href="/view/movies/${movie['id']}">
                        ${movie['title']}
                        </a>
                    </h3>
                </th>
                `;
    // const movieTitle = document.createElement("th");
    // const anchor = document.createElement("a");
    //
    // movieTitle.classList.add("text-center");
    // movieTitle.scope = "row";
    // // movieTitle.innerText = movie['title'];
    //
    // anchor.href = "/view/movies/" + movie['id'];
    // anchor.innerText = movie['title'];
    // movieTitle.appendChild(anchor);

    // movieContainer.appendChild(movieTitle);

    movieContainer.innerHTML = block;

    movieListContainer.appendChild(movieContainer);
}

$(function () {
    const token = localStorage.getItem('Authorization');
    const pathname = window.location.pathname.split("/");
    const id = pathname[3];

    $.ajax({
        type: 'GET',
        url: '/holiday/' + id + "/movies",
        contentType: 'application/json; charset=utf-8',
        dataType: 'json',
        beforeSend: function (xhr) {
            xhr.setRequestHeader("Authorization", token);
        },
        success: function (json) {
            let len = 10;
            if (json.length < len) {
                len = json.length;
            }
            for (let i = 0; i < len; i++) {
                showHolidayMovieList(json[i]);
                // console.log(json[i])
            }
        },
        error: function (json) {
        }
    });
});

