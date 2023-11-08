function flipCard() {
    const card = document.querySelector('.card-account');
    card.style.transform = card.style.transform === 'rotateY(180deg)' ? '' : 'rotateY(180deg)';
}

function deleteCartItem(button, url) {
   var cartID = button.getAttribute("data-cartID");
  	window.location.href = url+cartID;
}

function deleteCartByCustomerID(button, url) {
   var customerID = button.getAttribute("data-customerID");
  	window.location.href = url+customerID;
}
