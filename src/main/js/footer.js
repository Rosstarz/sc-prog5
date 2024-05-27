import _ from 'lodash'
import moment from 'moment'

function loadFooter() {
    document.getElementById('locales').addEventListener('change', function() {
        const selectedOption = this.value
        if (selectedOption !== '') {
            window.location.replace('?lang=' + selectedOption)
        }
    })

    document.getElementById('footer-rights').innerHTML = _.join(['Copyright &copy; ', moment().format('YYYY'), 'All Rights Reserved.'], ' ')
}

// Run on window load
document.addEventListener('DOMContentLoaded', () => loadFooter())
