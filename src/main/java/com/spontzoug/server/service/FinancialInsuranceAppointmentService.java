package com.spontzoug.server.service;

import com.spontzoug.server.model.FinancialInsuranceAppointment;
import com.spontzoug.server.repository.IFinancialInsuranceAppointmentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;
@Slf4j
@Service
public class FinancialInsuranceAppointmentService implements IFinancialInsuranceAppointmentService {
    @Autowired
    private IFinancialInsuranceAppointmentRepository appointmentRepository;

    public  void create(FinancialInsuranceAppointment appointment){
        appointmentRepository.save(appointment).subscribe();
    }
    public Mono<FinancialInsuranceAppointment> update(FinancialInsuranceAppointment appointment){
        return appointmentRepository.save(appointment);
    }
    public Mono<Void> deleteById(final String id){
        return appointmentRepository.deleteById(id);
    }

    public Flux<FinancialInsuranceAppointment> findByBusinessidAndDate(
            final String id, final Date date1, final Date date2){
        return appointmentRepository.findBusinessidAndDate(id,date1, date2);
    }

    public Flux<FinancialInsuranceAppointment> findByBusinessidAndDateInc(
            final String id, final Date date1, final Date date2, final  Date timestamp){
        return appointmentRepository.findBusinessidAndDateInc(id,date1, date2, timestamp);
    }

    public Mono<FinancialInsuranceAppointment> findById(final String id){
        return appointmentRepository.findById(id);
    }

    public Flux<FinancialInsuranceAppointment> findDayReport(
            final String id, final Date date1, final Date date2){
        return appointmentRepository.findDayReport(id,date1, date2);
    }
    public Flux<FinancialInsuranceAppointment> findMonthReport(
            final String id, final Date date1, final Date date2){
        return appointmentRepository.findMonthReport(id,date1, date2);
    }

    public Flux<FinancialInsuranceAppointment> findDayReportInc(
            final String id, final Date date1, final Date date2, final Date timestamp){
        return appointmentRepository.findDayReportInc(id,date1, date2, timestamp);
    }
    public Flux<FinancialInsuranceAppointment> findMonthReportInc(
            final String id, final Date date1, final Date date2, final Date timestamp){
        return appointmentRepository.findMonthReportInc(id,date1, date2, timestamp);
    }

}