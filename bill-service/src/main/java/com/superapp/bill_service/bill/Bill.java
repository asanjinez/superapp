package com.superapp.bill_service.bill;

import com.superapp.bill_service.item.Item;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "bill")
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDateTime date;
    @OneToMany(cascade=CascadeType.ALL)
    private List<Item> items;
    private Float total;
}
