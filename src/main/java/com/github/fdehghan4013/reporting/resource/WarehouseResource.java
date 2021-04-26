package com.github.fdehghan4013.reporting.resource;

import com.github.fdehghan4013.reporting.domain.dto.warehouse.WarehouseAvailabilityByDateRQ;
import com.github.fdehghan4013.reporting.domain.dto.warehouse.WarehouseAvailabilityByDateRS;
import com.github.fdehghan4013.reporting.domain.dto.warehouse.WarehouseAvailabilityRS;
import com.github.fdehghan4013.reporting.service.WarehouseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/warehouse")
@Slf4j
public class WarehouseResource {
    private final WarehouseService warehouseService;

    @Autowired
    public WarehouseResource(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    @GetMapping(value = "availability", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public List<WarehouseAvailabilityRS> getAvailability() {
        return warehouseService.getAvailability();
    }

    @PostMapping(value = "/availabilityByDate", produces = MimeTypeUtils.APPLICATION_JSON_VALUE, consumes = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public List<WarehouseAvailabilityByDateRS> getAvailabilityByDate(@RequestBody WarehouseAvailabilityByDateRQ request) {
        return warehouseService.getAvailabilityByDate(request);
    }
}
