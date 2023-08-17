package com.superapp.cart;

import com.superapp.item.Item;

import java.util.ArrayList;
import java.util.List;

public class CartDto {
    private Integer id;
    private List<Item> itemList = new ArrayList<>();
    private Integer personId;
}
