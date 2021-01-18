package com.spontzoug.server.service;

import com.spontzoug.server.model.AutomativeSaleAppointment;
import com.spontzoug.server.repository.IAutomativeSaleAppointmentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;
@Slf4j
@Service
public class AutomativeSaleAppointmentService implements IAutomativeSaleAppointmentService {
    @Autowired
    private IAutomativeSaleAppointmentRepository appointmentRepository;

    public  void create(AutomativeSaleAppointment appointment){
        appointmentRepository.save(appointment).subscribe();
    }
    public Mono<AutomativeSaleAppointment> update(AutomativeSaleAppointment appointment){
        return appointmentRepository.save(appointment);
    }
    public Mono<Void> deleteById(final String id){
        return appointmentRepository.deleteById(id);
    }

    public Flux<AutomativeSaleAppointment> findByBusinessidAndDate(
            final String id, final Date date1, final Date date2){
        return appointmentRepository.findBusinessidAndDate(id,date1, date2);
    }

    public Flux<AutomativeSaleAppointment> findByBusinessidAndDateInc(
            final String id, final Date date1, final Date date2, final  Date timestamp){
        return appointmentRepository.findBusinessidAndDateInc(id,date1, date2, timestamp);
    }

    public Mono<AutomativeSaleAppointment> findById(final String id){
        return appointmentRepository.findById(id);
    }

    public Flux<AutomativeSaleAppointment> findDayReport(
            final String id, final Date date1, final Date date2){
        return appointmentRepository.findDayReport(id,date1, date2);
    }
    public Flux<AutomativeSaleAppointment> findMonthReport(
            final String id, final Date date1, final Date date2){
        return appointmentRepository.findMonthReport(id,date1, date2);
    }

    public Flux<AutomativeSaleAppointment> findDayReportInc(
            final String id, final Date date1, final Date date2, final Date timestamp){
        return appointmentRepository.findDayReportInc(id,date1, date2, timestamp);
    }
    public Flux<AutomativeSaleAppointment> findMonthReportInc(
            final String id, final Date date1, final Date date2, final Date timestamp){
        return appointmentRepository.findMonthReportInc(id,date1, date2, timestamp);
    }

}