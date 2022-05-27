$(function () {
    const token = localStorage.getItem('Authorization');

    let loginBlock = `<li class="nav-item">
                            <a class="nav-link h2 mb-0" href="#" data-bs-toggle="modal"
                               data-bs-target="#exampleModal">LOGIN</a>
                        </li>`;

    let signUpBlock = `<li class="nav-item">
                            <a class="nav-link h2 mb-0" href="/sign-up">
                            <span style="color: black;"> || </span> SIGN UP</a>
                            
                        </li>`;

    let myDiaryBlock = `<li class="nav-item">
                            <a class="nav-link h2 mb-0" href="/view/journals">
                            <span style="color: black;"> || </span> MY DIARY</a>
                        </li>`;

    const logout = document.querySelector("#logout");

    const menuListContainer = document.querySelector("#nav-menu-list");
    const li1 = document.createElement("li");
    const li2 = document.createElement("li");
    const li3 = document.createElement("li");
    li1.classList.add("nav-item");
    li2.classList.add("nav-item");
    li3.classList.add("nav-item");

    if (token == null) {
        li1.innerHTML = loginBlock;
        li2.innerHTML = signUpBlock;
        logout.style.display = 'none';
    } else {
        li3.innerHTML = myDiaryBlock;
        logout.style.display = '';
    }

    menuListContainer.appendChild(li1);
    menuListContainer.appendChild(li2);
    menuListContainer.appendChild(li3);
});