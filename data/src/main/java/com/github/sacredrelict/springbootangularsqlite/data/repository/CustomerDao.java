package com.github.sacredrelict.springbootangularsqlite.data.repository;

import com.github.sacredrelict.springbootangularsqlite.data.entity.Customer;

import java.util.List;

/**
 * Created by Oleg on 14.05.2017.
 */
public interface CustomerDao extends BaseDao<Customer> {

    List<Customer> findCustomers();

    void deleteCustomer(Long id);

    void updateCustomer(Long id, String firstName, String lastName, String dateOfBirth, String adress);

}
