package com.superapp.cart;

import com.superapp.product.ProductDto;

public interface ICartService {
    public Cart addItem(Integer idCart, ProductDto productDto, Float quantity);
    public Cart deleteItem(Integer idCart, Integer idItem);
    public Cart deleteAllItems(Integer idCart);
}
