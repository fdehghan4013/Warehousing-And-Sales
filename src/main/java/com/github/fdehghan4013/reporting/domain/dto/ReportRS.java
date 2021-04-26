package com.github.fdehghan4013.reporting.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.github.fdehghan4013.reporting.domain.enumeration.ReportType;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class ReportRS implements Serializable {
    private String     uid;
    private ReportType type;
    private String     createdBy;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", shape = JsonFormat.Shape.STRING)
    private Date       createdDate;
}
