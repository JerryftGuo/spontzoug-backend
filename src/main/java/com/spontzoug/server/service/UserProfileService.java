package com.spontzoug.server.service;

import com.spontzoug.server.model.User;
import com.spontzoug.server.model.UserProfile;
import com.spontzoug.server.repository.IUserBusinessLinkRepository;
import com.spontzoug.server.repository.IUserProfileRepository;
import com.spontzoug.server.util.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;

@Service
public class UserProfileService implements IUserProfileService {
    @Autowired
    private IUserProfileRepository userProfileRepository;
    @Autowired
    private IUserBusinessLinkRepository userBusinessLinkRepository;


    public void create(UserProfile userProfile) {
        userProfileRepository.save(userProfile).subscribe();
    }

    public Mono<UserProfile> update(UserProfile userProfile){
        return userProfileRepository.save(userProfile);
    }

    public Mono<Void> deleteById(final String id){
        return userProfileRepository.deleteById(id);
    }

    public Mono<UserProfile> findById(final String id){
        return userProfileRepository.findById(id);
    }
    public Mono<UserProfile> findByUserid(final String id){
        return userProfileRepository.findByUserid(id);
    }
    public Flux<UserProfile> findByCreator(final String id){
        return userProfileRepository.findByCreator(id);
    }


    public Flux<UserProfile> findByBusinessid(final String id)
    {
        return userBusinessLinkRepository.findByBusinessid(id)
                .filter(ln -> ln.getRole().getRole().equals(Roles.RL_ENT_ADMIN))
                .flatMap( link ->{
                    return userProfileRepository.findByAdmin(link.getUserid());
                });
    }

}