package com.github.fdehghan4013.reporting.domain.dto.warehouse;

import lombok.Data;

import java.io.Serializable;

@Data
public class WarehouseAvailabilityRS implements Serializable {
    private String code;
    private String nameProduct;
    private Integer count;
    private Double weight;
}
