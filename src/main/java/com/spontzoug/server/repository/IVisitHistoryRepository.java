package com.spontzoug.server.repository;

import com.spontzoug.server.model.Business;
import com.spontzoug.server.model.VisitHistory;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

public interface IVisitHistoryRepository extends ReactiveMongoRepository<VisitHistory, String> {
    @Query("{'businessid':?0,'familyid':?1, 'memberid':?2}")
    Flux<VisitHistory> findByBusinessFamilyId(final String bid, final String fid, final String mid);
    @Query("{'businessid':?0,'familyid':?1, 'memberid':?2,'modified':{'$gt':?3 } }")
    Flux<VisitHistory> findByBusinessFamilyIdInc(final String bid, final String fid, final String mid, final Date date);
}