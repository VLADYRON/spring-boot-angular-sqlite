package com.github.sacredrelict.springbootangularsqlite.core.dto;

import javax.validation.constraints.NotNull;

/**
 * Created by Oleg on 14.05.2017.
 */
public class PaymentDto {

    @NotNull
    private Long customerId;

    @NotNull
    private Long billerId;

    @NotNull
    private String account;

    @NotNull
    private String amount;

    public PaymentDto() {
    }

    public PaymentDto(Long customerId, Long billerId, String account, String amount) {
        this.customerId = customerId;
        this.billerId = billerId;
        this.account = account;
        this.amount = amount;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getBillerId() {
        return billerId;
    }

    public void setBillerId(Long billerId) {
        this.billerId = billerId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "PaymentDto{" +
                "customerId=" + customerId +
                ", billerId=" + billerId +
                ", account='" + account + '\'' +
                ", amount='" + amount + '\'' +
                '}';
    }
}
