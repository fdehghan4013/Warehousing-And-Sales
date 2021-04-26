package com.github.fdehghan4013.reporting.domain.dto.warehouse;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class WarehouseAvailabilityByDateThirdPartyRS extends WarehouseAvailabilityThirdPartyRS implements Serializable {
    public WarehouseAvailabilityByDateThirdPartyRS(String code, String name, Integer count, Double weight, Date incomingDate, Date outgoingDate) {
        super(code, name, count, weight);
        this.incomingDate = incomingDate;
        this.outgoingDate = outgoingDate;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", shape = JsonFormat.Shape.STRING)
    private Date incomingDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", shape = JsonFormat.Shape.STRING)
    private Date outgoingDate;
}
