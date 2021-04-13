if (document.readyState == 'loading') {
	document.addEventListener('DOMContentLoaded', ready)
} else {
	ready()
}

function ready() {

	var addToCartButtons = document.getElementsByClassName('shop-item-button')
	for (var i = 0; i < addToCartButtons.length; i++) {
		var button = addToCartButtons[i]
		button.addEventListener('click', addToCartClicked)
	}
	
}

function addToCartClicked(event) {
	addToLocalStorage(event)
	updateStorageTotal()

}

function addToLocalStorage(event) {
	let items = []
	var button = event.target
	var shopItem = button.parentElement.parentElement
	if (typeof (Storage) !== 'undefined') {
		let item = {
			id: parseInt(shopItem.getElementsByClassName('shop-item-id')[0].innerText),
			name: shopItem.getElementsByClassName('shop-item-title')[0].innerText,
			price: parseFloat(shopItem.getElementsByClassName('shop-item-price')[0].innerText.replace('$', '')),
			imageSrc: shopItem.getElementsByClassName('shop-item-image')[0].src,
			no: 1
		};
		if (JSON.parse(localStorage.getItem('items')) === null) {
			items.push(item);
			localStorage.setItem("items", JSON.stringify(items));
		} else {
			let localItems = JSON.parse(localStorage.getItem("items"));
			console.log(localItems)
			let there = false;
			for (let i = 0; i < localItems.length; i++) {
				if (localItems[i].id == item.id) {
					there = true;
				}
			}
			if (there == true) {
				alert('Item is already added to the cart.');
			} else {
				localItems.push(item);
				localStorage.setItem('items', JSON.stringify(localItems));
			}

		}
	} else {
		alert('local storage is not working on your browser go & update it');
	}

}

function updateStorageTotal() {
	const localItems = JSON.parse(localStorage.getItem("items"));
	var total = 0
	for (let i = 0; i < localItems.length; i++) {
		var price = localItems[i].price
		var quantity = localItems[i].no
		total = total + ( price * quantity)
	}
	total = Math.round(total * 100) / 100
	localStorage.setItem("totalPrice", JSON.stringify(total))
}


