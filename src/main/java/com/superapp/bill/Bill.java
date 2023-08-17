package com.superapp.bill;

import com.superapp.item.Item;
import com.superapp.person.Person;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "factura")
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "person")
    @ManyToOne
    private Person person;

    @OneToMany(mappedBy = "item")
    private List<Item> items;

    @Column(name = "person")
    private Float total;
}
