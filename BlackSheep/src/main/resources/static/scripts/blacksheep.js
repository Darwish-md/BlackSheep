if (document.readyState == 'loading') {
    document.addEventListener('DOMContentLoaded', ready)
} else {
    ready()
}

function ready() {
    showCartItems();

    const cartbtn = document.getElementsByClassName("cartbtn")[0];
    cartbtn.addEventListener("click", showCart);

    const cartX = document.getElementsByClassName("close")[0];
    cartX.addEventListener("click", closeCart);
    var removeCartItemButtons = document.getElementsByClassName('btn-danger')
    for (var i = 0; i < removeCartItemButtons.length; i++) {
        var button = removeCartItemButtons[i]
        button.addEventListener('click', removeCartItem)
    }

    var quantityInputs = document.getElementsByClassName('cart-quantity-input')
    for (var i = 0; i < quantityInputs.length; i++) {
        var input = quantityInputs[i]
        input.addEventListener('change', quantityChanged)
    }

    var addToCartButtons = document.getElementsByClassName('shop-item-button')
    for (var i = 0; i < addToCartButtons.length; i++) {
        var button = addToCartButtons[i]
        button.addEventListener('click', addToCartClicked)
    }

    document.getElementsByClassName('btn-purchase')[0].addEventListener('click', purchaseClicked)




}

function showCart() {
    const cart = document.getElementsByClassName("cart")[0];
    cart.style.display = "inline";

}

function closeCart() {
    const cart = document.getElementsByClassName("cart")[0];
    cart.style.display = "none";
}


function purchaseClicked() {
    alert('Thank you for your purchase')
    var cartItems = document.getElementsByClassName('cart-items')[0]

    while (cartItems.hasChildNodes()) {
        cartItems.removeChild(cartItems.firstChild)
    }
    localStorage.setItem("items", null)
    updateCartTotal()
}

function removeCartItem(event) {
    var buttonClicked = event.target
    buttonClicked.parentElement.parentElement.remove()
    updateCartTotal()
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
        let localItems = JSON.parse(localStorage.getItem("items"));
        for (let i = 0; i < localItems.length; i++) {
            if (localItems[i].id == id) {
                localItems[i].no = parseInt(newQuantity)
                console.log(localItems)
            }
        }
        localStorage.setItem('items', JSON.stringify(localItems));
    }

    else {
        alert('local storage is not working on your browser go & update it');
    }
}


function addToCartClicked(event) {
    var button = event.target
    var shopItem = button.parentElement.parentElement
    var id = shopItem.getElementsByClassName('shop-item-id')[0].innerText
    var title = shopItem.getElementsByClassName('shop-item-title')[0].innerText
    var price = shopItem.getElementsByClassName('shop-item-price')[0].innerText
    var imageSrc = shopItem.getElementsByClassName('shop-item-image')[0].src
    updateLocalStorage(event)   
    addItemToCart(id, title)
    updateCartTotal()
    
}

function updateLocalStorage(event) {
    let items = []
    var button = event.target
    var shopItem = button.parentElement.parentElement
    if (typeof (Storage) !== 'undefined') {
        let item = {
            id: shopItem.getElementsByClassName('shop-item-id')[0].innerText,
            name: shopItem.getElementsByClassName('shop-item-title')[0].innerText,
            price: parseFloat(shopItem.getElementsByClassName('shop-item-price')[0].innerText.replace('$','')),
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
                console.log('item in storage bitch');
            } else {
                localItems.push(item);
                localStorage.setItem('items', JSON.stringify(localItems));
            }

        }
    } else {
        alert('local storage is not working on your browser go & update it');
    }

}

function addItemToCart(id, title) {
    var cartRow = document.createElement('div')
    cartRow.classList.add('cart-row')
    var cartItems = document.getElementsByClassName('cart-items')[0]
    var cartItemNames = cartItems.getElementsByClassName('cart-item-title')
    for (var i = 0; i < cartItemNames.length; i++) {
        if (cartItemNames[i].innerText == title) {
            alert('This item is already added to the cart')
            return
        }
    }
    const localItems = JSON.parse(localStorage.getItem('items'))
    for (var i = 0; i < localItems.length; i++){
        if (localItems[i].id == id) {
            itemToShow = localItems[i]
        }
    }
    
    var cartRowContents = `
        <div class="cart-item cart-column">
            <img class="cart-item-image" src="${itemToShow.imageSrc}" width="100" height="100">
            <span class="cart-item-title">${itemToShow.name}</span>
        </div>
        <span class="cart-price cart-column">${itemToShow.price}</span>
        <div class="cart-quantity cart-column">
            <span class="cart-item-id" hidden>${itemToShow.id}</span>
            <input class="cart-quantity-input" type="number" value="1">
            <button class="btn btn-danger" type="button">REMOVE</button>
        </div>`
    cartRow.innerHTML = cartRowContents
    cartItems.append(cartRow)
    cartRow.getElementsByClassName('btn-danger')[0].addEventListener('click', removeCartItem)
    cartRow.getElementsByClassName('cart-quantity-input')[0].addEventListener('change', quantityChanged)
}

function showCartItems(){
    
    const localItems = JSON.parse(localStorage.getItem('items'))
    if(!localItems){
        length = 0;
    }else{
        length = localItems.length
    }
    for (var i = 0; i < length; i++){
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
            <button class="btn btn-danger" type="button">REMOVE</button>
        </div>`
    cartRow.innerHTML = cartRowContents
    cartItems.append(cartRow)
        }
        var totalPrice = document.getElementsByClassName('cart-total-price')[0]
        totalPrice.innerText = '$' + JSON.parse(localStorage.getItem('totalPrice'))
        
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