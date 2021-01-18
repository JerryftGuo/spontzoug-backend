package com.spontzoug.server.service;


import com.spontzoug.server.model.Order;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

public interface IOrderService {
    void create(Order b);
    Mono<Order> findById(final String id);
    Mono<Order> update(Order service);
    Mono<Void> deleteById(final String id);
    Flux<Order> findByBusinessidAndDate(final String id, final Date date1, final Date date2);
    Flux<Order> findByClientidAndDate(final String id, final Date date1, final Date date2);
    Mono<Order> findBySerial(final String serial);
    Flux<Order> findDayReport(
            final String id, final Date date1, final Date date2);
    Flux<Order> findMonthReport(
            final String id, final Date date1, final Date date2);
}