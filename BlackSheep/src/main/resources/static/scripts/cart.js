if (document.readyState == 'loading') {
	document.addEventListener('DOMContentLoaded', ready)
} else {
	ready()
}

function ready() {

	showCartItems();

	var quantityInputs = document.getElementsByClassName('cart-quantity-input')
	for (var i = 0; i < quantityInputs.length; i++) {
		var input = quantityInputs[i]
		input.addEventListener('change', quantityChanged)
	}

	var removeCartItemButtons = document.getElementsByClassName('btn-danger')
	for (var i = 0; i < removeCartItemButtons.length; i++) {
		var button = removeCartItemButtons[i]
		button.addEventListener('click', removeCartItem)
	}

}


function showCartItems() {

	const localItems = JSON.parse(localStorage.getItem('items'))
	if (!localItems) {
		length = 0;
	} else {
		length = localItems.length
	}
	for (var i = 0; i < length; i++) {
		var cartRow = document.createElement('div')
		cartRow.classList.add('cart-row')
		var cartItems = document.getElementsByClassName('cart-items')[0]

		itemToShow = localItems[i]
		var cartRowContents = `
      <div class="cart-item cart-column">
          <img class="cart-item-image" src="${itemToShow.imageSrc}" width="100" height="100">
          <span class="cart-item-title">${itemToShow.name}</span>
      </div>
      <span class="cart-price cart-column">${itemToShow.price}</span>
      <div class="cart-quantity cart-column">
          <span class="cart-item-id" hidden>${itemToShow.id}</span>
          <input class="cart-quantity-input" type="number" value=${itemToShow.no}>
          <button class="btn btn-danger" type="button">Remove</button>
      </div>`
		cartRow.innerHTML = cartRowContents
		cartItems.append(cartRow)
	}
	var totalPrice = document.getElementsByClassName('cart-total-price')[0]
	totalPrice.innerText = '$' + JSON.parse(localStorage.getItem('totalPrice'))

}

function quantityChanged(event) {
	var input = event.target
	if (isNaN(input.value) || input.value <= 0) {
		input.value = 1
	}
	updateCartTotal()
	updateStorageQuantity(event)
}

function updateStorageQuantity(event) {
	var button = event.target
	var shopItem = button.parentElement
	var newQuantity = button.value
	const id = shopItem.getElementsByClassName('cart-item-id')[0].innerText
	if (typeof (Storage) !== 'undefined') {
		var localItems = JSON.parse(localStorage.getItem("items"));
		for (let i = 0; i < localItems.length; i++) {
			if (localItems[i].id == id) {
				localItems[i].no = parseInt(newQuantity)
				console.log(localItems)
			}
		}
		localStorage.setItem('items', JSON.stringify(localItems));
		var totalItems = 0
		for (let i = 0; i < localItems.length; i++) {
			totalItems += localItems[i].no
		}
		document.getElementById("lblCartCount").innerText = totalItems
	}

	else {
		alert('local storage is not working on your browser go & update it');
	}
}

function removeCartItem(event) {
	var buttonClicked = event.target
	buttonClicked.parentElement.parentElement.remove()
	removeFromLocalStorage(event)
	updateCartTotal()
}

function removeFromLocalStorage(event) {
	var buttonClicked = event.target
	var id = buttonClicked.parentElement.children[0].innerText
	const localItems = JSON.parse(localStorage.getItem('items'))
	for (var i = 0; i < localItems.length; i++) {
		if (localItems[i].id == id) {
			localItems.splice(i, 1)
			localStorage.setItem('items', JSON.stringify(localItems))
			break
		}
	}
	var totalItems = 0
	for (let i = 0; i < localItems.length; i++) {
		totalItems += localItems[i].no
	}
	document.getElementById("lblCartCount").innerText = totalItems
}



function updateCartTotal() {
	var cartItemContainer = document.getElementsByClassName('cart-items')[0]
	var cartRows = cartItemContainer.getElementsByClassName('cart-row')
	var total = 0
	for (var i = 0; i < cartRows.length; i++) {
		var cartRow = cartRows[i]
		var priceElement = cartRow.getElementsByClassName('cart-price')[0]
		var quantityElement = cartRow.getElementsByClassName('cart-quantity-input')[0]
		var price = parseFloat(priceElement.innerText.replace('$', ''))
		var quantity = quantityElement.value
		total = total + (price * quantity)
	}
	total = Math.round(total * 100) / 100
	document.getElementsByClassName('cart-total-price')[0].innerText = '$' + total
	localStorage.setItem("totalPrice", JSON.stringify(total))
}