package com.spontzoug.server.service;

import com.spontzoug.server.model.Assistant;
import com.spontzoug.server.model.Business;
import com.spontzoug.server.model.User;
import com.spontzoug.server.repository.IUserBusinessLinkRepository;
import com.spontzoug.server.repository.IUserRepository;
import com.spontzoug.server.util.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@Service
public class UserService implements IUserService {
    @Autowired
    private IUserRepository userRepository;

    public void create(User user) {
        userRepository.save(user).subscribe();
    }

    public Mono<User> update(User user){
        return userRepository.save(user);
    }

    public Mono<Void> deleteById(final String id){
        return userRepository.deleteById(id);
    }

    public Mono<User> findById(final String id){
        return userRepository.findById(id);
    }
    public Mono<User> findByUsername(final String username) {
        return userRepository.findByUsername(username);
    }

    public Flux<User> findAllInc(final Date date) {
        return userRepository.findAllInc(date);
    }
    public Flux<User> findAll(){
        return userRepository.findAll();
    }

}