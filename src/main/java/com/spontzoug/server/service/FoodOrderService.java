package com.spontzoug.server.service;

import com.spontzoug.server.http.FoodOrderRegionReport;
import com.spontzoug.server.model.FoodOrder;
import com.spontzoug.server.repository.IFoodOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@Service
public class FoodOrderService implements IFoodOrderService {
    @Autowired
    private IFoodOrderRepository orderRepository;

    public void create(FoodOrder order) {
        orderRepository.save(order).subscribe();
    }

    public Mono<FoodOrder> update(FoodOrder order){
        return orderRepository.save(order);
    }

    public Mono<Void> deleteById(final String id){
        return orderRepository.deleteById(id);
    }

    public Mono<FoodOrder> findById(final String id){
        return orderRepository.findById(id);
    }
    public Flux<FoodOrder> findByBusinessidAndDate(
            final String id, final Date date1, final Date date2) {
        return orderRepository.findByBusinessidAndDate(id, date1, date2);
    }
    public Flux<FoodOrder> findByBusinessidAndDateInc(
            final String id, final Date date1, final Date date2, final Date timestamp) {
        return orderRepository.findByBusinessidAndDateInc(id, date1, date2, timestamp);
    }
    public Flux<FoodOrder> findByClientidAndDate(final String id, final Date date1, final Date date2) {
        return orderRepository.findByClientidAndDate(id, date1, date2);
    }
    public Mono<FoodOrder> findBySerial(final String serial) {
        return orderRepository.findBySerial(serial);
    }
    public Flux<FoodOrder> findDayReport(
            final String id, final Date date1, final Date date2){
        return orderRepository.findDayReport(id,date1, date2);
    }
    public Flux<FoodOrder> findMonthReport(
            final String id, final Date date1, final Date date2){
        return orderRepository.findMonthReport(id,date1, date2);
    }

    public Flux<FoodOrder> findDayReportInc(
            final String id, final Date date1, final Date date2, final Date timestamp){
        return orderRepository.findDayReportInc(id,date1, date2, timestamp);
    }
    public Flux<FoodOrder> findMonthReportInc(
            final String id, final Date date1, final Date date2, final Date timestamp){
        return orderRepository.findMonthReportInc(id,date1, date2, timestamp);
    }
/*
    public Flux<FoodOrderRegionReport> findRegionDayReport(
            final String region, final Date date1, final Date date2){
        return orderRepository.findRegionDayReport(region,date1, date2);
    }
    public Flux<FoodOrderRegionReport> findRegionMonthReport(
            final String region, final Date date1, final Date date2){
        return orderRepository.findRegionMonthReport(region,date1, date2);
    }

    public Flux<FoodOrderRegionReport> findRegionDayReportInc(
            final String region, final Date date1, final Date date2, final Date timestamp){
        return orderRepository.findRegionDayReportInc(region,date1, date2, timestamp);
    }
    public Flux<FoodOrderRegionReport> findRegionMonthReportInc(
            final String region, final Date date1, final Date date2, final Date timestamp){
        return orderRepository.findRegionMonthReportInc(region,date1, date2, timestamp);
    }

 */
}