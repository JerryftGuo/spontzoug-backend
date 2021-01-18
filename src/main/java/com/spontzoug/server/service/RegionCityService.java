package com.spontzoug.server.service;

import com.spontzoug.server.model.RegionCity;
import com.spontzoug.server.repository.IRegionCityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@Service
public class RegionCityService implements IRegionCityService {
    @Autowired
    private IRegionCityRepository regionCityRepository;

    public void create(RegionCity regionCity){
        regionCityRepository.save(regionCity).subscribe();
    }
    public Mono<RegionCity> update(RegionCity regionCity){
        return regionCityRepository.save(regionCity);
    }
    public Mono<Void> deleteById(final String id){
        return regionCityRepository.deleteById(id);
    }
    public Mono<RegionCity> findById(final String id){
        return regionCityRepository.findById(id);
    }
    public Flux<RegionCity> findAll(){
        return regionCityRepository.findAll();
    }
    public Flux<RegionCity> findAllInc(final Date date){
        return regionCityRepository.findAllInc(date);
    }
    public Mono<Long> countByRegionCity(final String region, final String city){
        return regionCityRepository.countByRegionCity(region, city);
    }

}