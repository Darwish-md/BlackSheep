if (document.readyState == 'loading') {
	document.addEventListener('DOMContentLoaded', ready)
} else {
	ready()
}

function ready() {
	
 	var checkout = document.getElementsByClassName('checkout-btn')[0]
    checkout.addEventListener("click", checkoutClicked)
}

function checkoutClicked() {

	localStorage.setItem("items", null)
	localStorage.setItem("totalPrice", 0)
}