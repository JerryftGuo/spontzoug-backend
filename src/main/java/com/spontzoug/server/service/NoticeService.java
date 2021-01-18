package com.spontzoug.server.service;

import com.spontzoug.server.model.Notice;
import com.spontzoug.server.redis.ISseService;
import com.spontzoug.server.repository.INoticeRepository;
import com.spontzoug.server.util.SseEvent;
import com.spontzoug.server.util.SseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@Service
public class NoticeService implements INoticeService {
    @Autowired
    private INoticeRepository noticeRepository;
    @Autowired
    ISseService sseService;

    public void create(Notice notice)
    {
            noticeRepository.save(notice).subscribe();
    }

    public Mono<Notice> update(Notice notice)
    {
         return noticeRepository.save(notice);

    }

    public Mono<Void> deleteById(final String id){
        return noticeRepository.deleteById(id);
    }

    public Mono<Notice> findById(final String id){
        return noticeRepository.findById(id);
    }
    public Flux<Notice> findByBusinessid(final String id) {
        return noticeRepository.findByBusinessid(id);
    }
    public Flux<Notice> findByBusinessidInc(final String id,final  Date date) {
        return noticeRepository.findByBusinessidInc(id,date);
    }
    public Flux<Notice> findDynamics(final String id) {
        return noticeRepository.findDynamics(id);
    }
    public Flux<Notice> findDynamicsInc(final String id,final Date date) {
        return noticeRepository.findDynamicsInc(id,date);
    }
}