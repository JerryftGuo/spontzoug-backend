package com.spontzoug.server.service;

import com.spontzoug.server.model.Reservation;
import com.spontzoug.server.repository.IReservationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;
@Slf4j
@Service
public class ReservationService implements IReservationService {
    @Autowired
    private IReservationRepository reservationRepository;

    public  void create(Reservation reservation){
        reservationRepository.save(reservation).subscribe();
    }
    public Mono<Reservation> update(Reservation reservation){
        return reservationRepository.save(reservation);
    }
    public Mono<Void> deleteById(final String id){
        return reservationRepository.deleteById(id);
    }

    public Flux<Reservation> findByBusinessidAndDate(
            final String id, final Date date1, final Date date2){
        return reservationRepository.findBusinessidAndDate(id,date1, date2);
    }

    public Flux<Reservation> findByBusinessidAndDateInc(
            final String id, final Date date1, final Date date2, final  Date timestamp){
        return reservationRepository.findBusinessidAndDateInc(id,date1, date2, timestamp);
    }

    public Mono<Reservation> findById(final String id){
        return reservationRepository.findById(id);
    }

}