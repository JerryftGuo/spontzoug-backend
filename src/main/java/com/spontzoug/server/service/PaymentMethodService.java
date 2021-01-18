package com.spontzoug.server.service;

import com.spontzoug.server.model.PaymentMethod;
import com.spontzoug.server.repository.IPaymentMethodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@Service
public class PaymentMethodService implements IPaymentMethodService {
    @Autowired
    private IPaymentMethodRepository paymentMethodRepository;

    public void create(PaymentMethod paymentMethod) {
        paymentMethodRepository.save(paymentMethod).subscribe();
    }

    public Mono<PaymentMethod> update(PaymentMethod paymentMethod){
        return paymentMethodRepository.save(paymentMethod);
    }

    public Mono<Void> deleteById(final String id){
        return paymentMethodRepository.deleteById(id);
    }

    public Mono<PaymentMethod> findById(final String id){
        return paymentMethodRepository.findById(id);
    }
    public Flux<PaymentMethod> findDynamics(final String id) {
        return paymentMethodRepository.findDynamics(id);
    }
    public Flux<PaymentMethod> findDynamicsInc(final String id, final Date date) {
        return paymentMethodRepository.findDynamicsInc(id,date);
    }
    public Flux<PaymentMethod> findByBusinessid(final String id) {
        return paymentMethodRepository.findByBusinessid(id);
    }
    public Flux<PaymentMethod> findByBusinessidInc(final String id, final Date date) {
        return paymentMethodRepository.findByBusinessidInc(id,date);
    }
}