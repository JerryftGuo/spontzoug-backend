package com.spontzoug.server.service;

import com.spontzoug.server.model.Order;
import com.spontzoug.server.repository.IOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@Service
public class OrderService implements IOrderService {
    @Autowired
    private IOrderRepository orderRepository;

    public void create(Order order) {
        orderRepository.save(order).subscribe();
    }

    public Mono<Order> update(Order order){
        return orderRepository.save(order);
    }

    public Mono<Void> deleteById(final String id){
        return orderRepository.deleteById(id);
    }

    public Mono<Order> findById(final String id){
        return orderRepository.findById(id);
    }
    public Flux<Order> findByBusinessidAndDate(final String id, final Date date1, final Date date2) {
        return orderRepository.findByBusinessidAndDate(id, date1, date2);
    }
    public Flux<Order> findByClientidAndDate(final String id, final Date date1, final Date date2) {
        return orderRepository.findByClientidAndDate(id, date1, date2);
    }
    public Mono<Order> findBySerial(final String serial) {
        return orderRepository.findBySerial(serial);
    }
    public Flux<Order> findDayReport(
            final String id, final Date date1, final Date date2){
        return orderRepository.findDayReport(id,date1, date2);
    }
    public Flux<Order> findMonthReport(
            final String id, final Date date1, final Date date2){
        return orderRepository.findMonthReport(id,date1, date2);
    }
}