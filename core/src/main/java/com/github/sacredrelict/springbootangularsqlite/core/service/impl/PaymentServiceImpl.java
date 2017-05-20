package com.github.sacredrelict.springbootangularsqlite.core.service.impl;

import com.github.sacredrelict.springbootangularsqlite.core.dto.PaymentDto;
import com.github.sacredrelict.springbootangularsqlite.core.service.PaymentService;
import com.github.sacredrelict.springbootangularsqlite.data.Utils;
import com.github.sacredrelict.springbootangularsqlite.data.entity.Biller;
import com.github.sacredrelict.springbootangularsqlite.data.entity.Customer;
import com.github.sacredrelict.springbootangularsqlite.data.entity.Payment;
import com.github.sacredrelict.springbootangularsqlite.data.repository.BillerDao;
import com.github.sacredrelict.springbootangularsqlite.data.repository.CustomerDao;
import com.github.sacredrelict.springbootangularsqlite.data.repository.PaymentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by Oleg on 14.05.2017.
 */
@Service("paymentService")
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentDao paymentDao;

    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private BillerDao billerDao;

    @Override
    public List<Payment> getPayments() {
        return paymentDao.findPayments();
    }

    @Override
    public void addPaymentFromDto(PaymentDto paymentDto) {
        Customer customer = customerDao.findById(paymentDto.getCustomerId());
        Biller biller = billerDao.findById(paymentDto.getBillerId());
        if (customer != null && biller != null) {
            Payment payment = new Payment();
            payment.setDate(Utils.getFormattedDate(new Date()));
            payment.setCustomer(customer);
            payment.setBiller(biller);
            payment.setAccount(paymentDto.getAccount());
            payment.setAmount(paymentDto.getAmount());
            paymentDao.save(payment);
        }
    }


}