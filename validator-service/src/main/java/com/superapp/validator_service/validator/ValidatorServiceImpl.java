package com.superapp.validator_service.validator;

import com.superapp.validator_service.item.ItemDto;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional
public class ValidatorServiceImpl implements IValidatorService{
    @Autowired
    private IValidatorJpaDao validatorJpaDao;

    @Override
    public List<Validator> findAll() {
        return validatorJpaDao.findAll();
    }

    @Override
    public boolean isInStock(String code_product) {
        var validator = validatorJpaDao.findByCodeProduct(code_product);
        return validator.filter((validator1 -> validator1.getStock() > 0)).isPresent();
    }

    @Override
    public BaseResponse areInStock(List<ItemDto> items) {
        var errorList = new ArrayList<String>();
        List<String> codeList = items.stream().map(ItemDto::getCode_product).collect(Collectors.toList());

        var validatorsList = validatorJpaDao.findByCodeProductIn(codeList);

        items.forEach((item) -> {
            Optional<Validator> validator = validatorsList.stream().filter((val) -> val.getCodeProduct().equals(item.getCode_product())).findFirst();

            if (validator.isEmpty()){
                errorList.add("Product with code " + item.getCode_product() + " does not exist");

            } else if (validator.get().getStock() < item.getQuantity()) {
                errorList.add("Product with code " + item.getCode_product() + " has no stock");
            }
        });

        return errorList.size() > 0 ? new BaseResponse(errorList.toArray(new String[0])) : new BaseResponse(new String[0]);
    }
}
