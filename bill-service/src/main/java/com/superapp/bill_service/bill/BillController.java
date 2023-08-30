package com.superapp.bill_service.bill;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/bills")
public class BillController {
    @Autowired
    private IBillService billService;
    @Autowired
    private IBillMapper billMapper;

    /**
     * Create a new bill.
     * @param billDto The bill data to create.
     * @return ResponseEntity containing the created bill or an error message.
     */
    @PostMapping("/create")
    public ResponseEntity createBill(@RequestBody BillDto billDto) {
        BillDto billCreated = billMapper.billToBillDto(billService.createBill(billDto));
        return new ResponseEntity<>(billCreated, HttpStatus.CREATED);
    }

    /**
     * Get a list of all bills.
     * @return ResponseEntity containing the list of bills or an error message.
     */
    @GetMapping("/list")
    public ResponseEntity findAll() {
        List<BillDto> bills = billMapper.billListToBillDtoList(billService.findAll());
        return new ResponseEntity<>(bills, HttpStatus.OK);
    }

    /**
     * Get a bill by its ID.
     * @param id The ID of the bill.
     * @return ResponseEntity containing the bill or an error message.
     */
    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable Integer id) {
        BillDto billFound = billMapper.billToBillDto(billService.findById(id));
        return new ResponseEntity<>(billFound, HttpStatus.OK);
    }

    /**
     * Update an existing bill.
     * @param billDto The updated bill data.
     * @return ResponseEntity containing the updated bill or an error message.
     */
    @PutMapping("/update")
    public ResponseEntity updateBill(@RequestBody BillDto billDto) {
        BillDto billUpdated = billMapper.billToBillDto(billService.updateBill(billDto));
        return new ResponseEntity<>(billUpdated, HttpStatus.OK);
    }

    /**
     * Delete a bill by its ID.
     * @param id The ID of the bill to delete.
     * @return ResponseEntity indicating success or an error message.
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteBill(@PathVariable Integer id) {
        billService.deleteBill(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}


