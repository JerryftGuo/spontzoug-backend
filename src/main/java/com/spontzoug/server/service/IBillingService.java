package com.spontzoug.server.service;

import com.spontzoug.server.http.SysReportBillingResponse;
import com.spontzoug.server.model.Billing;
import com.spontzoug.server.util.Counter;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

public interface IBillingService {
    void create(Billing b);
    Mono<Billing> update(Billing business);
    Mono<Void> deleteById(final String id);
    Mono<Billing> findById(final String id);
    Flux<Billing> findByBusinessid(final String id);
    Flux<Billing> findByBusinessidInc(final String id, final Date date);
    Flux<Billing> findByDate(final String region,final Date date1, final Date date2);
    Mono<Billing> findBySerial(final String serial);

    Mono<Counter> countFoodOrder(final String businessid, final Date date1, final Date date2);
    Mono<Counter> countRetailerOrder(final String businessid, final Date date1, final Date date2);

    Mono<Counter> countAutomotiveSaleAppointment(final String businessid, final Date date1, final Date date2);
    Mono<Counter> countAutomotiveServiceAppointment(final String businessid, final Date date1, final Date date2);
    Mono<Counter> countFinancialInsuranceAppointment(final String businessid, final Date date1, final Date date2);
    Mono<Counter> countHealthAppointment(final String businessid, final Date date1, final Date date2);
    Mono<Counter> countPersonalCareAppointment(final String businessid, final Date date1, final Date date2);
    Mono<Counter> countResidentialAppointment(final String businessid, final Date date1, final Date date2);
    Mono<Counter> countRealEstateAppointment(final String businessid, final Date date1, final Date date2);

    Mono<Counter> countActiveAssistant(final String businessid );
    Mono<Counter> countActiveCashier(final String businessid );
    Mono<Counter> countActiveReceptionist(final String businessid);
    Mono<Counter> countActivePractitioner(final String businessid);
    Mono<Counter> countActiveSalesPerson(final String businessid);
    Mono<Counter> countActiveRealtor(final String businessid );
    Mono<Counter> countActiveFinancialInsuranceStaff(final String businessid);
    Mono<Counter> countActivePersonalCareArtist(final String businessid);
    Mono<Counter> countActiveTrainingCoach(final String businessid );

    Mono<Counter> countActiveAutomotiveSaleMenu(final String businessid, final Date date1, final Date date2);
    Mono<Counter> countActiveFoodMenu(final String businessid, final Date date1, final Date date2);
    Mono<Counter> countActiveRealEstateMenu(final String businessid, final Date date1, final Date date2);
    Mono<Counter> countActiveResidentialMenu(final String businessid, final Date date1, final Date date2);
    Mono<Counter> countActiveRetailerMenu(final String businessid, final Date date1, final Date date2);
/*
    Mono<Counter> countClosingAssistant(final String businessid, final Date date1, final Date date2);
    Mono<Counter> countClosingCashier(final String businessid, final Date date1, final Date date2);
    Mono<Counter> countClosingReceptionist(final String businessid, final Date date1, final Date date2);
    Mono<Counter> countClosingPractitioner(final String businessid, final Date date1, final Date date2);
    Mono<Counter> countClosingRealtor(final String businessid, final Date date1, final Date date2);
    Mono<Counter> countClosingSalesPerson(final String businessid, final Date date1, final Date date2);
    Mono<Counter> countClosingFinancialInsuranceStaff(final String businessid, final Date date1, final Date date2);
    Mono<Counter> countClosingPersonaCareArtist(final String businessid, final Date date1, final Date date2);
    Mono<Counter> countClosingTrainingCoach(final String businessid, final Date date1, final Date date2);

    Mono<Counter> countDeletingAutomotiveSaleMenu(final String businessid, final Date date1, final Date date2);
    Mono<Counter> countDeletingFoodMenu(final String businessid, final Date date1, final Date date2);
    Mono<Counter> countDeletingRealEstateMenu(final String businessid, final Date date1, final Date date2);
    Mono<Counter> countDeletingResidentialMenu(final String businessid, final Date date1, final Date date2);
    Mono<Counter> countDeletingRetailerMenu(final String businessid, final Date date1, final Date date2);
*/

    Flux<Billing> findDayReport(
            final String region, final Date date1, final Date date2);
    Flux<Billing> findMonthReport(
            final String region, final Date date1, final Date date2);
    Flux<Billing> findDayReportInc(
            final String region, final Date date1, final Date date2, final Date timestamp);
    Flux<Billing> findMonthReportInc(
            final String region, final Date date1, final Date date2, final Date timestamp);
    /*
    Flux<SysReportBillingResponse> findDayReport(
            final String region, final Date date1, final Date date2);
    Flux<SysReportBillingResponse> findMonthReport(
            final String region, final Date date1, final Date date2);
    Flux<SysReportBillingResponse> findDayReportInc(
            final String region, final Date date1, final Date date2, final Date timestamp);
    Flux<SysReportBillingResponse> findMonthReportInc(
            final String region, final Date date1, final Date date2, final Date timestamp);

     */

}