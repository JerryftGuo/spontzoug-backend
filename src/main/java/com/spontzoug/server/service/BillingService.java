package com.spontzoug.server.service;

import com.spontzoug.server.http.SysReportBillingResponse;
import com.spontzoug.server.model.Billing;
import com.spontzoug.server.repository.*;
import com.spontzoug.server.util.Counter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;


@Service
public  class BillingService implements IBillingService {
    @Autowired
    private IBillingRepository billingRepository;
    @Autowired
    private IAssistantRepository assistantRepository;
    @Autowired
    private ICashierRepository cashierRepository;
    @Autowired
    private IReceptionistRepository receptionistRepository;
    @Autowired
    private ISalesPersonRepository salespersonRepository;
    @Autowired
    private IPractitionerRepository practitionerRepository;
    @Autowired
    private IPersonalCareArtistRepository personalCareArtistRepository;
    @Autowired
    private ITrainingCoachRepository trainingCoachRepository;
    @Autowired
    private IFinancialInsuranceStaffRepository financialInsuranceStaffRepository;
    @Autowired
    private IRealtorRepository realtorRepository;

    @Autowired
    private IAutomotiveSaleMenuRepository automotiveSaleMenuRepository;
    @Autowired
    private IFoodMenuRepository foodMenuRepository;
    @Autowired
    private IRealEstateMenuRepository realEstateMenuRepository;
    @Autowired
    private IResidentialMenuRepository residentialMenuRepository;
    @Autowired
    private IRetailerMenuRepository retailerMenuRepository;

    @Autowired
    private IAutomotiveSaleAppointmentRepository automotiveSaleAppointmentRepository;
    @Autowired
    private  IAutomotiveServiceAppointmentRepository automotiveServiceAppointmentRepository;
    @Autowired
    private IFinancialInsuranceAppointmentRepository financialInsuranceAppointmentRepository;
    @Autowired
    private IHealthAppointmentRepository healthAppointmentRepository;
    @Autowired
    private IPersonalCareAppointmentRepository personalCareAppointmentRepository;
    @Autowired
    private IRealEstateAppointmentRepository realEstateAppointmentRepository;
    @Autowired
    private IResidentialAppointmentRepository residentialAppointmentRepository;


    @Autowired
    private IFoodOrderRepository foodOrderRepository;
    @Autowired
    private IRetailerOrderRepository retailerOrderRepository;

    public void create(Billing billing) {
        billingRepository.save(billing).subscribe();
    }
    public Mono<Billing> update(Billing billing) {
        return billingRepository.save(billing);
    }

    public Mono<Void> deleteById(final String id) {
        return billingRepository.deleteById(id);
    }

    public Mono<Billing> findById(final String id) {
        return billingRepository.findById(id);
    }

    public Flux<Billing> findByBusinessid(final String id) {
        return billingRepository.findByBusinessid(id);
    }
    public Flux<Billing> findByBusinessidInc(final String id, final Date date) {
        return billingRepository.findByBusinessidInc(id, date);
    }
    public Flux<Billing> findByDate(final String region,final Date date1, final Date date2) {
        return billingRepository.findByDate(region,date1, date2);
    }
    public Mono<Billing> findBySerial(final String ser) {
        return billingRepository.findBySerial(ser);
    }


    public Mono<Counter> countActiveAssistant(final String id ) {
        return assistantRepository.countActive(id)
                .filter( c -> c != 0)
                .map(count -> { return new Counter(id,"account","active","assistant",count); });
    }
    public Mono<Counter> countActiveCashier(final String id) {
        return cashierRepository.countActive(id)
                .filter( c -> c != 0)
                .map(count -> { return new Counter(id,"account","active","cashier",count); });
    }
    public Mono<Counter> countActiveReceptionist(final String id) {
        return receptionistRepository.countActive(id)
                .filter( c -> c != 0)
                .map(count -> { return new Counter(id,"account","active","receptionist",count); });
    }
    public Mono<Counter> countActivePractitioner(final String id) {
        return practitionerRepository.countActive(id)
                .filter( c -> c != 0)
                .map(count -> { return new Counter(id,"account","active","practitioner",count); });
    }
    public Mono<Counter> countActiveSalesPerson(final String id) {
        return salespersonRepository.countActive(id)
                .filter( c -> c != 0)
                .map(count -> { return new Counter(id,"account","active","salesperson",count); });
    }
    public Mono<Counter> countActiveRealtor(final String id) {
        return realtorRepository.countActive(id)
                .filter( c -> c != 0)
                .map(count -> { return new Counter(id,"account","active","realtor",count); });
    }
    public Mono<Counter> countActiveFinancialInsuranceStaff(final String id ) {
        return financialInsuranceStaffRepository.countActive(id)
                .filter( c -> c != 0)
                .map(count -> { return new Counter(id,"account","active","financialinsurancestaff",count); });
    }
    public Mono<Counter> countActivePersonalCareArtist(final String id) {
        return personalCareArtistRepository.countActive(id)
                .filter( c -> c != 0)
                .map(count -> { return new Counter(id,"account","active","personalcareartist",count); });
    }
    public Mono<Counter> countActiveTrainingCoach(final String id) {
        return trainingCoachRepository.countActive(id)
                .filter( c -> c != 0)
                .map(count -> { return new Counter(id,"account","active","tainingcoach",count); });
    }



