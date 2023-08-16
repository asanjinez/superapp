package com.superapp.cart;

import com.superapp.person.PersonDto;
import com.superapp.product.ProductDto;

public interface ICartService {
    public Cart createCart(PersonDto person);
    public Cart addItem(Integer idCart, ProductDto productDto, Float quantity);
    public Cart deleteItem(Integer idCart, Integer idItem);
    public Cart deleteAllItems(Integer idCart);
    public Cart getCart(Integer idPerson);
}
