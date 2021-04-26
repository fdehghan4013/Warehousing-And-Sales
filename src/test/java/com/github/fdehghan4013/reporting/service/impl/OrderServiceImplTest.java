package com.github.fdehghan4013.reporting.service.impl;

import com.github.fdehghan4013.reporting.domain.dto.order.OrderForwardingRS;
import com.github.fdehghan4013.reporting.domain.dto.order.OrderForwardingThirdPartyRS;
import com.github.fdehghan4013.reporting.domain.dto.order.OrderPaymentRS;
import com.github.fdehghan4013.reporting.domain.dto.order.OrderPaymentThirdPartyRS;
import com.github.fdehghan4013.reporting.domain.entity.Report;
import com.github.fdehghan4013.reporting.domain.enumeration.DeliveryType;
import com.github.fdehghan4013.reporting.domain.enumeration.ReportType;
import com.github.fdehghan4013.reporting.repository.OrderRepository;
import com.github.fdehghan4013.reporting.service.ReportService;
import com.github.fdehghan4013.reporting.util.MapperUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static com.github.fdehghan4013.reporting.TestUtil.*;

@SpringBootTest(classes = {OrderServiceImpl.class, MapperUtil.class})
class OrderServiceImplTest {
    @MockBean
    private OrderRepository orderRepository;
    @MockBean
    private ReportService reportService;

    @Autowired
    private OrderServiceImpl orderServiceImpl;

    private List<OrderPaymentThirdPartyRS> fakeOrderPaymentList;
    private List<OrderForwardingThirdPartyRS> fakeOrderForwardList;
    private List<Report> fakeReportList;
    private final List<String> orderStatuses = Arrays.asList("CANCELLED", "NOT_PAID", "IN_PROCESS", "SHIPPING", "COMPLETED");


    @BeforeEach
    void preSetupLists() {
        fakeOrderPaymentList = new ArrayList<>(
                Arrays.asList(
                        new OrderPaymentThirdPartyRS(randomString(6), randomString(10), randomInt(0, 100), randomSample(orderStatuses), randomDate()),
                        new OrderPaymentThirdPartyRS(randomString(6), randomString(10), randomInt(0, 100), randomSample(orderStatuses), randomDate()),
                        new OrderPaymentThirdPartyRS(randomString(6), randomString(10), randomInt(0, 100), randomSample(orderStatuses), randomDate()),
                        new OrderPaymentThirdPartyRS(randomString(6), randomString(10), randomInt(0, 100), randomSample(orderStatuses), randomDate()),
                        new OrderPaymentThirdPartyRS(randomString(6), randomString(10), randomInt(0, 100), randomSample(orderStatuses), randomDate()),
                        new OrderPaymentThirdPartyRS(randomString(6), randomString(10), randomInt(0, 100), randomSample(orderStatuses), randomDate()),
                        new OrderPaymentThirdPartyRS(randomString(6), randomString(10), randomInt(0, 100), randomSample(orderStatuses), randomDate()),
                        new OrderPaymentThirdPartyRS(randomString(6), randomString(10), randomInt(0, 100), randomSample(orderStatuses), randomDate())
                )
        );

        fakeOrderForwardList = new ArrayList<>(
                Arrays.asList(
                        new OrderForwardingThirdPartyRS(randomString(6),randomString(10), randomInt(0, 100), randomDouble(1000, 10000), randomString(3), randomEnum(DeliveryType.class), randomDouble(1000, 10000), randomDouble(1000, 10000), randomDate()),
                        new OrderForwardingThirdPartyRS(randomString(6),randomString(10), randomInt(0, 100), randomDouble(1000, 10000), randomString(3), randomEnum(DeliveryType.class), randomDouble(1000, 10000), randomDouble(1000, 10000), randomDate()),
                        new OrderForwardingThirdPartyRS(randomString(6),randomString(10), randomInt(0, 100), randomDouble(1000, 10000), randomString(3), randomEnum(DeliveryType.class), randomDouble(1000, 10000), randomDouble(1000, 10000), randomDate()),
                        new OrderForwardingThirdPartyRS(randomString(6),randomString(10), randomInt(0, 100), randomDouble(1000, 10000), randomString(3), randomEnum(DeliveryType.class), randomDouble(1000, 10000), randomDouble(1000, 10000), randomDate()),
                        new OrderForwardingThirdPartyRS(randomString(6),randomString(10), randomInt(0, 100), randomDouble(1000, 10000), randomString(3), randomEnum(DeliveryType.class), randomDouble(1000, 10000), randomDouble(1000, 10000), randomDate()),
                        new OrderForwardingThirdPartyRS(randomString(6),randomString(10), randomInt(0, 100), randomDouble(1000, 10000), randomString(3), randomEnum(DeliveryType.class), randomDouble(1000, 10000), randomDouble(1000, 10000), randomDate()),
                        new OrderForwardingThirdPartyRS(randomString(6),randomString(10), randomInt(0, 100), randomDouble(1000, 10000), randomString(3), randomEnum(DeliveryType.class), randomDouble(1000, 10000), randomDouble(1000, 10000), randomDate()),
                        new OrderForwardingThirdPartyRS(randomString(6),randomString(10), randomInt(0, 100), randomDouble(1000, 10000), randomString(3), randomEnum(DeliveryType.class), randomDouble(1000, 10000), randomDouble(1000, 10000), randomDate())
                )
        );

        fakeReportList = new ArrayList<>();
    }

    @BeforeEach
    void mockSetup() {
        Mockito.when(orderRepository.findAllByOrderPaymentTrue()).thenAnswer(invocation -> fakeOrderPaymentList);

        Mockito.when(orderRepository.findAllByOrderForwardingTrue()).thenAnswer(invocation -> fakeOrderForwardList);

        Mockito.when(reportService.saveNewReportRequestAsync(Mockito.notNull())).then(invocation -> {
            ReportType reportType = invocation.getArgument(0);

            Report report = new Report(reportType);

            int startRange = 1;
            if (!fakeReportList.isEmpty()) startRange = fakeReportList.get(fakeReportList.size() - 1).getId().intValue();

            report.setId(Long.valueOf(randomInt(startRange, startRange * 2)));

            fakeReportList.add(report);

            fakeReportList.sort(Comparator.comparing(Report::getId));

            return null;
        });
    }

    @Test
    void getOrderPayment() {
        List<OrderPaymentRS> orderPayment = orderServiceImpl.getOrderPayment();

        Assertions.assertEquals(fakeOrderPaymentList.size(), orderPayment.size());

        boolean allExist = orderPayment.stream().allMatch(order ->
                fakeOrderPaymentList.stream().anyMatch(orderThirdParty -> order.getCode().equals(orderThirdParty.getCode()))
        );

        Assertions.assertTrue(allExist);

        Assertions.assertTrue(fakeReportList.stream().anyMatch(r -> r.getType().equals(ReportType.ORDER_PAYMENT)));
    }

    @Test
    void getOrderForwarding() {
        List<OrderForwardingRS> orderForwarding = orderServiceImpl.getOrderForwarding();

        Assertions.assertEquals(fakeOrderForwardList.size(), orderForwarding.size());

        boolean allExist = orderForwarding.stream().allMatch(order ->
                fakeOrderForwardList.stream().anyMatch(orderThirdParty -> order.getCode().equals(orderThirdParty.getCode()))
        );

        Assertions.assertTrue(allExist);

        Assertions.assertTrue(fakeReportList.stream().anyMatch(r -> r.getType().equals(ReportType.ORDER_FORWARDING)));
    }
}
