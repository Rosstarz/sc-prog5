import { header, token } from "../util/csrf.js";

//
// Get Game
async function getGame() {
    var gameId = window.location.href.substring(window.location.href.lastIndexOf('/') + 1);
    const response = await fetch('/api/games/' + gameId, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        }
    });

    if (response.status === 200) {
        const data = await response.json();

        // Set game title and description
        const gameTitle = document.getElementById('game-title');
        const editGameTitle = document.getElementById('edit-game-title');
        const gameDescription = document.getElementById('game-description');
        const editGameDescription = document.getElementById('edit-game-description');
        gameTitle.innerHTML = data.title;
        editGameTitle.value = data.title;
        gameDescription.innerHTML = data.description;
        editGameDescription.value = data.description;

        // Set game developer
        const developerTitle = document.getElementById('developer-title');
        const editDeveloper = document.getElementById('edit-developer');
        developerTitle.innerHTML = data.developer.name + ' (' + data.developer.country + ')';

        // Set game store list
        const storeList = document.getElementById('store-list');
        data.stores.forEach(store => {
            const storeItem = document.createElement('li');
            storeItem.className = 'list-group-item';
            storeItem.innerHTML = '<a href="/stores/' + store.store.id + '">' + store.store.name + '</a> - ' + store.price + '$';
            storeList.appendChild(storeItem);
        });
    } else {
        window.location.href = "/404";
    }
}

// Get Developers for select list in Edit Game Modal
async function setDevelopersList(){
    await fetch('/api/developers', {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(response => response.json())
        .then(data => {
            const selectDeveloper = document.getElementById('select-developer');
            // console.log(window.location.href.substring(window.location.href.lastIndexOf('/') + 1));
            data.forEach(developer => {
                const option = document.createElement('option');
                option.value = developer.name;
                option.text = developer.name;
                option.id = developer.id;
                // console.log(developer.gameIds);
                if (developer.gameIds.includes(parseInt(window.location.href.substring(window.location.href.lastIndexOf('/') + 1)))) {
                    console.log('selected');
                    option.selected = true;
                }
                selectDeveloper.appendChild(option);
            });
        });
}

//
// Update Game Info
const title = document.getElementById("edit-game-title");
const description = document.getElementById("edit-game-description");
const developer = document.getElementById("select-developer");
const addButton = document.getElementById("addButton");
const addModal = document.getElementById("editModalCenter");
addButton?.addEventListener("click", updateGame);

async function updateGame() {
    const response = await fetch('/api/games/' + window.location.href.substring(window.location.href.lastIndexOf('/') + 1), {
        method: "PATCH",
        headers: {
            "Accept": "application/json",
            "Content-Type": "application/json",
            [header]: token
        },
        body: JSON.stringify({
            title: title.value,
            description: description.value,
            developerId: developer.options[developer.selectedIndex].id
        })
    });
    if (response.status === 201) {
        const game = await response.json();
        // updateGameHtml(game);
        getGame();
        console.log(game);
        alert("Added successfully!");
        $(addModal).modal('hide');
    } else {
        alert("Something went wrong!"); // alerts are "bad"...
    }
}

window.addEventListener('load', () => getGame(), setDevelopersList());
