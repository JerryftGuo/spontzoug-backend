package com.spontzoug.server.util;

import reactor.core.publisher.Mono;

import java.util.Date;

@FunctionalInterface
public interface IBillingCounter {
    Mono<Counter> counterTotal(final String id, final Date date1, final Date date2);
}