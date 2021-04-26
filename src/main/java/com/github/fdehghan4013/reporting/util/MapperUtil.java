package com.github.fdehghan4013.reporting.util;

import com.github.fdehghan4013.reporting.domain.dto.*;
import com.github.fdehghan4013.reporting.domain.dto.order.OrderForwardingRS;
import com.github.fdehghan4013.reporting.domain.dto.order.OrderForwardingThirdPartyRS;
import com.github.fdehghan4013.reporting.domain.dto.order.OrderPaymentRS;
import com.github.fdehghan4013.reporting.domain.dto.order.OrderPaymentThirdPartyRS;
import com.github.fdehghan4013.reporting.domain.dto.warehouse.WarehouseAvailabilityByDateRS;
import com.github.fdehghan4013.reporting.domain.dto.warehouse.WarehouseAvailabilityByDateThirdPartyRS;
import com.github.fdehghan4013.reporting.domain.dto.warehouse.WarehouseAvailabilityRS;
import com.github.fdehghan4013.reporting.domain.dto.warehouse.WarehouseAvailabilityThirdPartyRS;
import com.github.fdehghan4013.reporting.domain.entity.Report;
import org.springframework.stereotype.Service;

@Service
public class MapperUtil {
    public ReportRS mapToReport(Report reportEntity) {
        ReportRS reportRS = new ReportRS();
        reportRS.setUid(reportEntity.getUid());
        reportRS.setType(reportEntity.getType());
        reportRS.setCreatedBy(reportEntity.getCreatedBy());
        reportRS.setCreatedDate(reportEntity.getCreatedDate());

        return reportRS;
    }

    public WarehouseAvailabilityByDateRS mapToAvailabilityByDateRS(WarehouseAvailabilityByDateThirdPartyRS response) {
        WarehouseAvailabilityByDateRS warehouseAvailabilityByDateRS = new WarehouseAvailabilityByDateRS();
        warehouseAvailabilityByDateRS.setCode(response.getCode());
        warehouseAvailabilityByDateRS.setNameProduct(response.getName());
        warehouseAvailabilityByDateRS.setCount(response.getCount());
        warehouseAvailabilityByDateRS.setWeight(response.getWeight());
        warehouseAvailabilityByDateRS.setIncomingDate(response.getIncomingDate());
        warehouseAvailabilityByDateRS.setOutgoingDate(response.getOutgoingDate());

        return warehouseAvailabilityByDateRS;
    }

    public WarehouseAvailabilityRS mapToAvailabilityRS(WarehouseAvailabilityThirdPartyRS response) {
        WarehouseAvailabilityRS warehouseAvailabilityRS = new WarehouseAvailabilityRS();
        warehouseAvailabilityRS.setCode(response.getCode());
        warehouseAvailabilityRS.setNameProduct(response.getName());
        warehouseAvailabilityRS.setCount(response.getCount());
        warehouseAvailabilityRS.setWeight(response.getWeight());

         return warehouseAvailabilityRS;
    }

    public OrderForwardingRS mapToOrderForwardingRS(OrderForwardingThirdPartyRS response) {
        OrderForwardingRS orderForwardingRS = new OrderForwardingRS();
        orderForwardingRS.setCode(response.getCode());
        orderForwardingRS.setNameProduct(response.getNameProduct());
        orderForwardingRS.setCount(response.getCount());
        orderForwardingRS.setUnitCost(response.getUnitCost());
        orderForwardingRS.setDestination(response.getDestination());
        orderForwardingRS.setDeliveryType(response.getDeliveryType());
        orderForwardingRS.setTransportationCost(response.getTransportationCost());
        orderForwardingRS.setFinalCost(response.getFinalCost());
        orderForwardingRS.setOrderDate(response.getOrderDate());

        return orderForwardingRS;
    }

    public OrderPaymentRS mapToOrderPaymentRS(OrderPaymentThirdPartyRS response) {
        OrderPaymentRS orderPaymentRS = new OrderPaymentRS();
        orderPaymentRS.setCode(response.getCode());
        orderPaymentRS.setNameProduct(response.getNameProduct());
        orderPaymentRS.setCount(response.getCount());
        orderPaymentRS.setPaymentStatus(response.getPaymentStatus());
        orderPaymentRS.setOrderDate(response.getOrderDate());

        return orderPaymentRS;
    }
}
