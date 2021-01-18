package com.spontzoug.server.service;

import com.spontzoug.server.model.HealthAppointment;
import com.spontzoug.server.repository.IHealthAppointmentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;
@Slf4j
@Service
public class HealthAppointmentService implements IHealthAppointmentService {
    @Autowired
    private IHealthAppointmentRepository appointmentRepository;

    public  void create(HealthAppointment appointment){
        appointmentRepository.save(appointment).subscribe();
    }
    public Mono<HealthAppointment> update(HealthAppointment appointment){
        return appointmentRepository.save(appointment);
    }
    public Mono<Void> deleteById(final String id){
        return appointmentRepository.deleteById(id);
    }

    public Flux<HealthAppointment> findByBusinessidAndDate(
            final String id, final Date date1, final Date date2){
      return appointmentRepository.findBusinessidAndDate(id,date1, date2);
    }

    public Flux<HealthAppointment> findByBusinessidAndDateInc(
            final String id, final Date date1, final Date date2, final  Date timestamp){
        return appointmentRepository.findBusinessidAndDateInc(id,date1, date2, timestamp);
    }
    public Flux<HealthAppointment> findByBusinessFamilyMemberServiceDate(
            final String business, final String family, final String member, final String service,
            final Date date1, final Date date2 ){
        return appointmentRepository.findByBusinessFamilyMemberServiceDate(
                business, family,member,service,date1, date2);
    }


    public Mono<HealthAppointment> findById(final String id){
        return appointmentRepository.findById(id);
    }
    public Flux<HealthAppointment> findByFamilyidOrderByDateDesc(final String id, final Date date1){
        return this.appointmentRepository.findByFamilyidOrderByDateDesc( id, date1);
    }
    public Flux<HealthAppointment> findDayReport(
            final String id, final Date date1, final Date date2){
        return appointmentRepository.findDayReport(id,date1, date2);
    }
    public Flux<HealthAppointment> findMonthReport(
            final String id, final Date date1, final Date date2){
        return appointmentRepository.findMonthReport(id,date1, date2);
    }

    public Flux<HealthAppointment> findDayReportInc(
            final String id, final Date date1, final Date date2, final Date timestamp){
        return appointmentRepository.findDayReportInc(id,date1, date2, timestamp);
    }
    public Flux<HealthAppointment> findMonthReportInc(
            final String id, final Date date1, final Date date2, final Date timestamp){
        return appointmentRepository.findMonthReportInc(id,date1, date2, timestamp);
    }
/*
    public Flux<HealthAppointmentRegionReport> findRegionDayReport(
            final String region, final Date date1, final Date date2){
        return appointmentRepository.findRegionDayReport(region,date1, date2);
    }
    public Flux<HealthAppointmentRegionReport> findRegionMonthReport(
            final String region, final Date date1, final Date date2){
        return appointmentRepository.findRegionMonthReport(region,date1, date2);
    }

    public Flux<HealthAppointmentRegionReport> findRegionDayReportInc(
            final String region, final Date date1, final Date date2, final Date timestamp){
        return appointmentRepository.findRegionDayReportInc(region,date1, date2, timestamp);
    }
    public Flux<HealthAppointmentRegionReport> findRegionMonthReportInc(
            final String region, final Date date1, final Date date2, final Date timestamp){
        return appointmentRepository.findRegionMonthReportInc(region,date1, date2, timestamp);
    }

 */
}