    public Mono<Counter> countActiveAutomotiveSaleMenu(final String id, final Date date1, final Date date2) {
        return automotiveSaleMenuRepository.countActive(id)
                .filter( c -> c != 0)
                .map(count -> { return new Counter(id,"item","active","automotivesalemenu",count); });
    }
    public Mono<Counter> countActiveFoodMenu(final String id, final Date date1, final Date date2) {
        return foodMenuRepository.countActive(id)
                .filter( c -> c != 0)
                .map(count -> { return new Counter(id,"item","active","foodmenu",count); });
    }
    public Mono<Counter> countActiveRealEstateMenu(final String id, final Date date1, final Date date2) {
        return realEstateMenuRepository.countActive(id)
                .filter( c -> c != 0)
                .map(count -> { return new Counter(id,"item","active","realesetatemenu",count); });
    }
    public Mono<Counter> countActiveResidentialMenu(final String id, final Date date1, final Date date2) {
        return residentialMenuRepository.countActive(id)
                .filter( c -> c != 0)
                .map(count -> { return new Counter(id,"item","active","residentialmenu",count); });
    }
    public Mono<Counter> countActiveRetailerMenu(final String id, final Date date1, final Date date2) {
        return retailerMenuRepository.countActive(id)
                .filter( c -> c != 0)
                .map(count -> { return new Counter(id,"item","active","retailermenu",count); });
    }


    public Mono<Counter> countAutomotiveSaleAppointment(final String id, final Date date1, final Date date2) {
        return automotiveSaleAppointmentRepository.countAutomotiveSaleAppointment(id,date1, date2)
                .filter( c -> c != 0)
                .map(count -> { return new Counter(id,"process","active","automotivesaleappointment",count); });
    }
    public Mono<Counter> countAutomotiveServiceAppointment(final String id, final Date date1, final Date date2) {
        return automotiveServiceAppointmentRepository.countAutomotiveServiceAppointment(id,date1, date2)
                .filter( c -> c != 0)
                .map(count -> { return new Counter(id,"process","active","automotiveserviceappointment",count); });
    }
    public Mono<Counter> countFinancialInsuranceAppointment(final String id, final Date date1, final Date date2) {
        return financialInsuranceAppointmentRepository.countFinancialInsuranceAppointment(id,date1, date2)
                .filter( c -> c != 0)
                .map(count -> { return new Counter(id,"process","active","finacialinsuranceappointment",count); });
    }
    public Mono<Counter> countHealthAppointment(final String id, final Date date1, final Date date2) {
        return healthAppointmentRepository.countHealthAppointment(id,date1, date2)
                .filter( c -> c != 0)
                .map(count -> { return new Counter(id,"process","active","healthappointment",count); });
    }
    public Mono<Counter> countPersonalCareAppointment(final String id, final Date date1, final Date date2) {
        return personalCareAppointmentRepository.countPersonalCareAppointment(id,date1, date2)
                .filter( c -> c != 0)
                .map(count -> { return new Counter(id,"process","active","personalcareappointment",count); });
    }
    public Mono<Counter> countRealEstateAppointment(final String id, final Date date1, final Date date2) {
        return realEstateAppointmentRepository.countRealEstateAppointment(id,date1, date2)
                .filter( c -> c != 0)
                .map(count -> { return new Counter(id,"process","active","realestateappointment",count); });
    }
    public Mono<Counter> countResidentialAppointment(final String id, final Date date1, final Date date2) {
        return residentialAppointmentRepository.countResidentialAppointment(id,date1, date2)
                .filter( c -> c != 0)
                .map(count -> { return new Counter(id,"process","active","residentialappointment",count); });
    }

