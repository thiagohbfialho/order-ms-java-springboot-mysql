package com.canadafood.order.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class PaymentDto {
    private Long id;
    private BigDecimal value;
    private String name;
    private String number;
    private String expiration;
    private String code;
    private StatusPayment status;
    private Long orderId;
    private Long paymentTypeId;
}
