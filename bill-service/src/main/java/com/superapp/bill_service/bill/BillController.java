package com.superapp.bill_service.bill;

import com.superapp.exception.ExistingIdException;
import com.superapp.exception.InvalidBillException;
import com.superapp.exception.NoPersonFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/bill")
public class BillController {
    @Autowired
    private IBillService billService;
    @Autowired
    private IBillMapper billMapper;

    @PostMapping
    public ResponseEntity createBill(@RequestBody BillDto billDto) {
        try {
            BillDto billCreated = billMapper.billToBillDto(billService.createBill(billDto));
            return new ResponseEntity<BillDto>(billCreated, HttpStatus.CREATED);

        } catch (ExistingIdException | NoPersonFoundException | InvalidBillException e) {
            log.debug(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);

        } catch (Exception e) {
            log.debug(e.getMessage());
            return new ResponseEntity<>("Unknown error :c", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity findAllBills() {
        try {
            List<BillDto> bills = billMapper.billListToBillDtoList(billService.findAll());
            return new ResponseEntity<List<BillDto>>(bills, HttpStatus.OK);

        } catch (Exception e) {
            log.debug(e.getMessage());
            return new ResponseEntity<>("Unknown error :c", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity findBillById(@PathVariable Integer id) {
        try {
            BillDto billFound = billMapper.billToBillDto(billService.findById(id));
            return new ResponseEntity<BillDto>(billFound, HttpStatus.FOUND);

        } catch (ExistingIdException | NoPersonFoundException | InvalidBillException e) {
            log.debug(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);

        } catch (Exception e) {
            log.debug(e.getMessage());
            return new ResponseEntity<>("Unknown error :c", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping
    public ResponseEntity updateBill(@RequestBody BillDto billDto) {
        try {
            BillDto billUpdated = billMapper.billToBillDto(billService.updateBill(billDto));
            return new ResponseEntity<BillDto>(billUpdated, HttpStatus.OK);

        } catch (ExistingIdException | NoPersonFoundException | InvalidBillException e) {
            log.debug(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);

        } catch (Exception e) {
            log.debug(e.getMessage());
            return new ResponseEntity<>("Unknown error :c", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping
    public ResponseEntity deleteBill(@RequestBody Integer id) {
        try {
            billService.deleteBill(id);
            return new ResponseEntity<>(HttpStatus.OK);

        } catch (ExistingIdException | NoPersonFoundException | InvalidBillException e) {
            log.debug(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);

        } catch (Exception e) {
            log.debug(e.getMessage());
            return new ResponseEntity<>("Unknown error :c", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
