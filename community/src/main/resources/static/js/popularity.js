function showPopularity(movie) {
    // id="popularity-movies" 에 포스터 추가할 것임.
    // <a href="해당 영화 id의 상세 페이지 path">
    //     <img src="(포스터이미지)movie['imageUrl']">
    // </a>
    const popularityMovies = document.querySelector("#popularity-movie-list");
    if (!popularityMovies) {
        return;
    }

    // a tag 만들기
    const imageLink = document.createElement("a");
    // class 속성 추가
    imageLink.classList.add("col");
    imageLink.classList.add("mb-5");
    imageLink.classList.add("d-flex");
    imageLink.classList.add("justify-content-center");
    imageLink.href = "/view/movies/" + movie['id'];

    // img tag 만들기
    const imageUrl = document.createElement("img");
    // src path 추가
    imageUrl.src = movie["imageUrl"];
    imageUrl.width = 300;
    imageUrl.height = 400;
    // a tag 에 img 넣어주기

    imageLink.appendChild(imageUrl);

    // popularity-movies 에 해당 a tag 넣어주기
    popularityMovies.appendChild(imageLink);
}

$(function () {
    const token = localStorage.getItem('Authorization');
    const pathname = window.location.pathname;
    console.log("popularity.js : " + pathname);
    if (pathname !== "/") {
        console.log("popularity.js is only operated on /");
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
            // popularity 기준 상위 6개 가져와야 함. 이거
            json.sort(function (a, b) {
                return b["popularity"] - a["popularity"];
            });

            // 상위 6개 인기 영화
            for (let i = 0; i < 6; i++) {
                showPopularity(json[i]);
            }
        },
        error: function (json) {
            console.log("error");
        }
    });
});

