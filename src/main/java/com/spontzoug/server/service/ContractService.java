package com.spontzoug.server.service;

import com.spontzoug.server.model.Business;
import com.spontzoug.server.model.Contract;
import com.spontzoug.server.repository.IContractRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;
@Slf4j
@Service
public class ContractService implements IContractService {
    @Autowired
    private IContractRepository contractRepository;

    public  void create(Contract contract){
        contractRepository.save(contract).subscribe();
    }
    public Mono<Contract> update(Contract contract){
        return contractRepository.save(contract);
    }
    public Mono<Void> deleteById(final String id){
        return contractRepository.deleteById(id);
    }

    public Flux<Contract> findByRegion(final String region)
    {
        return contractRepository.findByRegion(region);
    }

    public Flux<Contract> findByRegionInc(final String region, final Date date) {
        return contractRepository.findByRegionInc(region,date);
    }
    public Flux<Contract> findByBusinessid( final String id)
    {
        return contractRepository.findByBusinessid(id);
    }
    public Flux<Contract> findByBusinessidInc( final String id, final Date date)
    {
        return contractRepository.findByBusinessidInc(id,date);
    }
    public Mono<Contract> findById(final String id){
        return contractRepository.findById(id);
    }

    public Flux<Contract> findDayReport(
            final String id, final Date date1, final Date date2){
        return contractRepository.findDayReport(id,date1, date2);
    }
    public Flux<Contract> findMonthReport(
            final String id, final Date date1, final Date date2){
        return contractRepository.findMonthReport(id,date1, date2);
    }

    public Flux<Contract> findDayReportInc(
            final String id, final Date date1, final Date date2, final Date timestamp){
        return contractRepository.findDayReportInc(id,date1, date2, timestamp);
    }
    public Flux<Contract> findMonthReportInc(
            final String id, final Date date1, final Date date2, final Date timestamp){
        return contractRepository.findMonthReportInc(id,date1, date2, timestamp);
    }

}