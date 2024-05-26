document.addEventListener('DOMContentLoaded', function() {
    document.getElementById('locales').addEventListener('change', function() {
        const selectedOption = this.value
        if (selectedOption !== '') {
            window.location.replace('?lang=' + selectedOption)
        }
    })
})
