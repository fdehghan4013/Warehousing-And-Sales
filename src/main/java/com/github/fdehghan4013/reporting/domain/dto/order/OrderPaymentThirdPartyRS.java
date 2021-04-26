package com.github.fdehghan4013.reporting.domain.dto.order;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderPaymentThirdPartyRS {
    private String  code;
    private String  nameProduct;
    private Integer count;
    private String  paymentStatus;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", shape = JsonFormat.Shape.STRING)
    private Date    orderDate;
}
