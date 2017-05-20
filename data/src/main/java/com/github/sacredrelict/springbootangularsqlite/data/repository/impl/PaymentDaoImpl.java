package com.github.sacredrelict.springbootangularsqlite.data.repository.impl;

import com.github.sacredrelict.springbootangularsqlite.data.entity.Payment;
import com.github.sacredrelict.springbootangularsqlite.data.repository.PaymentDao;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Oleg on 14.05.2017.
 */
@Repository("paymentDao")
public class PaymentDaoImpl extends BaseDaoImpl<Payment> implements PaymentDao {

    @Override
    protected String getEntityName() {
        return Payment.class.getSimpleName();
    }

    @Override
    public List<Payment> findPayments() {
        String queryString = "SELECT * FROM payment";
        final Query query = sessionNew().createSQLQuery(queryString).addEntity(Payment.class);
        return query.list();
    }

}
