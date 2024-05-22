export const userDetails = async function getCurrentUser() {
    const response = await fetch('/api/users/current', {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        }
    });

    if (response.status === 200) {
        return await response.json();
    } else {
        return null;
    }
}

export const userGamesOwned = async function getUserGamesOwned() {
    const response = await fetch('/api/users/games', {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        }
    });

    if (response.status === 200) {
        return await response.json();
    } else {
        return null;
    }
}