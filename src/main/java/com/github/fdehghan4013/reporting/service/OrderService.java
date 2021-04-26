package com.github.fdehghan4013.reporting.service;

import com.github.fdehghan4013.reporting.domain.dto.order.OrderForwardingRS;
import com.github.fdehghan4013.reporting.domain.dto.order.OrderPaymentRS;

import java.util.List;

public interface OrderService {
    List<OrderPaymentRS> getOrderPayment();
    List<OrderForwardingRS> getOrderForwarding();
}
