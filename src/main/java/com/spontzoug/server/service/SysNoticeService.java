package com.spontzoug.server.service;

import com.spontzoug.server.model.SysNotice;
import com.spontzoug.server.redis.ISseService;
import com.spontzoug.server.repository.ISysNoticeRepository;
import com.spontzoug.server.util.SseEvent;
import com.spontzoug.server.util.SseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@Service
public class SysNoticeService implements ISysNoticeService {
    @Autowired
    private ISysNoticeRepository sysNoticeRepository;

    public void create(SysNotice sysNotice)
    {
            sysNoticeRepository.save(sysNotice).subscribe();
    }

    public Mono<SysNotice> update(SysNotice sysNotice)
    {
         return sysNoticeRepository.save(sysNotice);

    }

    public Mono<Void> deleteById(final String id){
        return sysNoticeRepository.deleteById(id);
    }

    public Mono<SysNotice> findById(final String id){
        return sysNoticeRepository.findById(id);
    }
    public Flux<SysNotice> findByBusinessid(final String id) {
        return sysNoticeRepository.findByBusinessid(id);
    }
    public Flux<SysNotice> findByBusinessidInc(final String id,final  Date date) {
        return sysNoticeRepository.findByBusinessidInc(id,date);
    }
    public Flux<SysNotice> findDynamics(final String id) {
        return sysNoticeRepository.findDynamics(id);
    }
    public Flux<SysNotice> findDynamicsInc(final String id,final Date date) {
        return sysNoticeRepository.findDynamicsInc(id,date);
    }

    public Flux<SysNotice> findForBusiness() {
        return sysNoticeRepository.findForBusiness();
    }
    public Flux<SysNotice> findForBusinessInc(final Date date) {
        return sysNoticeRepository.findForBusinessInc(date);
    }
}