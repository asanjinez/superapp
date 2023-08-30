package com.superapp.bill_service.bill;

import com.superapp.bill_service.exception.ExistingIdException;
import com.superapp.bill_service.exception.InvalidBillException;
import com.superapp.bill_service.exception.NoBillFoundException;
import com.superapp.bill_service.exception.ValidatorServiceException;
import com.superapp.bill_service.item.IItemMapper;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientRequestException;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Transactional
public class BillServiceImpl implements IBillService {
    @Autowired
    private IBillJpaDao billJpaDao;
    @Autowired
    private IBillMapper billMapper;
    @Autowired
    private IItemMapper itemMapper;

    @Autowired
    private WebClient webValidatorClient;

    @Override
    public List<Bill> findAll() {
        return billJpaDao.findAll();
    }

    @Override
    public Bill createBill(BillDto billDto) {
        try {
            if(billDto.getId() != null && billJpaDao.findById(billDto.getId()).isPresent())
                throw new ExistingIdException("Already exists a bill with id " + billDto.getId());

            if(billDto.getDtoItems() == null || billDto.getDtoItems().size() == 0)
                throw new InvalidBillException("No products were loaded");

            BaseResponse response = webValidatorClient.
                    post()
                    .uri("validator/in-stock")
                    .bodyValue(billDto.getDtoItems())
                    .retrieve()
                    .bodyToMono(BaseResponse.class)
                    .block();

            if (response.hasErrors())
                throw new InvalidBillException("Some products are not in stock");

            return billJpaDao.save(billMapper.billDtoToBill(billDto));

        } catch (WebClientRequestException exception) {
            log.info(exception.getMessage(), exception);
            throw new ValidatorServiceException("Error with Validator-microservice");
        } catch (ExistingIdException | InvalidBillException exception) {
            log.info(exception.getMessage(), exception);
            throw exception;
        } catch (Exception exception){
            log.info(exception.getMessage(), exception);
            throw exception;
        }
    }

    @Override
    public Bill findById(Integer id) {
        try {
            return this.billExists(id);

        } catch (NoBillFoundException exception) {
            log.info(exception.getMessage(), exception);
            throw exception;
        } catch (Exception exception){
            log.info(exception.getMessage(), exception);
            throw exception;
        }
    }

    @Override
    public Bill updateBill(BillDto billDto) {
        try {
            if(billDto.getDtoItems().size() == 0)
                throw new InvalidBillException("No products were loaded");

            Bill billToEdit = this.billExists(billDto.getId());
            billToEdit.setId(billDto.getId());
            billToEdit.setItems(itemMapper.itemDtoListToItemList(billDto.getDtoItems()));
            billToEdit.setTotal(billDto.getTotal());

            return billJpaDao.save(billToEdit);
        } catch (NoBillFoundException|InvalidBillException exception) {
            log.info(exception.getMessage(), exception);
            throw exception;
        } catch (Exception exception){
            log.info(exception.getMessage(), exception);
            throw exception;
        }

    }

    @Override
    public void deleteBill(Integer id) {
        try {
            Bill billToDelete = this.billExists(id);
            billJpaDao.delete(billToDelete);

        } catch (NoBillFoundException exception) {
            log.info(exception.getMessage(), exception);
            throw exception;
        } catch (Exception exception){
            log.info(exception.getMessage(), exception);
            throw exception;
        }
    }

    private Bill billExists(Integer id){
        Optional<Bill> bill = billJpaDao.findById(id);
        if (!bill.isPresent())
            throw new NoBillFoundException("The bill with id: " + id + " doesn't exist");

        return bill.get();
    }
}
