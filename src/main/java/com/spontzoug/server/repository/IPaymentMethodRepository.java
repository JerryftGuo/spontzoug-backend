package com.spontzoug.server.repository;

import com.spontzoug.server.model.PaymentMethod;
import com.spontzoug.server.model.PaymentMethod;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

public interface IPaymentMethodRepository extends ReactiveMongoRepository<PaymentMethod, String> {
    @Query(value = "{'businessid':?0}")
    Flux<PaymentMethod> findDynamics(final String id);
    @Query(value = "{'businessid':?0,'modified':{'$gt':?1}}")
    Flux<PaymentMethod> findDynamicsInc(final String id, final Date date);
    @Query("{'businessid':?0}")
    Flux<PaymentMethod> findByBusinessid(final String id);
    @Query(value = "{'businessid':?0,'modified':{'$gt':?1}}")
    Flux<PaymentMethod> findByBusinessidInc(final String id, final Date date);
}