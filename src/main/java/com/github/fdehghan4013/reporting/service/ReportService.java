package com.github.fdehghan4013.reporting.service;

import com.github.fdehghan4013.reporting.domain.dto.ReportRS;
import com.github.fdehghan4013.reporting.domain.enumeration.ReportType;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface ReportService {
    List<ReportRS> getAllReportInfo();

    void saveNewReportRequest(ReportType type);

    CompletableFuture<Void> saveNewReportRequestAsync(ReportType type);
}
