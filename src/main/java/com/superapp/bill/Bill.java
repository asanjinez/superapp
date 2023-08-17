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
@Table(name = "bill")
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "person_fk")
    private Person person;

    @OneToMany
    private List<Item> items;

    @Column(name = "total")
    private Float total;
}
