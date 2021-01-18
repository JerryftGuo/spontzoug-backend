package com.spontzoug.server.service;

import com.spontzoug.server.model.ServiceAppointment;
import com.spontzoug.server.repository.IServiceAppointmentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;
@Slf4j
@Service
public class ServiceAppointmentService implements IServiceAppointmentService {
    @Autowired
    private IServiceAppointmentRepository appointmentRepository;

    public  void create(ServiceAppointment appointment){
        appointmentRepository.save(appointment).subscribe();
    }
    public Mono<ServiceAppointment> update(ServiceAppointment appointment){
        return appointmentRepository.save(appointment);
    }
    public Mono<Void> deleteById(final String id){
        return appointmentRepository.deleteById(id);
    }

    public Flux<ServiceAppointment> findByBusinessidAndDate(
            final String id, final Date date1, final Date date2){
        return appointmentRepository.findBusinessidAndDate(id,date1, date2);
    }

    public Flux<ServiceAppointment> findByBusinessidAndDateInc(
            final String id, final Date date1, final Date date2, final  Date timestamp){
        return appointmentRepository.findBusinessidAndDateInc(id,date1, date2, timestamp);
    }

    public Mono<ServiceAppointment> findById(final String id){
        return appointmentRepository.findById(id);
    }

    public Flux<ServiceAppointment> findDayReport(
            final String id, final Date date1, final Date date2){
        return appointmentRepository.findDayReport(id,date1, date2);
    }
    public Flux<ServiceAppointment> findMonthReport(
            final String id, final Date date1, final Date date2){
        return appointmentRepository.findMonthReport(id,date1, date2);
    }

    public Flux<ServiceAppointment> findDayReportInc(
            final String id, final Date date1, final Date date2, final Date timestamp){
        return appointmentRepository.findDayReportInc(id,date1, date2, timestamp);
    }
    public Flux<ServiceAppointment> findMonthReportInc(
            final String id, final Date date1, final Date date2, final Date timestamp){
        return appointmentRepository.findMonthReportInc(id,date1, date2, timestamp);
    }
/*
    public Flux<ServiceAppointmentRegionReport> findRegionDayReport(
            final String region, final Date date1, final Date date2){
        return appointmentRepository.findRegionDayReport(region,date1, date2);
    }
    public Flux<ServiceAppointmentRegionReport> findRegionMonthReport(
            final String region, final Date date1, final Date date2){
        return appointmentRepository.findRegionMonthReport(region,date1, date2);
    }

    public Flux<ServiceAppointmentRegionReport> findRegionDayReportInc(
            final String region, final Date date1, final Date date2, final Date timestamp){
        return appointmentRepository.findRegionDayReportInc(region,date1, date2, timestamp);
    }
    public Flux<ServiceAppointmentRegionReport> findRegionMonthReportInc(
            final String region, final Date date1, final Date date2, final Date timestamp){
        return appointmentRepository.findRegionMonthReportInc(region,date1, date2, timestamp);
    }

 */
}