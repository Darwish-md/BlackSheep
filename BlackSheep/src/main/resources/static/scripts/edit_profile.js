if (document.readyState == 'loading') {
	document.addEventListener('DOMContentLoaded', ready)
} else {
	ready()
}

function ready() {
	form = document.getElementsByClassName("edit-profile-form")[0];
	form.onsubmit = function(e) {
		e.preventDefault();

		fetch('/edit_profile', {
			method: 'POST',
			body: JSON.stringify({
				'firstName': document.getElementById('firstName').value,
				'lastName': document.getElementById('lastName').value,
				'email': document.getElementById('email').value,
				'password': document.getElementById('password').value,
				'phone': document.getElementById('phone').value,
				'city': document.getElementById('city').value,
				'state': document.getElementById('state').value,
				'streetAddress': document.getElementById('streetAddress').value,
				'postalCode': document.getElementById('postalCode').value,
			}),
			headers: {
				'Accept': 'application/json',
				'Content-Type': 'application/json',
				'X-CSRF-TOKEN': document.getElementsByClassName("csrf_token")[0].value
			}
		})
			.then(function(res) { console.log(res) })
			.catch(function(err) {
				console.log(err);
			})
	}
}