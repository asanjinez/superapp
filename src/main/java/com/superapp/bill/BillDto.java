package com.superapp.bill;

import com.superapp.person.PersonDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class BillDto {
    private Integer id;
    private PersonDto person;
    private Float total;
}
