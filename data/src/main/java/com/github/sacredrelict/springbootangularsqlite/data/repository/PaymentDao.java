package com.github.sacredrelict.springbootangularsqlite.data.repository;

import com.github.sacredrelict.springbootangularsqlite.data.entity.Payment;

import java.util.List;

/**
 * Created by Oleg on 14.05.2017.
 */
public interface PaymentDao extends BaseDao<Payment> {

    List<Payment> findPayments();

}

