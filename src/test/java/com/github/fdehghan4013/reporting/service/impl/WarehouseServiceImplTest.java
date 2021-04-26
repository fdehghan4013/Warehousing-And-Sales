package com.github.fdehghan4013.reporting.service.impl;

import com.github.fdehghan4013.reporting.domain.dto.warehouse.*;
import com.github.fdehghan4013.reporting.domain.entity.Report;
import com.github.fdehghan4013.reporting.domain.enumeration.ReportType;
import com.github.fdehghan4013.reporting.repository.WarehouseRepository;
import com.github.fdehghan4013.reporting.service.ReportService;
import com.github.fdehghan4013.reporting.util.MapperUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.*;
import java.util.stream.Collectors;

import static com.github.fdehghan4013.reporting.TestUtil.*;

@SpringBootTest(classes = {WarehouseServiceImpl.class, MapperUtil.class})
class WarehouseServiceImplTest {
    @MockBean
    private WarehouseRepository warehouseRepository;

    @MockBean
    private ReportService reportService;

    @Autowired
    private WarehouseServiceImpl warehouseService;

    private List<WarehouseAvailabilityThirdPartyRS>       fakeWarehouseAvailabilityList;
    private List<WarehouseAvailabilityByDateThirdPartyRS> fakeWarehouseAvailabilityByDateList;
    private List<Report> fakeReportList;

    @BeforeEach
    void preSetupLists() {
        fakeWarehouseAvailabilityList = new ArrayList<>(
                Arrays.asList(
                        new WarehouseAvailabilityThirdPartyRS(randomString(6), randomString(10), randomInt(0, 100), randomDouble(10, 100)),
                        new WarehouseAvailabilityThirdPartyRS(randomString(6), randomString(10), randomInt(0, 100), randomDouble(10, 100)),
                        new WarehouseAvailabilityThirdPartyRS(randomString(6), randomString(10), randomInt(0, 100), randomDouble(10, 100)),
                        new WarehouseAvailabilityThirdPartyRS(randomString(6), randomString(10), randomInt(0, 100), randomDouble(10, 100)),
                        new WarehouseAvailabilityThirdPartyRS(randomString(6), randomString(10), randomInt(0, 100), randomDouble(10, 100)),
                        new WarehouseAvailabilityThirdPartyRS(randomString(6), randomString(10), randomInt(0, 100), randomDouble(10, 100)),
                        new WarehouseAvailabilityThirdPartyRS(randomString(6), randomString(10), randomInt(0, 100), randomDouble(10, 100))
                )
        );

        fakeWarehouseAvailabilityByDateList = new ArrayList<>(
                Arrays.asList(
                        new WarehouseAvailabilityByDateThirdPartyRS(randomString(6), randomString(10), randomInt(0, 100), randomDouble(10, 100), randomDate(2016, 2017), randomDate(2017, 2018)),
                        new WarehouseAvailabilityByDateThirdPartyRS(randomString(6), randomString(10), randomInt(0, 100), randomDouble(10, 100), randomDate(2015, 2017), randomDate(2017, 2020)),
                        new WarehouseAvailabilityByDateThirdPartyRS(randomString(6), randomString(10), randomInt(0, 100), randomDouble(10, 100), randomDate(2016, 2016), randomDate(2017, 2017)),
                        new WarehouseAvailabilityByDateThirdPartyRS(randomString(6), randomString(10), randomInt(0, 100), randomDouble(10, 100), randomDate(2016, 2017), randomDate(2017, 2018)),
                        new WarehouseAvailabilityByDateThirdPartyRS(randomString(6), randomString(10), randomInt(0, 100), randomDouble(10, 100), randomDate(), randomDate()),
                        new WarehouseAvailabilityByDateThirdPartyRS(randomString(6), randomString(10), randomInt(0, 100), randomDouble(10, 100), randomDate(), randomDate()),
                        new WarehouseAvailabilityByDateThirdPartyRS(randomString(6), randomString(10), randomInt(0, 100), randomDouble(10, 100), randomDate(), randomDate())
                )
        );

        fakeReportList = new ArrayList<>();
    }

    @BeforeEach
    void mockSetup() {
        Mockito.when(warehouseRepository.findAllByAvailabilityTrue()).thenAnswer(invocation -> this.fakeWarehouseAvailabilityList);

        Mockito.when(warehouseRepository.findAllByAvailabilityTrueAndDateBetween(Mockito.notNull(), Mockito.notNull())).thenAnswer(invocation ->
            findByDateInBetween(invocation.getArgument(0), invocation.getArgument(1))
        );

        Mockito.when(reportService.saveNewReportRequestAsync(Mockito.notNull())).then(invocation -> {
            ReportType reportType = invocation.getArgument(0);

            Report report = new Report(reportType);

            int startRange = 1;
            if (!fakeReportList.isEmpty()) startRange = fakeReportList.get(fakeReportList.size() - 1).getId().intValue();

            report.setId(Long.valueOf(randomInt(startRange, startRange * 2)));

            fakeReportList.add(report);

            fakeReportList.sort(Comparator.comparing(Report::getId));

            return null;
        });
    }

    @Test
    void getAvailabilityTest() {
        List<WarehouseAvailabilityRS> availability = warehouseService.getAvailability();

        Assertions.assertEquals(fakeWarehouseAvailabilityList.size(), availability.size());

        boolean allExist = availability.stream().allMatch(item ->
                fakeWarehouseAvailabilityList.stream().anyMatch(itemThirdParty -> item.getCode().equals(itemThirdParty.getCode()))
        );

        Assertions.assertTrue(allExist);

        Assertions.assertTrue(fakeReportList.stream().anyMatch(r -> r.getType().equals(ReportType.WAREHOUSE_AVAILABILITY)));

    }

    @Test
    void getAvailabilityByDateTest() {
        Date start = randomDate(2015, 2016);
        Date end = randomDate(2019, 2021);

        List<WarehouseAvailabilityByDateRS> availabilityByDate = warehouseService.getAvailabilityByDate(new WarehouseAvailabilityByDateRQ(start, end));
        List<WarehouseAvailabilityByDateThirdPartyRS> fakeThirdPartyByDate = findByDateInBetween(start, end);

        Assertions.assertEquals(availabilityByDate.size(), fakeThirdPartyByDate.size());

        if (!availabilityByDate.isEmpty() && !fakeThirdPartyByDate.isEmpty()) {
            boolean allExist = availabilityByDate.stream().allMatch(item ->
                    fakeThirdPartyByDate.stream().anyMatch(itemThirdParty -> item.getCode().equals(itemThirdParty.getCode()))
            );

            Assertions.assertTrue(allExist);
        }

        Assertions.assertTrue(fakeReportList.stream().anyMatch(r -> r.getType().equals(ReportType.WAREHOUSE_AVAILABILITY_BY_DATE)));
    }

    private List<WarehouseAvailabilityByDateThirdPartyRS> findByDateInBetween(Date start, Date end) {
        return fakeWarehouseAvailabilityByDateList.stream().filter(data ->
                (data.getIncomingDate().after(start) || data.getIncomingDate().equals(start)) &&
                        (data.getOutgoingDate().before(end) || data.getOutgoingDate().equals(end))
        ).collect(Collectors.toList());
    }
}
