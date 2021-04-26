package com.github.fdehghan4013.reporting.repository;

import com.github.fdehghan4013.reporting.domain.dto.warehouse.WarehouseAvailabilityByDateThirdPartyRS;
import com.github.fdehghan4013.reporting.domain.dto.warehouse.WarehouseAvailabilityThirdPartyRS;

import java.util.Date;
import java.util.List;

public interface WarehouseRepository {
    List<WarehouseAvailabilityThirdPartyRS> findAllByAvailabilityTrue();
    List<WarehouseAvailabilityByDateThirdPartyRS> findAllByAvailabilityTrueAndDateBetween(Date incomingDate, Date outgoingDate);
}
