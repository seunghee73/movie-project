function showMovieList(movie) {
    const movieListContainer = document.querySelector("#movie-view-list");
    if (!movieListContainer) {
        return;
    }

    const movieContainer = document.createElement("tr");
    const movieTitle = document.createElement("th");
    const anchor = document.createElement("a");
    console.log("ac : " + anchor);

    movieTitle.classList.add("text-center");
    movieTitle.scope = "row";
    // movieTitle.innerText = movie['title'];

    anchor.href = "/view/movies/" + movie['id'];
    anchor.classList.add("h4");
    anchor.innerText = movie['title'];
    movieTitle.appendChild(anchor);

    movieContainer.appendChild(movieTitle);

    console.log(movieContainer);
    console.log(movieListContainer);
    movieListContainer.appendChild(movieContainer);
}

$(function () {
    const token = localStorage.getItem('Authorization');
    const pathname = window.location.pathname;
    console.log("getMovies.js : " + pathname);
    if (pathname !== "/view/movies") {
        console.log("ndlafnlefnksfnlenfklesn");
        return;
    }

    $.ajax({
        type: 'GET',
        url: '/movies',
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
                showMovieList(json[i]);
                console.log(json[i]);
            }
        },
        error: function (json) {
        }
    });
});

