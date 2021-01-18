package com.spontzoug.server.service;

import com.spontzoug.server.model.ClinicAppointment;
import com.spontzoug.server.repository.IClinicAppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@Service
public class ClinicAppointmentService implements IClinicAppointmentService {
    @Autowired
    private IClinicAppointmentRepository appointmentRepository;

    public  void create(ClinicAppointment appointment){
        appointmentRepository.save(appointment).subscribe();
    }
    public Mono<ClinicAppointment> update(ClinicAppointment appointment){
        return appointmentRepository.save(appointment);
    }
    public Mono<Void> deleteById(final String id){
        return appointmentRepository.deleteById(id);
    }

    public Flux<ClinicAppointment> findByBusinessidAndStartAfterAndStartBefore(
            final String id, final Date date1, final Date date2){
        return appointmentRepository.findByBusinessidAndStartAfterAndStartBefore(id,date1, date2);
    }
    public Flux<ClinicAppointment> findByBusinessidAndDate(
            final String id, final Long date){
        return appointmentRepository.findByBusinessidAndDate(id,date);
    }
    public Mono<ClinicAppointment> findById(final String id){
        return appointmentRepository.findById(id);
    }
    public Flux<ClinicAppointment> findByFamilyidOrderByDateDesc(final String id, final Date date1){
        return this.appointmentRepository.findByFamilyidOrderByDateDesc( id, date1);
    }
    public Flux<ClinicAppointment> findDayReport(
            final String id, final Date date1, final Date date2){
        return appointmentRepository.findDayReport(id,date1, date2);
    }
    public Flux<ClinicAppointment> findMonthReport(
            final String id, final Date date1, final Date date2){
        return appointmentRepository.findMonthReport(id,date1, date2);
    }

}