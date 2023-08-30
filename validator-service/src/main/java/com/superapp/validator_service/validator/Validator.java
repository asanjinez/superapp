package com.superapp.validator_service.validator;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "validator")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Validator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String codeProduct;
    private Integer stock;
}
