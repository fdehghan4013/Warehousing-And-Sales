package com.github.fdehghan4013.reporting.service.impl;

import com.github.fdehghan4013.reporting.domain.dto.ReportRS;
import com.github.fdehghan4013.reporting.domain.entity.Report;
import com.github.fdehghan4013.reporting.domain.enumeration.ReportType;
import com.github.fdehghan4013.reporting.repository.ReportRepository;
import com.github.fdehghan4013.reporting.service.ReportService;
import com.github.fdehghan4013.reporting.util.MapperUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ReportServiceImpl implements ReportService {
    private static final ExecutorService threadPool = Executors.newFixedThreadPool(
            Math.max(Runtime.getRuntime().availableProcessors() - 1, 1)
    );

    private final ReportRepository reportRepository;
    private final MapperUtil mapperUtil;

    @Autowired
    public ReportServiceImpl(ReportRepository reportRepository, MapperUtil mapperUtil) {
        this.reportRepository = reportRepository;
        this.mapperUtil = mapperUtil;
    }

    @Override
    public List<ReportRS> getAllReportInfo() {
        try {
            return reportRepository.findAll().stream()
                    .map(mapperUtil::mapToReport)
                    .collect(Collectors.toList());
        } catch (RuntimeException ex){
            log.error("an error happened when tries to get all the report records", ex);
            throw ex;
        }

    }

    @Override
    @Transactional(rollbackFor = {Throwable.class})
    public void saveNewReportRequest(ReportType type) {
        try {
            reportRepository.save(new Report(type));
        } catch (RuntimeException ex) {
            log.error("an error happened on saving new report record", ex);
            throw ex;
        }
    }

    @Override
    @Transactional(rollbackFor = {Throwable.class})
    public CompletableFuture<Void> saveNewReportRequestAsync(ReportType type) {
        try {
            return CompletableFuture.runAsync(() -> this.saveNewReportRequest(type), threadPool);
        } catch (RuntimeException ex) { // HibernateExceptions are extended from RuntimeException + it may throws IllegalArgumentException (for null inputs)
            log.error("an error happened on saving new report record asynchronously", ex);
            throw ex;
        }
    }

}
