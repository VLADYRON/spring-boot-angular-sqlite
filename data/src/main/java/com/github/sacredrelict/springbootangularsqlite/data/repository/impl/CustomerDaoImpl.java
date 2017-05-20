package com.github.sacredrelict.springbootangularsqlite.data.repository.impl;

import com.github.sacredrelict.springbootangularsqlite.data.entity.Customer;
import com.github.sacredrelict.springbootangularsqlite.data.repository.CustomerDao;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Oleg on 14.05.2017.
 */
@Repository("customerDao")
public class CustomerDaoImpl extends BaseDaoImpl<Customer> implements CustomerDao {

    @Override
    protected String getEntityName() {
        return Customer.class.getSimpleName();
    }

    @Override
    public List<Customer> findCustomers() {
        String queryString = "SELECT * FROM customer";
        final Query query = sessionNew().createSQLQuery(queryString).addEntity(Customer.class);
        return query.list();
    }

    @Override
    public void deleteCustomer(Long id) {
        String queryString = "DELETE FROM customer WHERE id = :id";
        final Query query = sessionNew().createSQLQuery(queryString).addEntity(Customer.class);
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public void updateCustomer(Long id, String firstName, String lastName, String dateOfBirth, String adress) {
        String queryString = "UPDATE customer SET first_name = :firstName, " +
                "last_name = :lastName, " +
                "date_of_birth = :dateOfBirth, " +
                "adress = :adress " +
                "WHERE id = :id";
        final Query query = sessionNew().createSQLQuery(queryString).addEntity(Customer.class);
        query.setParameter("id", id);
        query.setParameter("firstName", firstName);
        query.setParameter("lastName", lastName);
        query.setParameter("dateOfBirth", dateOfBirth);
        query.setParameter("adress", adress);
        query.executeUpdate();
    }

}
