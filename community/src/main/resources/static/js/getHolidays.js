function showHolidayList (holiday) {
    const holidayListContainer = document.querySelector("#holiday-list");

    const holidayContainer = document.createElement("tr");
    const holidayName = document.createElement("th");
    const anchor = document.createElement("a");

    holidayName.classList.add("text-center");
    holidayName.scope = "row";
    anchor.innerText = holiday['holiday'];
    anchor.classList.add("h3");
    anchor.href = "/view/holiday/" + holiday["id"];
    // holidayName.innerText = holiday['holiday'];

    holidayName.appendChild(anchor);
    holidayContainer.appendChild(holidayName);

    holidayListContainer.appendChild(holidayContainer);
}

$(function() {
    const token = localStorage.getItem('Authorization');
    const pathname = window.location.pathname;
    console.log(pathname);
    if (pathname !== "/view/holiday-list") {
        console.log("holiday.js is only operated on /view/holiday-list");
        return;
    }

    $.ajax({
        type: 'GET',
        url: '/holiday',
        contentType: 'application/json; charset=utf-8',
        dataType: 'json',
        beforeSend: function (xhr) {
            xhr.setRequestHeader("Authorization", token);
        },
        success: function (json) {
            // popularity 기준 상위 6개 가져와야 함. 이거
            let len = 10;
            if (json.length < len) {
                len = json.length;
            }
            for (let i = 0; i < len; i++) {
                showHolidayList(json[i]);
                // console.log(json[i])
            }
        },
        error: function (json) {
        }
    });
});

