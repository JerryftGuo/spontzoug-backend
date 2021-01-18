package com.spontzoug.server.controller;

import com.spontzoug.server.redis.IInvitationService;
import com.spontzoug.server.http.*;

import com.spontzoug.server.model.Business;
import com.spontzoug.server.model.PaymentMethod;
import com.spontzoug.server.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/api/invitation")
public class InvitationController {
    @Autowired
    private IInvitationService invitationService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.OK)
    public Mono<InvitationResponse> create(
            @RequestBody InvitationRequest request) {
        return invitationService.create(request);
    }
}
