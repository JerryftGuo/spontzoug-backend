package com.spontzoug.server.service;

import com.spontzoug.server.model.PaymentMethod;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

public interface IPaymentMethodService {
    void create(PaymentMethod b);
    Mono<PaymentMethod> findById(final String id);
    Mono<PaymentMethod> update(PaymentMethod room);
    Mono<Void> deleteById(final String id);
    Flux<PaymentMethod> findDynamics(final String id);
    Flux<PaymentMethod> findDynamicsInc(final String id, final Date date);
    Flux<PaymentMethod> findByBusinessid(final String id);
    Flux<PaymentMethod> findByBusinessidInc(final String id, final Date date);
}