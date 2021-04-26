package com.github.fdehghan4013.reporting.domain.dto.warehouse;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WarehouseAvailabilityThirdPartyRS implements Serializable {
    private String code;
    private String name;
    private Integer count;
    @JsonProperty("Weight")
    private Double weight;
}
