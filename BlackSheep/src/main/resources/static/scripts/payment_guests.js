if (document.readyState == 'loading') {
    document.addEventListener('DOMContentLoaded', ready)
} else {
    ready()
}

function ready() {
    document.getElementById('payment-form').onsubmit = function (e) {
        e.preventDefault();

        fetch('/payment_guests', {
            method: 'POST',
            body: JSON.stringify({
                'name': document.getElementById('todolist-input').value
                
            }),
            headers: {
                'Content-Type': 'application/json'
            }
        })
        .then(function (response) {
            return response.json();
        })
        .then(function (jsonResponse) {
            const liItem = document.createElement('LI');

            liItem.innerHTML = jsonResponse['name'];
            document.getElementById('lists').appendChild(liItem);
            document.getElementById('error').className = 'hidden';
            window.location.reload(true);
        })
        .catch(function (err) {
            document.getElementById('error').className = '';
        })
      }
$('#payment-form').validate({
                    roles: {
                        fullname: {
                            required: true,
                        },
                        email: {
                            required: true,
                        },
                        address: {
                            required: true,
                        },
                        city: {
                            required: true,
                        },
                        state: {
                            required: true,
                        },
                        zip: {
                            required: true,
                        },
                        cardname: {
                            required: true,
                        },
                        cardnumber: {
                            required: true,
                        },
                        expmonth: {
                            required: true,
                        },
                        expyear: {
                            required: true,
                        },
                        cvv: {
                            required: true,
                        },

                    },
                    messages: {
                        fullname: "Please input full name*",
                        email: "Please input email*",
                        city: "Please input city*",
                        address: "Please input address*",
                        state: "Please input state*",
                        zip: "Please input address*",
                        cardname: "Please input card name*",
                        cardnumber: "Please input card number*",
                        expmonth: "Please input exp month*",
                        expyear: "Please input exp year*",
                        cvv: "Please input cvv*",
                    },
                });
    }