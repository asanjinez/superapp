package com.superapp.cart;

import com.superapp.item.Item;
import com.superapp.person.Person;
import com.superapp.product.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name="cart")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToMany(cascade=CascadeType.ALL)
    private List<Item> itemList = new ArrayList<>();

    @OneToOne
    private Person person;

    public Cart(Person person) {
        this.person = person;
    }

    public void addItem(Product product, Float quantity) {
        Float totalQuantity = product.getUnitPrice()*quantity;
        Item item = new Item(product, quantity, totalQuantity);
        itemList.add(item);
    }

    public void removeItem(Integer idItem) {
        itemList = itemList.stream().filter(i -> i.getId() != idItem).collect(Collectors.toList());
    }

    public void removeAllItems() {
        itemList = new ArrayList<>();
    }
}
