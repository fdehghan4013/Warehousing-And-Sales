package com.github.fdehghan4013.reporting.util;

import com.github.fdehghan4013.reporting.domain.dto.ReportRS;
import com.github.fdehghan4013.reporting.domain.dto.order.OrderForwardingRS;
import com.github.fdehghan4013.reporting.domain.dto.order.OrderForwardingThirdPartyRS;
import com.github.fdehghan4013.reporting.domain.dto.order.OrderPaymentRS;
import com.github.fdehghan4013.reporting.domain.dto.order.OrderPaymentThirdPartyRS;
import com.github.fdehghan4013.reporting.domain.dto.warehouse.WarehouseAvailabilityByDateRS;
import com.github.fdehghan4013.reporting.domain.dto.warehouse.WarehouseAvailabilityByDateThirdPartyRS;
import com.github.fdehghan4013.reporting.domain.dto.warehouse.WarehouseAvailabilityRS;
import com.github.fdehghan4013.reporting.domain.dto.warehouse.WarehouseAvailabilityThirdPartyRS;
import com.github.fdehghan4013.reporting.domain.entity.Report;
import com.github.fdehghan4013.reporting.domain.enumeration.DeliveryType;
import com.github.fdehghan4013.reporting.domain.enumeration.ReportType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = {MapperUtil.class})
class MapperUtilTest {
    @Autowired
    private MapperUtil mapperUtil;

    private final Long timestamp = 878520600000L; // 1997-11-03 05:00:00

    @Test
    void mapToReport() {
        // input
        ReportType type = ReportType.ORDER_PAYMENT;
        Report input = new Report(type);

        // function
        ReportRS reportRS = mapperUtil.mapToReport(input);
        // correct output

        assertEquals(reportRS.getType(), type);
    }


    @Test
    void mapToAvailabilityByDateRS() {
        WarehouseAvailabilityByDateThirdPartyRS input = new WarehouseAvailabilityByDateThirdPartyRS();
        input.setIncomingDate(new Date(timestamp));
        input.setOutgoingDate(new Date(timestamp + (24 * 60 * 60 * 1000))); // add one day

        WarehouseAvailabilityByDateRS warehouseAvailabilityByDateRS = mapperUtil.mapToAvailabilityByDateRS(input);

        assertEquals(input.getIncomingDate(), warehouseAvailabilityByDateRS.getIncomingDate());
        assertEquals(input.getOutgoingDate(), warehouseAvailabilityByDateRS.getOutgoingDate());

    }

    @Test
    void mapToAvailabilityRS() {
        WarehouseAvailabilityThirdPartyRS input = new WarehouseAvailabilityThirdPartyRS();
        input.setCode("123");
        input.setName("pen");
        input.setCount(10);
        input.setWeight(10.5);

        WarehouseAvailabilityRS warehouseAvailabilityRS = mapperUtil.mapToAvailabilityRS(input);

        assertEquals(input.getCode(), warehouseAvailabilityRS.getCode());
        assertEquals(input.getName(), warehouseAvailabilityRS.getNameProduct());
        assertEquals(input.getCount(), warehouseAvailabilityRS.getCount());
        assertEquals(input.getWeight(), warehouseAvailabilityRS.getWeight());
    }

    @Test
    void mapToOrderForwardingRS() {
        OrderForwardingThirdPartyRS input = new OrderForwardingThirdPartyRS();
        input.setCode("123");
        input.setNameProduct("pen");
        input.setCount(10);
        input.setUnitCost(10500.);
        input.setDestination("boro pishe zahra");
        input.setDeliveryType(DeliveryType.MOTORCYCLE);
        input.setTransportationCost(5000.);
        input.setFinalCost(15500.);
        input.setOrderDate(new Date(timestamp));

        OrderForwardingRS orderForwardingRS = mapperUtil.mapToOrderForwardingRS(input);

        assertEquals(input.getCode(), orderForwardingRS.getCode());
        assertEquals(input.getNameProduct(), orderForwardingRS.getNameProduct());
        assertEquals(input.getCount(), orderForwardingRS.getCount());
        assertEquals(input.getUnitCost(), orderForwardingRS.getUnitCost());
        assertEquals(input.getDestination(), orderForwardingRS.getDestination());
        assertEquals(input.getDeliveryType(), orderForwardingRS.getDeliveryType());
        assertEquals(input.getTransportationCost(), orderForwardingRS.getTransportationCost());
        assertEquals(input.getFinalCost(), orderForwardingRS.getFinalCost());
        assertEquals(input.getOrderDate(), orderForwardingRS.getOrderDate());

    }

    @Test
    void mapToOrderPaymentRS() {
        // input
        OrderPaymentThirdPartyRS input = new OrderPaymentThirdPartyRS();
        input.setCode("123");
        input.setNameProduct("pen");
        input.setCount(10);
        input.setPaymentStatus("paid");
        input.setOrderDate(new Date(timestamp));

        // function
        OrderPaymentRS orderPaymentRS = mapperUtil.mapToOrderPaymentRS(input);
        // correct output
        assertEquals(input.getCode(), orderPaymentRS.getCode());
        assertEquals(input.getNameProduct(), orderPaymentRS.getNameProduct());
        assertEquals(input.getCount(), orderPaymentRS.getCount());
        assertEquals(input.getPaymentStatus(), orderPaymentRS.getPaymentStatus());
        assertEquals(input.getOrderDate(), orderPaymentRS.getOrderDate());

    }
}
