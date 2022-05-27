function showMovie(movie) {
    const imageContainer = document.querySelector("#movie-image");
    if (!imageContainer) {
        return;
    }
    const image = document.createElement("img");
    image.src = movie['imageUrl'];
    image.width = 300 * 1;
    image.height = 400 * 1;

    imageContainer.appendChild(image);


    const titleContainer = document.querySelector("#title");
    const title = document.createElement("h1");
    title.innerText = movie['title'];

    titleContainer.appendChild(title);


    const scoreContainer = document.querySelector("#score");
    const score = document.createElement("h4");
    score.innerText = "SCORE : " + movie['score'];

    scoreContainer.appendChild(score);


    const desContainer = document.querySelector("#description");
    const description = document.createElement("h4");
    description.innerText = movie['description'];

    desContainer.appendChild(description);


    const dateContainer = document.querySelector("#release-date");
    const releaseDate = document.createElement("h4");
    releaseDate.innerText = "RELEASE DATE : " + movie['releasedDate'];

    dateContainer.appendChild(releaseDate);
}


function showRecommendations(movie) {
    const recommendationContainer = document.querySelector("#movie-recommendation-list");
    if (!recommendationContainer) {
        return;
    }

    const anchor = document.createElement("a");
    anchor.href = "/view/movies/" + movie['id'];

    const img = document.createElement("img");
    img.src = movie['imageUrl'];
    img.width = 300 * 0.7;
    img.height = 400 * 0.7;

    anchor.appendChild(img);

    recommendationContainer.appendChild(anchor);
}


$(function () {
    const token = localStorage.getItem('Authorization');
    const pathname = window.location.pathname.split("/");
    const id = pathname[3];

    $.ajax({
        type: 'GET',
        url: '/movies/' + id,
        contentType: 'application/json; charset=utf-8',
        dataType: 'json',
        beforeSend: function (xhr) {
            xhr.setRequestHeader("Authorization", token);
        },
        success: function (json) {
            showMovie(json);
        },
        error: function (json) {
        }
    });

    $.ajax({
        type: 'GET',
        url: '/recommendations/' + id,
        contentType: 'application/json; charset=utf-8',
        dataType: 'json',
        beforeSend: function (xhr) {
            xhr.setRequestHeader("Authorization", token);
        },
        success: function (json) {
            for (let i = 0; i < json.length; i++) {
                showRecommendations(json[i]);
                console.log(json[i]);
            }
        },
        error: function (json) {
        }
    });
});

