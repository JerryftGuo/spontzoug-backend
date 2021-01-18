package com.spontzoug.server.service;


import com.spontzoug.server.http.RetailerOrderRegionReport;
import com.spontzoug.server.model.RetailerOrder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

public interface IRetailerOrderService {
    void create(RetailerOrder b);
    Mono<RetailerOrder> findById(final String id);
    Mono<RetailerOrder> update(RetailerOrder service);
    Mono<Void> deleteById(final String id);
    Flux<RetailerOrder> findByBusinessidAndDate(
            final String id, final Date date1, final Date date2);
    Flux<RetailerOrder> findByBusinessidAndDateInc(
            final String id, final Date date1, final Date date2, final Date timestamp);
    Flux<RetailerOrder> findByClientidAndDate(
            final String id, final Date date1, final Date date2);
    Mono<RetailerOrder> findBySerial(final String serial);
    Flux<RetailerOrder> findDayReport(
            final String id, final Date date1, final Date date2);
    Flux<RetailerOrder> findMonthReport(
            final String id, final Date date1, final Date date2);
    Flux<RetailerOrder> findDayReportInc(
            final String id, final Date date1, final Date date2, final Date timestamp);
    Flux<RetailerOrder> findMonthReportInc(
            final String id, final Date date1, final Date date2, final Date timestamp);
/*

    Flux<RetailerOrderRegionReport> findRegionDayReport(
            final String region, final Date date1, final Date date2);
    Flux<RetailerOrderRegionReport> findRegionMonthReport(
            final String region, final Date date1, final Date date2);
    Flux<RetailerOrderRegionReport> findRegionDayReportInc(
            final String region, final Date date1, final Date date2, final Date timestamp);
    Flux<RetailerOrderRegionReport> findRegionMonthReportInc(
            final String region, final Date date1, final Date date2, final Date timestamp);


 */
}