import { header, token } from './util/csrf.js'
import { Modal } from 'bootstrap'
import validator from 'validator'

// Add Game to DB
const title = document.getElementById('title')
const description = document.getElementById('description')
const developer = document.getElementById('developer')
const addButton = document.getElementById('addButton')
const addModal = document.getElementById('addModalCenter')
const bootstrapModal = new Modal(addModal)
const gamesTableBody = document.getElementById('gamesTableBody')
addButton?.addEventListener('click', addNewGame)

function validateNewGame() {
    if (!validator.isAlphanumeric(title.value) || validator.isEmpty(title.value)) {
        alert('Title is invalid!')
        return false
    }
    if (!validator.isAlphanumeric(description.value) || validator.isEmpty(description.value)) {
        alert('Description is invalid!')
        return false
    }
    if (developer.selectedIndex === 0) {
        alert('Developer is required!')
        return false
    }
    return true
}

async function addNewGame() {
    if (!validateNewGame()) {
        return
    }
    const response = await fetch('/api/games', {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            [header]: token
        },
        body: JSON.stringify({
            title: title.value,
            description: description.value,
            developerId: developer.options[developer.selectedIndex].id
        })
    })
    if (response.status === 201) {
        const game = await response.json()
        addGameToTable(game)
        // console.log(game)
        // alert('Added successfully!')
        console.log('Added successfully!')
        hideModal()
    } else {
        alert('Something went wrong!')
    }
}

// Function to hide the modal
function hideModal() {
    bootstrapModal.hide()

    const modalBackdrop = document.querySelector('.modal-backdrop')
    if (modalBackdrop) {
        modalBackdrop.parentNode.removeChild(modalBackdrop)
    }
}

// New Game show in table
function addGameToTable(game) {
    const tableRow = document.createElement('tr')
    tableRow.id = `game_${game.id}`
    tableRow.innerHTML = `
        <tr id=''game_' + ${game.id}'>
            <td>${game.title}</td>
            <td>${game.developer.name}</td>
            <td>${game.description}</td>
            <td>
                <a href='/games/${game.id}' class='btn btn-primary btn-sm'>Details</a>
                <button type='button' class='btn btn-danger btn-sm'>Delete</button>
            </td>
        </tr>
    `
    gamesTableBody.appendChild(tableRow)

    const newDeleteButton = tableRow.querySelector('button')
    newDeleteButton.addEventListener('click', handleDeleteGame)
}

//
// Delete Game
const deleteButtons = document.querySelectorAll('button.btn-danger')

for (const deleteButton of deleteButtons) {
    deleteButton.addEventListener('click', handleDeleteGame)
}

async function handleDeleteGame(event) {
    const rowId = event.target.parentNode.parentNode.parentNode.id
    const gameId = parseInt(rowId.substring(rowId.indexOf('_') + 1))
    const response = await fetch(`/api/games/${gameId}`, {
        method: 'DELETE',
        headers: {
            [header]: token
        }
    })
    if (response.status === 204) {
        const row = document.getElementById(`game_${gameId}`)
        row.parentNode.removeChild(row)
        console.log('Deleted successfully!')
    }
}
