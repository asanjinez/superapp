package com.superapp.item;

import com.superapp.product.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="item")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    private Product product;
    private Float quantity;
    private Float total;
    public Item(Product product, Float quantity, Float total){
        this.product = product;
        this.quantity = quantity;
        this.total = total;
    }
}
