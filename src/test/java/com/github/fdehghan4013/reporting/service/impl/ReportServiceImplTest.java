package com.github.fdehghan4013.reporting.service.impl;

import com.github.fdehghan4013.reporting.domain.dto.ReportRS;
import com.github.fdehghan4013.reporting.domain.entity.Report;
import com.github.fdehghan4013.reporting.domain.enumeration.ReportType;
import com.github.fdehghan4013.reporting.repository.ReportRepository;
import com.github.fdehghan4013.reporting.service.ReportService;
import com.github.fdehghan4013.reporting.util.MapperUtil;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;

@SpringBootTest(classes = {ReportServiceImpl.class, MapperUtil.class})
class ReportServiceImplTest {
    @MockBean
    private ReportRepository reportRepository;

    @Autowired
    private ReportService reportService;

    private List<Report> fakedReport;

    @BeforeEach
    void preSetupLists() {
        fakedReport = new ArrayList<>(
                Arrays.asList( // Arrays.asList alone, create a fixed size array and u cant add new members to it
                        new Report(1L, UUID.randomUUID().toString(), ReportType.WAREHOUSE_AVAILABILITY, "anonymous", new Date(ThreadLocalRandom.current().nextInt() * 1000L)),
                        new Report(2L, UUID.randomUUID().toString(), ReportType.ORDER_FORWARDING, "anonymous", new Date(ThreadLocalRandom.current().nextInt() * 1000L)),
                        new Report(3L, UUID.randomUUID().toString(), ReportType.ORDER_PAYMENT, "anonymous", new Date(ThreadLocalRandom.current().nextInt() * 1000L)),
                        new Report(4L, UUID.randomUUID().toString(), ReportType.WAREHOUSE_AVAILABILITY_BY_DATE, "system", new Date(ThreadLocalRandom.current().nextInt() * 1000L)),
                        new Report(5L, UUID.randomUUID().toString(), ReportType.WAREHOUSE_AVAILABILITY_BY_DATE, "anonymous", new Date(ThreadLocalRandom.current().nextInt() * 1000L)),
                        new Report(6L, UUID.randomUUID().toString(), ReportType.WAREHOUSE_AVAILABILITY, "system", new Date(ThreadLocalRandom.current().nextInt() * 1000L)),
                        new Report(7L, UUID.randomUUID().toString(), ReportType.WAREHOUSE_AVAILABILITY, "anonymous", new Date(ThreadLocalRandom.current().nextInt() * 1000L))
                )
        );
    }

    @BeforeEach
    void mockSetup() {
        // mock findAll method in the repository
        Mockito.when(reportRepository.findAll()).thenReturn(fakedReport);

        // mock save method in the repository
        Mockito.when(reportRepository.save(Mockito.notNull())).then(invocationOnMock -> {
            Report report = invocationOnMock.getArgument(0);
            report.setId((long) (Math.random() * 1000) + 7);

            fakedReport.add(report);

            return null;
        });
    }


    @Test
    void getAllReportInfo() {
        List<ReportRS> allReportInfo = reportService.getAllReportInfo();

        Assertions.assertEquals(7, allReportInfo.size());

        allReportInfo.forEach(reportRS -> {
            Report report = fakedReport.stream().filter(mr -> mr.getUid().equals(reportRS.getUid())).findAny().orElse(null);

            Assertions.assertNotNull(report);
            Assertions.assertEquals(reportRS.getType(), report.getType());
        });

    }

    @Test
    void saveNewReportRequest() {
        ReportType inputType = ReportType.WAREHOUSE_AVAILABILITY;
        reportService.saveNewReportRequest(inputType);

        Assertions.assertTrue(fakedReport.stream().anyMatch(r -> r.getType().equals(inputType) && r.getId() > 7));
    }

    @Test
    @SneakyThrows
    void saveNewReportRequestAsync() {
        ReportType inputType = ReportType.WAREHOUSE_AVAILABILITY;

        CompletableFuture<Void> asyncTask = reportService.saveNewReportRequestAsync(inputType);
        asyncTask.join();

        Assertions.assertTrue(fakedReport.stream().anyMatch(r -> r.getType().equals(inputType) && r.getId() > 7));
    }
}
