package com.spontzoug.server.service;

import com.spontzoug.server.model.ResidentialAppointment;
import com.spontzoug.server.repository.IResidentialAppointmentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;
@Slf4j
@Service
public class ResidentialAppointmentService implements IResidentialAppointmentService {
    @Autowired
    private IResidentialAppointmentRepository appointmentRepository;

    public  void create(ResidentialAppointment appointment){
        appointmentRepository.save(appointment).subscribe();
    }
    public Mono<ResidentialAppointment> update(ResidentialAppointment appointment){
        return appointmentRepository.save(appointment);
    }
    public Mono<Void> deleteById(final String id){
        return appointmentRepository.deleteById(id);
    }

    public Flux<ResidentialAppointment> findByBusinessidAndDate(
            final String id, final Date date1, final Date date2){
        return appointmentRepository.findBusinessidAndDate(id,date1, date2);
    }

    public Flux<ResidentialAppointment> findByBusinessidAndDateInc(
            final String id, final Date date1, final Date date2, final  Date timestamp){
        return appointmentRepository.findBusinessidAndDateInc(id,date1, date2, timestamp);
    }

    public Mono<ResidentialAppointment> findById(final String id){
        return appointmentRepository.findById(id);
    }

    public Flux<ResidentialAppointment> findDayReport(
            final String id, final Date date1, final Date date2){
        return appointmentRepository.findDayReport(id,date1, date2);
    }
    public Flux<ResidentialAppointment> findMonthReport(
            final String id, final Date date1, final Date date2){
        return appointmentRepository.findMonthReport(id,date1, date2);
    }

    public Flux<ResidentialAppointment> findDayReportInc(
            final String id, final Date date1, final Date date2, final Date timestamp){
        return appointmentRepository.findDayReportInc(id,date1, date2, timestamp);
    }
    public Flux<ResidentialAppointment> findMonthReportInc(
            final String id, final Date date1, final Date date2, final Date timestamp){
        return appointmentRepository.findMonthReportInc(id,date1, date2, timestamp);
    }

}