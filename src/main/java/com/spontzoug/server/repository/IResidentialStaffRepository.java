package com.spontzoug.server.repository;

import com.spontzoug.server.model.ResidentialStaff;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

public interface IResidentialStaffRepository extends ReactiveMongoRepository<ResidentialStaff,String> {
    @Query(value="{'businessid':?0}")
    Flux<ResidentialStaff> findByBusinessid(final String id);
    @Query(value="{'businessid':?0,'modified':{'$gt':?1}}")
    Flux<ResidentialStaff> findByBusinessidInc(final String id, final Date date);
    @Query(value="{'businessid':?0}",
            fields="{'address':0, 'description':0,'vacation':0," +
                    " 'email':0, 'created':0,'modified':0, 'creator':0}")
    Flux<ResidentialStaff> findDynamics(final String id);
    @Query(value="{'businessid':?0,'modified':{'$gt':?1}}",
            fields="{'address':0, 'description':0,'vacation':0," +
                    " 'email':0, 'created':0,'modified':0, 'creator':0}")
    Flux<ResidentialStaff> findDynamicsInc(final String id, final Date date);

    @Query(value="{'businessid':?0, 'closed':false }",  count = true )
    Mono<Long> countActive(final String id );

    @Query(value="{'businessid':?0,'closed':true,'modified':{'$gt': ?1, '$lt': ?2} }",  count = true )
    Mono<Long> countClosingResidentialStaff(final String id, final Date date1, final Date date2);

}