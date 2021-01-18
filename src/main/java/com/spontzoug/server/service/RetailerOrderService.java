package com.spontzoug.server.service;

import com.spontzoug.server.http.RetailerOrderRegionReport;
import com.spontzoug.server.model.RetailerOrder;
import com.spontzoug.server.repository.IRetailerOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@Service
public class RetailerOrderService implements IRetailerOrderService {
    @Autowired
    private IRetailerOrderRepository orderRepository;

    public void create(RetailerOrder order) {
        orderRepository.save(order).subscribe();
    }

    public Mono<RetailerOrder> update(RetailerOrder order){
        return orderRepository.save(order);
    }

    public Mono<Void> deleteById(final String id){
        return orderRepository.deleteById(id);
    }

    public Mono<RetailerOrder> findById(final String id){
        return orderRepository.findById(id);
    }
    public Flux<RetailerOrder> findByBusinessidAndDate(
            final String id, final Date date1, final Date date2) {
        return orderRepository.findByBusinessidAndDate(id, date1, date2);
    }
    public Flux<RetailerOrder> findByBusinessidAndDateInc(
            final String id, final Date date1, final Date date2, final Date timestamp) {
        return orderRepository.findByBusinessidAndDateInc(id, date1, date2, timestamp);
    }
    public Flux<RetailerOrder> findByClientidAndDate(
            final String id, final Date date1, final Date date2) {
        return orderRepository.findByClientidAndDate(id, date1, date2);
    }
    public Mono<RetailerOrder> findBySerial(final String serial) {
        return orderRepository.findBySerial(serial);
    }
    public Flux<RetailerOrder> findDayReport(
            final String id, final Date date1, final Date date2){
        return orderRepository.findDayReport(id,date1, date2);
    }
    public Flux<RetailerOrder> findMonthReport(
            final String id, final Date date1, final Date date2){
        return orderRepository.findMonthReport(id,date1, date2);
    }

    public Flux<RetailerOrder> findDayReportInc(
            final String id, final Date date1, final Date date2, final Date timestamp){
        return orderRepository.findDayReportInc(id,date1, date2, timestamp);
    }
    public Flux<RetailerOrder> findMonthReportInc(
            final String id, final Date date1, final Date date2, final  Date timestamp){
        return orderRepository.findMonthReportInc(id,date1, date2, timestamp);
    }
/*
    public Flux<RetailerOrderRegionReport> findRegionDayReport(
            final String region, final Date date1, final Date date2){
        return orderRepository.findRegionDayReport(region,date1, date2);
    }
    public Flux<RetailerOrderRegionReport> findRegionMonthReport(
            final String region, final Date date1, final Date date2){
        return orderRepository.findRegionMonthReport(region,date1, date2);
    }

    public Flux<RetailerOrderRegionReport> findRegionDayReportInc(
            final String region, final Date date1, final Date date2, final Date timestamp){
        return orderRepository.findRegionDayReportInc(region,date1, date2, timestamp);
    }
    public Flux<RetailerOrderRegionReport> findRegionMonthReportInc(
            final String region, final Date date1, final Date date2, final  Date timestamp){
        return orderRepository.findRegionMonthReportInc(region,date1, date2, timestamp);
    }

 */
}