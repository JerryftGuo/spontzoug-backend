package com.spontzoug.server.service;

import com.spontzoug.server.model.Family;
import com.spontzoug.server.repository.IFamilyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@Service
public class FamilyService implements IFamilyService {
    @Autowired
    private IFamilyRepository familyRepository;

    public void create(Family family) {
        familyRepository.save(family).subscribe();
    }
    public Mono<Family> update(Family family){
        return familyRepository.save(family);
    }
    public Mono<Void> deleteById(String id){
        return familyRepository.deleteById(id);
    }

    public Mono<Family> findById(final String id) {
        return familyRepository.findById(id);
    }
    public Flux<Family> findByFirstName(final String firstName){
        return familyRepository.findByFirstNameIgnoreCase(firstName);
    }
    public Mono<Long> countByBusinessIdAndPhone(final String businessid,final String phone){
        return familyRepository.countByBusinessIdAndPhone(businessid,phone);
    }
    public Flux<Family> findByBusinessid(final String id){
        return familyRepository.findByBusinessid(id);
    }
    public Flux<Family> findByBusinessidInc(
            final String id, final Date date){
        return familyRepository.findByBusinessidInc(id,date);
    }
    public Flux<Family> findFamilies(final String id) { return familyRepository.findFamilies((id)); }
    public Flux<Family> findDynamics(final String id) { return familyRepository.findDynamics((id)); }
    public Flux<Family> findDynamicsInc(final String id, final Date date) {
        return familyRepository.findDynamicsInc(id,date); }

}