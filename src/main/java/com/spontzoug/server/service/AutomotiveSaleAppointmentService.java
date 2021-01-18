package com.spontzoug.server.service;

import com.spontzoug.server.model.AutomotiveSaleAppointment;
import com.spontzoug.server.repository.IAutomotiveSaleAppointmentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;
@Slf4j
@Service
public class AutomotiveSaleAppointmentService implements IAutomotiveSaleAppointmentService {
    @Autowired
    private IAutomotiveSaleAppointmentRepository appointmentRepository;

    public  void create(AutomotiveSaleAppointment appointment){
        appointmentRepository.save(appointment).subscribe();
    }
    public Mono<AutomotiveSaleAppointment> update(AutomotiveSaleAppointment appointment){
        return appointmentRepository.save(appointment);
    }
    public Mono<Void> deleteById(final String id){
        return appointmentRepository.deleteById(id);
    }

    public Flux<AutomotiveSaleAppointment> findByBusinessidAndDate(
            final String id, final Date date1, final Date date2){
        return appointmentRepository.findBusinessidAndDate(id,date1, date2);
    }

    public Flux<AutomotiveSaleAppointment> findByBusinessidAndDateInc(
            final String id, final Date date1, final Date date2, final  Date timestamp){
        return appointmentRepository.findBusinessidAndDateInc(id,date1, date2, timestamp);
    }

    public Mono<AutomotiveSaleAppointment> findById(final String id){
        return appointmentRepository.findById(id);
    }

    public Flux<AutomotiveSaleAppointment> findDayReport(
            final String id, final Date date1, final Date date2){
        return appointmentRepository.findDayReport(id,date1, date2);
    }
    public Flux<AutomotiveSaleAppointment> findMonthReport(
            final String id, final Date date1, final Date date2){
        return appointmentRepository.findMonthReport(id,date1, date2);
    }

    public Flux<AutomotiveSaleAppointment> findDayReportInc(
            final String id, final Date date1, final Date date2, final Date timestamp){
        return appointmentRepository.findDayReportInc(id,date1, date2, timestamp);
    }
    public Flux<AutomotiveSaleAppointment> findMonthReportInc(
            final String id, final Date date1, final Date date2, final Date timestamp){
        return appointmentRepository.findMonthReportInc(id,date1, date2, timestamp);
    }

}