package com.github.fdehghan4013.reporting.repository.impl;

import com.github.fdehghan4013.reporting.domain.dto.warehouse.WarehouseAvailabilityByDateThirdPartyRS;
import com.github.fdehghan4013.reporting.domain.dto.warehouse.WarehouseAvailabilityThirdPartyRS;
import com.github.fdehghan4013.reporting.repository.WarehouseRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class WarehouseRepositoryImpl implements WarehouseRepository {
    @Override
    public List<WarehouseAvailabilityThirdPartyRS> findAllByAvailabilityTrue() {
        /*
         TODO: use Warehouse service SDK or
                call the it's API by libraries like
                OkHttp3 or etc.
         */
        throw new RuntimeException("Still in the process");
    }

    @Override
    public List<WarehouseAvailabilityByDateThirdPartyRS> findAllByAvailabilityTrueAndDateBetween(Date incomingDate, Date outgoingDate) {
        /*
         TODO: use Warehouse service SDK or
                call the it's API by libraries like
                OkHttp3 or etc.
         */
        throw new RuntimeException("Still in the process");
    }
}
