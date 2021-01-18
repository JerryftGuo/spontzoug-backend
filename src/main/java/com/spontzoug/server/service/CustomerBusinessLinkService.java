package com.spontzoug.server.service;

import com.spontzoug.server.model.Customer;
import com.spontzoug.server.model.CustomerBusinessLink;
import com.spontzoug.server.repository.ICustomerBusinessLinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@Service
public class CustomerBusinessLinkService implements ICustomerBusinessLinkService {
    @Autowired
    private ICustomerBusinessLinkRepository userRepository;

    public void create(CustomerBusinessLink user) {
        userRepository.save(user).subscribe();
    }

    public Mono<CustomerBusinessLink> update(CustomerBusinessLink user){
        return userRepository.save(user);
    }

    public Mono<Void> deleteById(final String id){
        return userRepository.deleteById(id);
    }

    public Mono<CustomerBusinessLink> findById(final String id){
        return userRepository.findById(id);
    }
    public Flux<CustomerBusinessLink> findByCustomerid(final String id) {
        return userRepository.findByCustomerid(id);
    }

    public Flux<CustomerBusinessLink> findByBusinessid(
            final String bid){
        return userRepository.findByBusinessid(bid);
    }
}