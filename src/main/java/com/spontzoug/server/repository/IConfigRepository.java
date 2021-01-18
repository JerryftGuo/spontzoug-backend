package com.spontzoug.server.repository;

import com.spontzoug.server.model.Config;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

public interface IConfigRepository extends ReactiveMongoRepository<Config, String> {
    @Query("{'name':?0,'release':'prod'}")
    Mono<Config> findProductByName(final String name);
    @Query("{'name':?0,'release':'beta'}")
    Mono<Config> findBetaByName(final String name);
    @Query("{'name':?0,'release':'prod',modified:{'$gt':?1}}")
    Mono<Config> findProductIncByName(final String name, final Date date);
    @Query("{'name':?0,'release':'beta',modified:{'$gt':?1}}")
    Mono<Config> findBetaIncByName(final String name, final Date date);
    @Query("{'release':'prod','$or':[{'name':'basic'},{'name':'data'},{'name':?0}]}")
 //   @Query("{'release':'prod'}")
    Flux<Config> findIndustryByName(final String name);
    @Query("{'release':'prod'}")
    Flux<Config> findIndustryAll();
    @Query("{'release':'prod','$or':[{'name':'basic',},{'name':'data'},{'name':?0}],modified:{'$gt':?1} }")
    Flux<Config> findIndustryIncByName(final String name,final Date date);
    @Query("{'release':'prod',modified:{'$gte':?0}}")
    Flux<Config> findIndustryIncAll(final Date date);
    @Query("{'release':'prod','$or':[{'name':'basic'},{'name':'data'}]}")
    Flux<Config> findBasicConfig();

}