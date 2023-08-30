package com.superapp.bill_service.bill;

import com.superapp.exception.ExistingIdException;
import com.superapp.exception.InvalidBillException;
import com.superapp.exception.NoBillFoundException;
import com.superapp.exception.NoPersonFoundException;
import com.superapp.person.IPersonJpaDao;
import com.superapp.person.IPersonMapper;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Transactional
public class BillServiceImpl implements IBillService {
    @Autowired
    IBillJpaDao billJpaDao;
    @Autowired
    IPersonJpaDao personJpaDao;
    @Autowired
    IBillMapper billMapper;
    @Autowired
    IPersonMapper personMapper;

    @Override
    public List<Bill> findAll() {
        return billJpaDao.findAll();
    }

    @Override
    public Bill createBill(BillDto billDto) {
        if(billDto.getId() != null && billJpaDao.findById(billDto.getId()).isPresent())
            throw new ExistingIdException("Alredy exists a bill with id " + billDto.getId());

         if(billDto.getPersonDto() == null ||
            billDto.getPersonDto().getId() == null ||
            !personJpaDao.findById(billDto.getPersonDto().getId()).isPresent())
            throw new NoPersonFoundException("An existing person must be specified");
        if(billDto.getItems().size() == 0)
            throw new InvalidBillException("No products were loaded");
        return billJpaDao.save(billMapper.billDtoToBill(billDto));
    }

    @Override
    public Bill findById(Integer id) {
        return this.billExists(id);
    }

    @Override
    public Bill findByPersonId(Integer id) {
        Optional<Bill> bill = billJpaDao.findByPersonId(id);
        if(!bill.isPresent())
            throw new NoBillFoundException("This person does not have an associated bill");
        return bill.get();

    }

    @Override
    public Bill updateBill(BillDto billDto) {
        Bill billToEdit = this.billExists(billDto.getId());
        billToEdit.setPerson(personMapper.personDtoToPerson(billDto.getPersonDto()));
        billToEdit.setItems(billDto.getItems());
        billToEdit.setTotal(billDto.getTotal());

        return billJpaDao.save(billToEdit);
    }

    @Override
    public void deleteBill(Integer id) {
        Bill billToDelete = this.billExists(id);
        billJpaDao.delete(billToDelete);

    }

    private Bill billExists(Integer id){
        Optional<Bill> bill = billJpaDao.findById(id);
        if (!bill.isPresent())
            throw new NoBillFoundException("This bill doesn't exist");

        return bill.get();
    }
}
