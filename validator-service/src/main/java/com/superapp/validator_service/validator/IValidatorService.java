package com.superapp.validator_service.validator;

import com.superapp.validator_service.item.ItemDto;

import java.util.List;

public interface IValidatorService {
    public List<Validator> findAll();
    public boolean isInStock(String code_product);
    public BaseResponse areInStock(List<ItemDto> items);
}
