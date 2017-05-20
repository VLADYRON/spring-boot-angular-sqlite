package com.github.sacredrelict.springbootangularsqlite.core.service;

import com.github.sacredrelict.springbootangularsqlite.core.dto.CustomerDto;
import com.github.sacredrelict.springbootangularsqlite.data.entity.Customer;

import java.util.List;

/**
 * Created by Oleg on 11.02.2017.
 */
public interface CustomerService {

    List<Customer> getCustomers();

    void addCustomerFromDto(CustomerDto customerDto);

    void removeCustomer(Long id);

    void updateCustomerFromDto(CustomerDto customerDto);
}
