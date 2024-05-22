import { header, token } from "../util/csrf.js";

// 
// Add Game to DB
const title = document.getElementById("title");
const description = document.getElementById("description");
const developer = document.getElementById("developer");
const addButton = document.getElementById("addButton");
const addModal = document.getElementById("addModalCenter");
const gamesTableBody = document.getElementById("gamesTableBody");
addButton?.addEventListener("click", addNewGame);

async function addNewGame() {
    const response = await fetch('/api/games', {
        method: "POST",
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
        addGameToTable(game);
        console.log(game);
        alert("Added successfully!");
        $(addModal).modal('hide');
    } else {
        alert("Something went wrong!"); // alerts are "bad"...
    }
}

// New Game show in table
function addGameToTable(game) {
    const tableRow = document.createElement("tr");
    tableRow.id = `game_${game.id}`;
    
    tableRow.innerHTML = `
        <tr id="'game_' + ${game.id}">
            <td>${game.title}</td>
            <td>${game.developer.name}</td>
            <td>${game.description}</td>
            <td>
                <a href="/games/${game.id}" class="btn btn-primary btn-sm">Details</a>
                <button type="button" class="btn btn-danger btn-sm">Delete</button>
            </td>
        </tr>
    `
    gamesTableBody.appendChild(tableRow);

    const newDeleteButton = tableRow.querySelector('button');
    newDeleteButton.addEventListener("click", handleDeleteGame)
}

//
// Delete Game
const deleteButtons = document.querySelectorAll('button.btn-danger');

for (const deleteButton of deleteButtons) {
    deleteButton.addEventListener("click", handleDeleteGame);
}

async function handleDeleteGame(event) {
    const rowId = event.target.parentNode.parentNode.id;
    const gameId = parseInt(rowId.substring(rowId.indexOf('_') + 1));
    const response = await fetch(`/api/games/${gameId}`, {
        method: "DELETE",
        headers: {
            [header]: token
        }
    })
    if (response.status === 204) {
        const row = document.getElementById(`game_${gameId}`);
        row.parentNode.removeChild(row);
    }
}
