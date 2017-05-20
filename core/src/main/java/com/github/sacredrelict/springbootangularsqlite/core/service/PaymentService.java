package com.github.sacredrelict.springbootangularsqlite.core.service;

import com.github.sacredrelict.springbootangularsqlite.core.dto.PaymentDto;
import com.github.sacredrelict.springbootangularsqlite.data.entity.Payment;

import java.util.List;

/**
 * Created by Oleg on 14.05.2017.
 */
public interface PaymentService {

    List<Payment> getPayments();

    void addPaymentFromDto(PaymentDto paymentDto);

}
