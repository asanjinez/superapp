package com.superapp.cart;

import com.superapp.item.Item;
import com.superapp.persona.Person;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

import java.util.ArrayList;
import java.util.List;

public class CartDto {
    private Integer id;
    private List<Item> itemList = new ArrayList<>();
    private Integer personId;
}
