package com.github.fdehghan4013.reporting.repository;

import com.github.fdehghan4013.reporting.domain.dto.order.OrderForwardingThirdPartyRS;
import com.github.fdehghan4013.reporting.domain.dto.order.OrderPaymentThirdPartyRS;

import java.util.List;

public interface OrderRepository {
    List<OrderForwardingThirdPartyRS> findAllByOrderForwardingTrue();
    List<OrderPaymentThirdPartyRS> findAllByOrderPaymentTrue();
}
