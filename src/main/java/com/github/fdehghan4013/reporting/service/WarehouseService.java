package com.github.fdehghan4013.reporting.service;

import com.github.fdehghan4013.reporting.domain.dto.warehouse.WarehouseAvailabilityByDateRQ;
import com.github.fdehghan4013.reporting.domain.dto.warehouse.WarehouseAvailabilityByDateRS;
import com.github.fdehghan4013.reporting.domain.dto.warehouse.WarehouseAvailabilityRS;

import java.util.List;

public interface WarehouseService {
    List<WarehouseAvailabilityRS> getAvailability();
    List<WarehouseAvailabilityByDateRS> getAvailabilityByDate(WarehouseAvailabilityByDateRQ request);
}
