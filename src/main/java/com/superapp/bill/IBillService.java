package com.superapp.bill;


import java.util.List;

public interface IBillService {
    public List<Bill> findAll();
    public Bill createBill(BillDto billDto);
    public Bill findById(Integer id);
    Bill findByPersonId(Integer id);
    Bill updateBill(BillDto billDto);
    public void deleteBill(Integer id);
}
