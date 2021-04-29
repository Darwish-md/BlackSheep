if (document.readyState == 'loading') {
	document.addEventListener('DOMContentLoaded', ready)
} else {
	ready()
}

function ready() {
	form = document.getElementsByClassName("payment-form")[0];
	form.onsubmit = function(e) {
		e.preventDefault();
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