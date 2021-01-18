package com.spontzoug.server.service;

import com.spontzoug.server.model.Customer;
import com.spontzoug.server.model.CustomerProfile;
import com.spontzoug.server.repository.ICustomerBusinessLinkRepository;
import com.spontzoug.server.repository.ICustomerProfileRepository;
import com.spontzoug.server.util.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;

@Service
public class CustomerProfileService implements ICustomerProfileService {
    @Autowired
    private ICustomerProfileRepository userProfileRepository;
    @Autowired
    private ICustomerBusinessLinkRepository userBusinessLinkRepository;


    public void create(CustomerProfile userProfile) {
        userProfileRepository.save(userProfile).subscribe();
    }

    public Mono<CustomerProfile> update(CustomerProfile userProfile){
        return userProfileRepository.save(userProfile);
    }

    public Mono<Void> deleteById(final String id){
        return userProfileRepository.deleteById(id);
    }

    public Mono<CustomerProfile> findById(final String id){
        return userProfileRepository.findById(id);
    }
    public Mono<CustomerProfile> findByCustomerid(final String id){
        return userProfileRepository.findByCustomerid(id);
    }
    public Flux<CustomerProfile> findByCreator(final String id){
        return userProfileRepository.findByCreator(id);
    }


    public Flux<CustomerProfile> findByBusinessid(final String id)
    {
        return userBusinessLinkRepository.findByBusinessid(id)
                .flatMap( link ->{
                    return userProfileRepository.findByAdmin(link.getCustomerid());
                });
    }

}