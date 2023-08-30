package com.superapp.bill_service.item;

import com.superapp.bill_service.bill.Bill;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="item")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String code_product;
    private Float price;
    private Float quantity;
    @ManyToOne
    @JoinColumn(name = "bill_id")
    private Bill bill;

}
