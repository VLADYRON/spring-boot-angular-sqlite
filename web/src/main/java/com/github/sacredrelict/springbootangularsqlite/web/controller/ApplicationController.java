package com.github.sacredrelict.springbootangularsqlite.web.controller;

import com.github.sacredrelict.springbootangularsqlite.core.dto.BillerDto;
import com.github.sacredrelict.springbootangularsqlite.core.dto.CustomerDto;
import com.github.sacredrelict.springbootangularsqlite.core.dto.PaymentDto;
import com.github.sacredrelict.springbootangularsqlite.core.service.BillerService;
import com.github.sacredrelict.springbootangularsqlite.core.service.CustomerService;
import com.github.sacredrelict.springbootangularsqlite.core.service.PaymentService;
import com.github.sacredrelict.springbootangularsqlite.data.entity.Biller;
import com.github.sacredrelict.springbootangularsqlite.data.entity.Customer;
import com.github.sacredrelict.springbootangularsqlite.data.entity.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by Oleg on 11.02.2017.
 */
@Controller
@RequestMapping(value = "/")
public class ApplicationController {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private BillerService billerService;

    @GetMapping("payments")
    @ResponseBody
    public List<Payment> getPayments() {
        return paymentService.getPayments();
    }

    @GetMapping("customers")
    @ResponseBody
    public List<Customer> getCustomers() {
        return customerService.getCustomers();
    }

    @GetMapping("billers")
    @ResponseBody
    public List<Biller> getBillers() {
        return billerService.getBillers();
    }

    @PostMapping("payments/add")
    @ResponseBody
    public ResponseEntity addPayment(@Valid @RequestBody PaymentDto paymentDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(bindingResult.getFieldErrors());
        }
        paymentService.addPaymentFromDto(paymentDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("customers/add")
    @ResponseBody
    public ResponseEntity addCustomer(@Valid @RequestBody CustomerDto customerDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(bindingResult.getFieldErrors());
        }
        customerService.addCustomerFromDto(customerDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("billers/add")
    @ResponseBody
    public ResponseEntity addBiller(@Valid @RequestBody BillerDto billerDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(bindingResult.getFieldErrors());
        }
        billerService.addBillerFromDto(billerDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("customers/remove/{id}")
    @ResponseBody
    public ResponseEntity removeCustomer(@PathVariable("id") Long id) {
        customerService.removeCustomer(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("billers/remove/{id}")
    @ResponseBody
    public ResponseEntity removeBiller(@PathVariable("id") Long id) {
        billerService.removeBiller(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("customers/update")
    @ResponseBody
    public ResponseEntity updateCustomer(@Valid @RequestBody CustomerDto customerDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(bindingResult.getFieldErrors());
        }
        customerService.updateCustomerFromDto(customerDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("billers/update")
    @ResponseBody
    public ResponseEntity updateBiller(@Valid @RequestBody BillerDto billerDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(bindingResult.getFieldErrors());
        }
        billerService.updateBillerFromDto(billerDto);
        return ResponseEntity.ok().build();
    }

}
