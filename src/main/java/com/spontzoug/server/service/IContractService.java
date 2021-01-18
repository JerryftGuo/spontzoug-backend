package com.spontzoug.server.service;

import com.spontzoug.server.http.AppointmentRegionReport;
import com.spontzoug.server.model.Business;
import com.spontzoug.server.model.Contract;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

public interface IContractService {
    void create(Contract appointment);
    Mono<Contract> update(Contract appointment);
    Mono<Void> deleteById(final String id);
    Mono<Contract> findById(final String id);
    Flux<Contract> findByRegion(final String region);
    Flux<Contract> findByRegionInc(final String region, final Date date);
    Flux<Contract> findByBusinessid(
            final String id);
    Flux<Contract> findByBusinessidInc(
            final String id, final Date date);

    Flux<Contract> findDayReport(
            final String id, final Date date1, final Date date2);
    Flux<Contract> findMonthReport(
            final String id, final Date date1, final Date date2);

    Flux<Contract> findDayReportInc(
            final String id, final Date date1, final Date date2, final Date timestamp);
    Flux<Contract> findMonthReportInc(
            final String id, final Date date1, final Date date2, final Date timestamp);

}