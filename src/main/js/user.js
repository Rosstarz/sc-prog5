import { userDetails } from './util/get-current-user.js'

const username = document.getElementById('username')
const role = document.getElementById('userrole')

// Get User
async function loadAccount(){
    const user = await userDetails()
    console.log(user)
    username.innerHTML = 'Username: ' + user.username
    // role.innerHTML = 'Role: ' + user.role
}

// Run on window load
window.addEventListener('load', () => loadAccount())
