package com.spontzoug.server.service;

import com.spontzoug.server.model.User;
import com.spontzoug.server.model.UserBusinessLink;
import com.spontzoug.server.repository.IUserBusinessLinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@Service
public class UserBusinessLinkService implements IUserBusinessLinkService {
    @Autowired
    private IUserBusinessLinkRepository userRepository;

    public void create(UserBusinessLink user) {
        userRepository.save(user).subscribe();
    }

    public Mono<UserBusinessLink> update(UserBusinessLink user){
        return userRepository.save(user);
    }

    public Mono<Void> deleteById(final String id){
        return userRepository.deleteById(id);
    }

    public Mono<UserBusinessLink> findById(final String id){
        return userRepository.findById(id);
    }
    public Flux<UserBusinessLink> findByUserid(final String id) {
        return userRepository.findByUserid(id);
    }
    public Flux<UserBusinessLink> findAllInc(final Date date) {
        return userRepository.findAllInc(date);
    }
    public Flux<UserBusinessLink> findAll(){
        return userRepository.findAll();
    }
    public Mono<UserBusinessLink> findByBusinessStaffid(
            final String bid, final String sid){
        return userRepository.findByBusinessStaffid(bid, sid);
    }
    public Flux<UserBusinessLink> findByBusinessid(
            final String bid){
        return userRepository.findByBusinessid(bid);
    }
}