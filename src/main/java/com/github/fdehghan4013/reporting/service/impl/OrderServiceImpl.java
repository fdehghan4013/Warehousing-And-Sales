package com.github.fdehghan4013.reporting.service.impl;

import com.github.fdehghan4013.reporting.domain.dto.order.OrderForwardingRS;
import com.github.fdehghan4013.reporting.domain.dto.order.OrderPaymentRS;
import com.github.fdehghan4013.reporting.domain.enumeration.ReportType;
import com.github.fdehghan4013.reporting.repository.OrderRepository;
import com.github.fdehghan4013.reporting.service.OrderService;
import com.github.fdehghan4013.reporting.service.ReportService;
import com.github.fdehghan4013.reporting.util.MapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final MapperUtil mapperUtil;
    private final ReportService reportService;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, MapperUtil mapperUtil, ReportService reportService) {
        this.orderRepository = orderRepository;
        this.mapperUtil = mapperUtil;
        this.reportService = reportService;
    }

    @Override
    public List<OrderPaymentRS> getOrderPayment() {
        try {
            return orderRepository.findAllByOrderPaymentTrue().stream()
                    .map(mapperUtil::mapToOrderPaymentRS)
                    .collect(Collectors.toList());
        } finally {
            reportService.saveNewReportRequestAsync(ReportType.ORDER_PAYMENT);
        }
    }

    @Override
    public List<OrderForwardingRS> getOrderForwarding() {
        try {
            return orderRepository.findAllByOrderForwardingTrue().stream()
                    .map(mapperUtil::mapToOrderForwardingRS)
                    .collect(Collectors.toList());
        } finally {
            reportService.saveNewReportRequestAsync(ReportType.ORDER_FORWARDING);
        }
    }
}
