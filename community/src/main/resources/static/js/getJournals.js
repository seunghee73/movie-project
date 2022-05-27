function showJournal(journal) {
    const writer = document.querySelector("#journal-writer");
    if (!writer) {
        return;
    }
    writer.innerText = journal['writer'] + "'s Diary";

    const journalListContainer = document.querySelector("#journal-view-container");

    const journalContainer = document.createElement("div");
    journalContainer.classList.add("col");
    journalContainer.classList.add("mb-5");
    journalContainer.classList.add("d-flex");
    journalContainer.classList.add("justify-cont");
// <h2><img src=${journal['imageUrl']}></h2>
    let block = `<div class="card">
                    <img src=${journal['imageUrl']}>
                    <div class="content">
                        
                        <h3>${journal['date']}</h3>
                        <h3>${journal['emotion']}</h3>
                        <p>${journal['quote']}</p>
                        
                        <a data-pk="${journal['id']}" onclick="removeJournal(this)">
                            <h3>DELETE</h3>
                        </a>
                    </div>
                </div>`;

    journalContainer.innerHTML = block;
    journalListContainer.appendChild(journalContainer);
    // journalListContainer.innerHTML = block;
}

function removeJournal(ths) {
    const token = localStorage.getItem('Authorization');
    const id = ths.getAttribute('data-pk');
    console.log(id);

    $.ajax({
        type: 'DELETE',
        url: '/journals/' + id,
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
    const pathname = window.location.pathname;
    console.log(pathname);
    if (pathname !== "/view/journals") {
        console.log("getJournals.js is only operated on /view/journals");
        return;
    }

    $.ajax({
        type: 'GET',
        url: '/journals',
        contentType: 'application/json; charset=utf-8',
        dataType: 'json',
        beforeSend: function (xhr) {
            xhr.setRequestHeader("Authorization", token);
        },
        success: function (json) {
            for (let i = 0; i < json.length; i++) {
                showJournal(json[i]);
                console.log(json[i]);
            }
        },
        error: function (json) {
        }
    });
});

