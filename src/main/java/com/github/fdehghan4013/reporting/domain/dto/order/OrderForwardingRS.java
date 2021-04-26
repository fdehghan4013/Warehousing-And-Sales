package com.github.fdehghan4013.reporting.domain.dto.order;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.github.fdehghan4013.reporting.domain.enumeration.DeliveryType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderForwardingRS implements Serializable {
    private String       code;
    private String       nameProduct;
    private Integer      count;
    private Double       unitCost;
    private String       destination;
    private DeliveryType deliveryType;
    private Double       transportationCost;
    private Double       finalCost;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", shape = JsonFormat.Shape.STRING)
    private Date         orderDate;
}
