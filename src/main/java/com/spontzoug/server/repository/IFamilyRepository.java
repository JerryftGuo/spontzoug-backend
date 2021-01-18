package com.spontzoug.server.repository;

import com.spontzoug.server.model.Family;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

public interface IFamilyRepository extends ReactiveMongoRepository<Family,String> {
    @Query(value="{ 'members.firstName':?0}}")
    Flux<Family> findByFirstNameIgnoreCase(final String firstName);
    @Query(value="{'businessid':?0}")
    Flux<Family> findByBusinessid(final String id);
    @Query(value="{'businessid':?0, 'modified':{'$gte':?1} }")
    Flux<Family> findByBusinessidInc(final String id, final Date date);
    @Query(value="{'businessid':?0, 'phone':?1}", count = true)
    Mono<Long> countByBusinessIdAndPhone(final String businessid, final String phone);
    @Query(value="{'businessid':?0}",
            fields="{'address':0, 'members.phone':0, 'members.dateofbirth':0, " +
                    "'members.gender':0, 'created':0, 'modified':0, 'creator':0}")
    Flux<Family> findFamilies(final String id);
    @Query(value="{'businessid':?0}",
            fields="{'members.dateofbirth':0, " +
                    "'members.gender':0, 'created':0, 'modified':0, 'creator':0}")
    Flux<Family> findDynamics(final String id);
    @Query(value="{'businessid':?0,'modified':{'$gt':?1}}",
            fields="{'members.dateofbirth':0, " +
                    "'members.gender':0, 'created':0, 'modified':0, 'creator':0}")
    Flux<Family> findDynamicsInc(final String id, final Date date);
}