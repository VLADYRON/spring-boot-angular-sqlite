package com.github.sacredrelict.springbootangularsqlite.core.service.impl;

import com.github.sacredrelict.springbootangularsqlite.core.dto.CustomerDto;
import com.github.sacredrelict.springbootangularsqlite.core.service.CustomerService;
import com.github.sacredrelict.springbootangularsqlite.data.entity.Customer;
import com.github.sacredrelict.springbootangularsqlite.data.repository.CustomerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Oleg on 11.02.2017.
 */
@Service("customerService")
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerDao customerDao;

    @Override
    public List<Customer> getCustomers() {
        return customerDao.findCustomers();
    }

    @Override
    public void addCustomerFromDto(CustomerDto customerDto) {
        Customer customer = new Customer();
        customer.setFirstName(customerDto.getFirstName());
        customer.setLastName(customerDto.getLastName());
        customer.setDateOfBirth(customerDto.getDateOfBirth());
        customer.setAdress(customerDto.getAdress());
        customerDao.save(customer);
    }

    @Override
    public void removeCustomer(Long id) {
        customerDao.deleteCustomer(id);
    }

    @Override
    public void updateCustomerFromDto(CustomerDto customerDto) {
        customerDao.updateCustomer(customerDto.getId(),
                customerDto.getFirstName(),
                customerDto.getLastName(),
                customerDto.getDateOfBirth(),
                customerDto.getAdress());
    }
}