    public Mono<Counter> countFoodOrder(final String id, final Date date1, final Date date2) {
        return foodOrderRepository.countFoodOrder(id,date1, date2).map(
                count -> { return new Counter(id,"process","active","foodorder",count); })
                .defaultIfEmpty( new Counter(id,"process","active","foodorder",0L) );
    }
    public Mono<Counter> countRetailerOrder(final String id, final Date date1, final Date date2) {
        return retailerOrderRepository.countRetailerOrder(id,date1, date2).map(
                count -> { return new Counter(id,"process","active","retailerorder",count); })
                .defaultIfEmpty( new Counter(id,"process","active","retailerorder",0L) );
    }

/*

    public Mono<Counter> countClosingAssistant(final String id, final Date date1, final Date date2) {
        return assistantRepository.countClosingAssistant(id,date1, date2).map(
                count -> { return new Counter(id,"account","closing","assistant",count); })
                .defaultIfEmpty( new Counter(id,"account","closing","assistant",0L) );
    }
    public Mono<Counter> countClosingCashier(final String id, final Date date1, final Date date2) {
        return cashierRepository.countClosingCashier(id,date1, date2).map(
                count -> { return new Counter(id,"account","closing","cashier",count); })
                .defaultIfEmpty( new Counter(id,"account","closing","cashier",0L) );
    }
    public Mono<Counter> countClosingReceptionist(final String id, final Date date1, final Date date2) {
        return receptionistRepository.countClosingReceptionist(id,date1, date2).map(
                count -> { return new Counter(id,"account","closing","receptionist",count); })
                .defaultIfEmpty( new Counter(id,"account","closing","receptionist",0L) );
    }
    public Mono<Counter> countClosingPractitioner(final String id, final Date date1, final Date date2) {
        return practitionerRepository.countClosingPractitioner(id,date1, date2).map(
                count -> { return new Counter(id,"account","closing","practitioner",count); })
                .defaultIfEmpty( new Counter(id,"account","closing","practitioner",0L) );
    }
    public Mono<Counter> countClosingSalesPerson(final String id, final Date date1, final Date date2) {
        return salespersonRepository.countClosingSalesPerson(id,date1, date2).map(
                count -> { return new Counter(id,"account","closing","sale",count); })
                .defaultIfEmpty( new Counter(id,"account","closing","sale",0L) );
    }
    public Mono<Counter> countDeletingMenu(final String id, final Date date1, final Date date2) {
        return menuRepository.countDeletingMenu(id,date1, date2).map(
                count -> { return new Counter(id,"item","deleting","menu",count); })
                .defaultIfEmpty( new Counter(id,"item","deleting","menu",0L) );
    }

 */

    public Flux<Billing> findDayReport(
            final String region, final Date date1, final Date date2){
        return billingRepository.findDayReport(region,date1, date2);
    }
    public Flux<Billing> findMonthReport(
            final String region, final Date date1, final Date date2){
        return billingRepository.findMonthReport(region,date1, date2);
    }


    public Flux<Billing> findDayReportInc(
            final String region, final Date date1, final Date date2, final Date timestamp){
        return billingRepository.findDayReportInc(region,date1, date2, timestamp);
    }
    public Flux<Billing> findMonthReportInc(
            final String region, final Date date1, final Date date2, final Date timestamp){
        return billingRepository.findMonthReportInc(region,date1, date2, timestamp);
    }

/*
    public Flux<SysReportBillingResponse> findDayReport(
            final String region, final Date date1, final Date date2){
        return billingRepository.findDayReport(region,date1, date2);
    }
    public Flux<SysReportBillingResponse> findMonthReport(
            final String region, final Date date1, final Date date2){
        return billingRepository.findMonthReport(region,date1, date2);
    }


    public Flux<SysReportBillingResponse> findDayReportInc(
            final String region, final Date date1, final Date date2, final Date timestamp){
        return billingRepository.findDayReportInc(region,date1, date2, timestamp);
    }
    public Flux<SysReportBillingResponse> findMonthReportInc(
            final String region, final Date date1, final Date date2, final Date timestamp){
        return billingRepository.findMonthReportInc(region,date1, date2, timestamp);
    }

 */
}
