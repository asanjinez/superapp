package com.superapp.validator_service.validator;

import com.superapp.validator_service.item.ItemDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/validator")
public class ValidatorController {
    @Autowired
    private IValidatorService validatorService;

    /**
     * Return status of a specific product
     * @return Return true or false if the product is in stock
     */
    @GetMapping("/{sku}")
    public boolean isInStock(@PathVariable String sku) {
        return validatorService.isInStock(sku);
    }

    /**
     * Check if a list of products are in stock
     * @param itemDtoList of products
     * @return BaseResponse with list of errors if a product is out of stock
     */
    @PostMapping("/in-stock")
    public BaseResponse areInStock(@RequestBody List<ItemDto> itemDtoList) {
        return validatorService.areInStock(itemDtoList);
    }

}
