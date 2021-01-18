package com.spontzoug.server.service;


import com.spontzoug.server.http.FoodOrderRegionReport;
import com.spontzoug.server.model.FoodOrder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

public interface IFoodOrderService {
    void create(FoodOrder b);
    Mono<FoodOrder> findById(final String id);
    Mono<FoodOrder> update(FoodOrder service);
    Mono<Void> deleteById(final String id);
    Flux<FoodOrder> findByBusinessidAndDate(
            final String id, final Date date1, final Date date2);
    Flux<FoodOrder> findByBusinessidAndDateInc(
            final String id, final Date date1, final Date date2, final Date timestamp);
    Flux<FoodOrder> findByClientidAndDate(
            final String id, final Date date1, final Date date2);
    Mono<FoodOrder> findBySerial(final String serial);
    Flux<FoodOrder> findDayReport(
            final String id, final Date date1, final Date date2);
    Flux<FoodOrder> findMonthReport(
            final String id, final Date date1, final Date date2);
    Flux<FoodOrder> findDayReportInc(
            final String id, final Date date1, final Date date2, final Date timestamp);
    Flux<FoodOrder> findMonthReportInc(
            final String id, final Date date1, final Date date2, final Date timestamp);
/*
    Flux<FoodOrderRegionReport> findRegionDayReport(
            final String region, final Date date1, final Date date2);
    Flux<FoodOrderRegionReport> findRegionMonthReport(
            final String region, final Date date1, final Date date2);
    Flux<FoodOrderRegionReport> findRegionDayReportInc(
            final String region, final Date date1, final Date date2, final Date timestamp);
    Flux<FoodOrderRegionReport> findRegionMonthReportInc(
            final String region, final Date date1, final Date date2, final Date timestamp);

 */
}