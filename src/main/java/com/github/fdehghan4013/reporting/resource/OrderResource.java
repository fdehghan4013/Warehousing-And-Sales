package com.github.fdehghan4013.reporting.resource;

import com.github.fdehghan4013.reporting.domain.dto.order.OrderForwardingRS;
import com.github.fdehghan4013.reporting.domain.dto.order.OrderPaymentRS;
import com.github.fdehghan4013.reporting.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/order")
@Slf4j
public class OrderResource {

    private final OrderService orderService;

    @Autowired
    public OrderResource(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping(value = "payment", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public List<OrderPaymentRS> getOrderPayment() {
        log.info("Get order payment ");

        List<OrderPaymentRS> response = null;
        try {
            response = orderService.getOrderPayment();
        } catch (Exception e) {
            log.error("Get order payment current error: " + e.getMessage());
            System.err.println(e.getMessage());
        }

        return response;
    }

    @GetMapping(value = "forwarding", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public List<OrderForwardingRS> getOrderForwarding() {
        log.info("Get order forwarding ");
        try {
            return orderService.getOrderForwarding();
        } catch (Exception e) {
            log.error("Get order forwarding current error: " + e.getMessage());
            System.err.println(e.getMessage());
        }
        return null;
    }

}
