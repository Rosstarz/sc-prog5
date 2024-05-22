import { header, token } from "../util/csrf.js";
import { userGamesOwned } from "../util/user.js";

//
// Get Game
async function getGame() {
    var currGameId = window.location.href.substring(window.location.href.lastIndexOf('/') + 1);
    const response = await fetch('/api/games/' + currGameId, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        }
    });

    if (response.status === 200) {
        const data = await response.json();
        const gamesOwned = await userGamesOwned();
        console.log(gamesOwned);

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
        storeList.innerHTML = '';
        data.stores.forEach(store => {
            const storeItemLi = document.createElement('li');
            storeItemLi.className = 'list-group-item';
            const storeItem = document.createElement('div');
            storeItem.className = 'store-item';
            storeItem.id = 'gamestore_' + store.id;
            storeItem.innerHTML = '<span><a href="/stores/' + store.store.id + '">' + store.store.name + '</a> - ' + store.price + '$</span>';
            if (gamesOwned.find((userGame) => userGame.gameStore.gameId == currGameId && userGame.gameStore.storeId == store.store.id)){
                console.log("Owned on " + store.store.name);
                storeItem.innerHTML += '<button type="button" class="btn btn-sm btn-success isOwnedButton">Owned</button>';
            } else {
                console.log("Not owned on " + store.store.name);
                storeItem.innerHTML += '<button type="button" class="btn btn-sm btn-danger isOwnedButton">Add</button>';
            }
            storeItemLi.appendChild(storeItem);
            storeList.appendChild(storeItemLi);
        });
        setOwnedButtons();
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
            data.forEach(developer => {
                const option = document.createElement('option');
                option.value = developer.name;
                option.text = developer.name;
                option.id = developer.id;
                // console.log(developer.gameIds);
                if (developer.gameIds.includes(parseInt(window.location.href.substring(window.location.href.lastIndexOf('/') + 1)))) {
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
    if (response.status === 200) {
        const game = await response.json();
        getGame();
        // console.log(game);
        // alert("Updated successfully!");
        console.log("Updated successfully!");
        $(addModal).modal('hide');
    } else {
        alert("Something went wrong!");
    }
}

//
// Set game ownership on cick
function setOwnedButtons() {
    const isOwnedButtons = document.querySelectorAll('button.isOwnedButton');
    for (const isOwnedButton of isOwnedButtons) {
        isOwnedButton.addEventListener("click", setGameOwnership.bind(null, isOwnedButton));
    }
}

async function setGameOwnership(isOwnedButton) {
    console.log("Set game ownership");
    const storeId = isOwnedButton.parentElement.id.substring(isOwnedButton.parentElement.id.lastIndexOf('_') + 1);
    const response = await fetch('/api/users/games/' + storeId, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            [header]: token
        }
    });
    if (response.status === 200) {
        // const data = await response.json();
        // console.log(data);
        console.log("Set successfully!");
        getGame();
    } else {
        alert("Something went wrong!");
    }
}

// Run on window load
window.addEventListener('load', () => getGame(), setDevelopersList());
