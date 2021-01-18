package com.spontzoug.server.service;

import com.spontzoug.server.model.Config;
import com.spontzoug.server.repository.IConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@Service
public class ConfigService implements IConfigService {
    @Autowired
    private IConfigRepository configRepository;

    public void create(Config config) {
        configRepository.save(config).subscribe();
    }

    public Mono<Config> update(Config config){
        return configRepository.save(config);
    }

    public Mono<Void> deleteById(final String id){
        return configRepository.deleteById(id);
    }

    public Mono<Config> findById(final String id){
        return configRepository.findById(id);
    }
    public Mono<Config> findProductByName(final String name) {
        return configRepository.findProductByName(name);
    }
    public Mono<Config> findBetaByName(final String name) {
        return configRepository.findBetaByName(name);
    }

    public Mono<Config> findProductIncByName(final String name, final Date date) {
        return configRepository.findProductIncByName(name, date);
    }
    public Mono<Config> findBetaIncByName(final String name, final Date date) {
        return configRepository.findBetaIncByName(name, date);
    }
    public Flux<Config> findIndustryByName(final String name) {
        return configRepository.findIndustryByName(name);
    }
    public Flux<Config> findIndustryAll() {
        return configRepository.findIndustryAll();
    }
    public Flux<Config> findIndustryIncByName(final String name, final Date date) {
        return configRepository.findIndustryIncByName(name,date);
    }
    public Flux<Config> findIndustryIncAll(final Date date) {
        return configRepository.findIndustryIncAll(date);
    }
}