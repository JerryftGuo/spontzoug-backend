package com.spontzoug.server.config;

import com.spontzoug.server.model.Business;
import com.spontzoug.server.model.User;
import com.spontzoug.server.model.UserBusinessLink;
import com.spontzoug.server.repository.IBusinessRepository;
import com.spontzoug.server.repository.IUserBusinessLinkRepository;
import com.spontzoug.server.repository.IUserRepository;
import com.spontzoug.server.util.*;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.awt.*;
import java.util.*;
import java.util.List;

@Log4j2
@Component
class Initializer implements ApplicationListener<ApplicationReadyEvent>{
    @Value("${initilization}")
    private String init;

    private final IUserRepository userRepository;
    private final IBusinessRepository businessRepository;
    private final IUserBusinessLinkRepository userBusinessLinkRepository;
    Initializer(IUserRepository userRepository, IBusinessRepository businessRepository, IUserBusinessLinkRepository userBusinessLinkRepository){
        this.userRepository = userRepository;
        this.businessRepository = businessRepository;
        this.userBusinessLinkRepository = userBusinessLinkRepository;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent){

        if( !init.toLowerCase().equals("true")) return;

        //    Address address = new Address();
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        String sysadmin= Roles.RL_SYS_ADMIN;
        GeoPoint geo = new GeoPoint();
        Address address = new Address("Street","Calgary","AB","T3a T3A", geo);
        ImageItem image = new ImageItem(0L,"image","");
        List<ImageItem> background= new ArrayList<>();
        List<ImageItem> logo = new ArrayList<>();
        background.add(image);
        logo.add(image);
        Mono<User> user = Mono.just( new User("","sysadmin001","email","{bcrypt}$2a$10$IRgjo1gG8GnbG96eNvtz1ug6/sKR431ZgwlKXYTHmH32K0.RfNpky","active",true,false, false,0, "","",date,date,date))
                .flatMap( user1 -> this.userRepository.save(user1));

        this.userRepository.deleteAll().then(user)
                .then( this.userRepository.findByUsername("sysadmin001"))
                .flatMap( res ->{
                      return this.businessRepository.deleteAll()
                            .then(
                                    Mono.just(new Business( "", "Spontzoug","Spontzoug Ltd.","System Service","system",
                                            "service","a service company","",address,background,logo,0, false, false, res.getId(),new Date(), new Date() ))
                                            .flatMap( biz -> this.businessRepository.save(biz))
                                            .then( this.businessRepository.findByCreator(res.getId()).next() )
                                            .flatMap( biz2 ->{
                                               String bizid = biz2.getId();
                                               BusinessRole  role= new BusinessRole(true,bizid,"",biz2.getBusinessname(), sysadmin);
                                               // List<BusinessRole> roles = new ArrayList<>();
                                               // roles.add(role);
                                                 return this.userBusinessLinkRepository.deleteAll().then(
                                                        Mono.just( new UserBusinessLink("",res.getId(),"system","","CA",role))
                                                    )
                                                    .flatMap( link -> this.userBusinessLinkRepository.save(link));
                                            })
                            );
                }).subscribe();
    }
}