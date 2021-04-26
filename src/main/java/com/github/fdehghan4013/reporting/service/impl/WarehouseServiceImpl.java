package com.github.fdehghan4013.reporting.service.impl;

import com.github.fdehghan4013.reporting.domain.dto.warehouse.WarehouseAvailabilityByDateRQ;
import com.github.fdehghan4013.reporting.domain.dto.warehouse.WarehouseAvailabilityByDateRS;
import com.github.fdehghan4013.reporting.domain.dto.warehouse.WarehouseAvailabilityRS;
import com.github.fdehghan4013.reporting.domain.enumeration.ReportType;
import com.github.fdehghan4013.reporting.repository.WarehouseRepository;
import com.github.fdehghan4013.reporting.service.ReportService;
import com.github.fdehghan4013.reporting.service.WarehouseService;
import com.github.fdehghan4013.reporting.util.MapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WarehouseServiceImpl implements WarehouseService {
    private final WarehouseRepository warehouseRepository;
    private final MapperUtil mapperUtil;
    private final ReportService reportService;

    @Autowired
    public WarehouseServiceImpl(WarehouseRepository warehouseRepository, MapperUtil mapperUtil, ReportService reportService) {
        this.warehouseRepository = warehouseRepository;
        this.mapperUtil = mapperUtil;
        this.reportService = reportService;
    }

    @Override
    public List<WarehouseAvailabilityRS> getAvailability() {
        try {
            return warehouseRepository.findAllByAvailabilityTrue().stream()
                    .map(mapperUtil::mapToAvailabilityRS)
                    .collect(Collectors.toList());
        } finally {
            reportService.saveNewReportRequestAsync(ReportType.WAREHOUSE_AVAILABILITY);
        }
    }

    @Override
    public List<WarehouseAvailabilityByDateRS> getAvailabilityByDate(WarehouseAvailabilityByDateRQ request) {
        try {
            return warehouseRepository.findAllByAvailabilityTrueAndDateBetween(
                    request.getIncomingDate(),
                    request.getOutgoingDate()
            ).stream()
                    .map(mapperUtil::mapToAvailabilityByDateRS)
                    .collect(Collectors.toList());
        } finally {
            reportService.saveNewReportRequestAsync(ReportType.WAREHOUSE_AVAILABILITY_BY_DATE);
        }
    }
}
