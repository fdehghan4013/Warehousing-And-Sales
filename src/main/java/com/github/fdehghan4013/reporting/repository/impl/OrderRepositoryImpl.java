package com.github.fdehghan4013.reporting.repository.impl;

import com.github.fdehghan4013.reporting.domain.dto.order.OrderForwardingThirdPartyRS;
import com.github.fdehghan4013.reporting.domain.dto.order.OrderPaymentThirdPartyRS;
import com.github.fdehghan4013.reporting.repository.OrderRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderRepositoryImpl implements OrderRepository {
    @Override
    public List<OrderForwardingThirdPartyRS> findAllByOrderForwardingTrue() {
        /*
         TODO: use Oreder/Accounting service SDK or
                call the it's API by libraries like
                OkHttp3 or etc.
         */

        throw new RuntimeException("Still in the process");
    }

    @Override
    public List<OrderPaymentThirdPartyRS> findAllByOrderPaymentTrue() {

        throw new RuntimeException("Still in the process");
    }
}
