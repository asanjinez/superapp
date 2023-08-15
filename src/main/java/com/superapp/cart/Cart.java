package com.superapp.cart;

import com.superapp.item.Item;
import com.superapp.persona.Person;
import com.superapp.product.Product;
import com.superapp.product.ProductDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="cart")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToMany
    private List<Item> itemList = new ArrayList<>();

    @OneToOne
    private Person person;

    public void addItem(Product product, Float quantity) {
        Float totalQuantity = product.getUnitPrice()*quantity;
        Item item = new Item(product, quantity, totalQuantity);
        itemList.add(item);
    }
}
