package com.spontzoug.server.controller;

import com.spontzoug.server.model.Billing;
import com.spontzoug.server.model.PlanOption;
import com.spontzoug.server.repository.IBusinessRepository;
import com.spontzoug.server.repository.IPlanOptionRepository;
import com.spontzoug.server.repository.ISysPromotionRepository;
import com.spontzoug.server.service.IBillingService;
import com.spontzoug.server.util.Counter;
import com.spontzoug.server.util.IBillingCounter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.*;

@Slf4j
@RestController
@RequestMapping("/api/billing")
public class BillingController {
    @Autowired
    private IBillingService billingService;
    @Autowired
    private IBusinessRepository businessRepository;
    @Autowired
    private IPlanOptionRepository planOptionRepository;
    @Autowired
    private ISysPromotionRepository sysPromotionRepository;
    // account
    private IBillingCounter assistantActiveCounter = (final String id, final Date date1, final Date date2)->{
        return billingService.countActiveAssistant(id);
    };
    private IBillingCounter cashierActiveCounter = (final String id, final Date date1, final Date date2)->{
        return billingService.countActiveCashier(id );
    };
    private IBillingCounter receptionistActiveCounter = (final String id, final Date date1, final Date date2)->{
        return billingService.countActiveReceptionist(id);
    };
    private IBillingCounter practitionerActiveCounter = (final String id, final Date date1, final Date date2)->{
        return billingService.countActivePractitioner(id);
    };
    private IBillingCounter salesPersonActiveCounter = (final String id, final Date date1, final Date date2)->{
        return billingService.countActiveSalesPerson(id);
    };
    private IBillingCounter realtorActiveCounter = (final String id, final Date date1, final Date date2)->{
        return billingService.countActiveRealtor(id);
    };
    private IBillingCounter financialInsuranceStaffActiveCounter = (final String id, final Date date1, final Date date2)->{
        return billingService.countActiveFinancialInsuranceStaff(id);
    };
    private IBillingCounter personalCareArtistActiveCounter = (final String id, final Date date1, final Date date2)->{
        return billingService.countActivePersonalCareArtist(id);
    };
    private IBillingCounter trainingCoachActiveCounter = (final String id, final Date date1, final Date date2)->{
        return billingService.countActiveTrainingCoach(id);
    };

    // menu
    private IBillingCounter automotiveSaleMenuActiveCounter = (final String id, final Date date1, final Date date2)->{
        return billingService.countActiveAutomotiveSaleMenu(id, date1, date2);
    };
    private IBillingCounter foodMenuActiveCounter = (final String id, final Date date1, final Date date2)->{
        return billingService.countActiveFoodMenu(id, date1, date2);
    };
    private IBillingCounter realEstateMenuActiveCounter = (final String id, final Date date1, final Date date2)->{
        return billingService.countActiveRealEstateMenu(id, date1, date2);
    };
    private IBillingCounter retailerMenuActiveCounter = (final String id, final Date date1, final Date date2)->{
        return billingService.countActiveAutomotiveSaleMenu(id, date1, date2);
    };
    private IBillingCounter residentialMenuActiveCounter = (final String id, final Date date1, final Date date2)->{
        return billingService.countActiveResidentialMenu(id, date1, date2);
    };


    // order
    private IBillingCounter foodOrderCounter = (final String id, final Date date1, final Date date2)->{
        return billingService.countFoodOrder(id, date1, date2);
    };
    private IBillingCounter retailerOrderCounter = (final String id, final Date date1, final Date date2)->{
        return billingService.countRetailerOrder(id, date1, date2);
    };

    // appointment
    private IBillingCounter automotiveSaleAppointmentCounter = (final String id, final Date date1, final Date date2)->{
        return billingService.countAutomotiveSaleAppointment(id, date1, date2);
    };
    private IBillingCounter automotiveServiceAppointmentCounter = (final String id, final Date date1, final Date date2)->{
        return billingService.countAutomotiveSaleAppointment(id, date1, date2);
    };
    private IBillingCounter financialInsuranceAppointmentCounter = (final String id, final Date date1, final Date date2)->{
        return billingService.countFinancialInsuranceAppointment(id, date1, date2);
    };
    private IBillingCounter healthAppointmentCounter = (final String id, final Date date1, final Date date2)->{
        return billingService.countHealthAppointment(id, date1, date2);
    };
    private IBillingCounter realEstateAppointmentCounter = (final String id, final Date date1, final Date date2)->{
        return billingService.countRealEstateAppointment(id, date1, date2);
    };
    private IBillingCounter personalCareAppointmentCounter = (final String id, final Date date1, final Date date2)->{
        return billingService.countPersonalCareAppointment(id, date1, date2);
    };
    private IBillingCounter residentialApointmentCounter = (final String id, final Date date1, final Date date2)->{
        return billingService.countResidentialAppointment(id, date1, date2);
    };

/*
    private IBillingCounter assistantClosingCounter = (final String id, final Date date1, final Date date2)->{
        return billingService.countClosingAssistant(id, date1, date2);
    };
    private IBillingCounter cashierClosingCounter = (final String id, final Date date1, final Date date2)->{
        return billingService.countClosingCashier(id, date1, date2);
    };
    private IBillingCounter receptionistClosingCounter = (final String id, final Date date1, final Date date2)->{
        return billingService.countClosingReceptionist(id, date1, date2);
    };
    private IBillingCounter practitionerClosingCounter = (final String id, final Date date1, final Date date2)->{
        return billingService.countClosingPractitioner(id, date1, date2);
    };
    private IBillingCounter salesPersonClosingCounter = (final String id, final Date date1, final Date date2)->{
        return billingService.countClosingSalesPerson(id, date1, date2);
    };
    private IBillingCounter menuDeletingCounter = (final String id, final Date date1, final Date date2)->{
        return billingService.countDeletingMenu(id, date1, date2);
    };

*/

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody Billing l) {  billingService.create(l); }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Billing> update(@RequestBody Billing l) {
        return billingService.update(l);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Void> deleteById (@PathVariable("id") String id) {
        return billingService.deleteById(id);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Mono<Billing>> findById(@PathVariable("id") String id) {
        Mono<Billing> l = billingService.findById(id);
        HttpStatus s = l!=null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<Mono<Billing>>(l,s);
    }

    @GetMapping("/businessid/{id}")
    public Flux<Billing> findByBusinessId(@PathVariable("id") String id){
        return billingService.findByBusinessid(id);
    }
    @GetMapping("/businessid/{id}/{date}")
    public Flux<Billing> findByBusinessId(
            @PathVariable("id") String id,
            @PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date ){
        return billingService.findByBusinessidInc(id,date);
    }


    @GetMapping("/{region}/{date}")
    public Flux<Billing> findByDate(
            @PathVariable("region") String region,
            @PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date dt){
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        cal.set(Calendar.HOUR_OF_DAY,0);
        cal.set(Calendar.MINUTE,0);
        cal.set(Calendar.SECOND,0);
        Date date1 = cal.getTime();
        Date date2 = new Date( date1.getTime()+ 86400000 );
        log.info(" get bill called:"+ date1 + "  "+ date2);
        return billingService.findByDate(region, date1, date2);
    }

    @PostMapping("/resource/{region}/{date}/{day}")
    public Flux<Counter> countResource(
            @PathVariable("region") String region,
            @PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date dt,
            @PathVariable("day") Integer day){
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        cal.set(Calendar.HOUR_OF_DAY,0);
        cal.set(Calendar.MINUTE,0);
        cal.set(Calendar.SECOND,0);
        cal.set(Calendar.DAY_OF_MONTH,day);
        Date datebase1 = cal.getTime();

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(dt);
        cal2.set(Calendar.HOUR_OF_DAY,23);
        cal2.set(Calendar.MINUTE,59);
        cal2.set(Calendar.SECOND,59);

        int dy = cal2.get(Calendar.DAY_OF_MONTH);
        int mth = cal.get(Calendar.MONTH);
        int yr = cal2.get(Calendar.YEAR);
        if( mth == 11) {
            cal2.set(Calendar.MONTH, 0);
            cal2.set(Calendar.YEAR, yr+1);
        } else {
            cal2.set(Calendar.MONTH, mth+1);
        }
        cal2.set(Calendar.DAY_OF_MONTH, dy-1);
        Date datebase2 = cal2.getTime();

        Calendar cal3 = Calendar.getInstance();
        cal3.setTime(dt);
        cal3.set(Calendar.HOUR_OF_DAY,0);
        cal3.set(Calendar.MINUTE,0);
        cal3.set(Calendar.SECOND,0);
        int dy3 = cal3.get(Calendar.DAY_OF_MONTH);
        int mth3 = cal3.get(Calendar.MONTH);
        int yr3 = cal3.get(Calendar.YEAR);
        if( mth3 == 0) {
            cal3.set(Calendar.MONTH, 11);
            cal3.set(Calendar.YEAR, yr3-1);
        } else {
            cal3.set(Calendar.MONTH, mth3-1);
        }
        Date dateoption1 = cal3.getTime();

        Calendar cal4 = Calendar.getInstance();
        cal4.setTime(dt);
        cal4.set(Calendar.HOUR_OF_DAY,23);
        cal4.set(Calendar.MINUTE,59);
        cal4.set(Calendar.SECOND,59);
        cal4.set(Calendar.DAY_OF_MONTH,day-1);

        Date dateoption2 = cal4.getTime();

log.info("datebase1:"+ datebase1);
log.info("datebase2:"+ datebase2);
log.info("dateoptoin1:"+ dateoption1);
log.info("dateoption2:"+ dateoption2);
        log.info("region:"+ region);
        log.info("day:"+ day.toString());

        Map<String, IBillingCounter> activeTable = new HashMap<>();
        activeTable.put("assistant",assistantActiveCounter);
        activeTable.put("cashier",cashierActiveCounter);
        activeTable.put("receptionist",receptionistActiveCounter);
        activeTable.put("salesperson",salesPersonActiveCounter);
        activeTable.put("practitioner",practitionerActiveCounter);
        activeTable.put("realtor",practitionerActiveCounter);
        activeTable.put("financialinsurancestaff",financialInsuranceStaffActiveCounter);
        activeTable.put("personalcareartist",personalCareArtistActiveCounter);
        activeTable.put("traningcoach",trainingCoachActiveCounter);
        
        activeTable.put("automotivesalemenu",automotiveSaleMenuActiveCounter);
        activeTable.put("foodmenu",foodMenuActiveCounter);
        activeTable.put("realestatemenu",realEstateMenuActiveCounter);
        activeTable.put("residentialmenu",residentialMenuActiveCounter);
        activeTable.put("retailermenu",retailerMenuActiveCounter);

        
        activeTable.put("foodorder",foodOrderCounter);
        activeTable.put("retailerorder",retailerOrderCounter);
        
        activeTable.put("automotivesaleappointment",automotiveSaleAppointmentCounter);
        activeTable.put("automotiveserviceappointment",automotiveServiceAppointmentCounter);
        activeTable.put("financialinsuranceappointment",financialInsuranceAppointmentCounter);
        activeTable.put("healthappointment",healthAppointmentCounter);
        activeTable.put("realestateappointment",realEstateAppointmentCounter);
        activeTable.put("personalcareappointment",personalCareAppointmentCounter);
        activeTable.put("residentialappointment",residentialApointmentCounter);

        Map<String, List<String>> industryTable = new HashMap<>();

        List<String> automotiveSaleTable = new ArrayList<>();
        automotiveSaleTable.add("receptionist");
        automotiveSaleTable.add("salesperson");
        automotiveSaleTable.add("automotivesalemenu");
        automotiveSaleTable.add("automotivesaleappointment");

        List<String> automotiveServiceTable = new ArrayList<>();
        automotiveServiceTable.add("receptionist");
        automotiveServiceTable.add("automativeserviceappointment");

        List<String> financialInsuranceTable = new ArrayList<>();
        financialInsuranceTable.add("receptionist");
        financialInsuranceTable.add("financialinsurancestaff");
        financialInsuranceTable.add("financialinsuranceappointment");

        List<String> foodTable = new ArrayList<>();
        foodTable.add("cashier");
        foodTable.add("foodorder");
        foodTable.add("foodmenu");

        List<String> healthTable = new ArrayList<>();
        healthTable.add("receptionist");
        healthTable.add("practitioner");
        healthTable.add("healthappointment");

        List<String> personalCareTable = new ArrayList<>();
        personalCareTable.add("receptionist");
        personalCareTable.add("personalcareartist");
        personalCareTable.add("personalcareappointment");

        List<String> realEstateTable = new ArrayList<>();
        realEstateTable.add("realtor");
        realEstateTable.add("realestatemenu");
        realEstateTable.add("realestateappointment");

        List<String> residentialTable = new ArrayList<>();
        residentialTable.add("receptionist");
        residentialTable.add("salesperson");
        residentialTable.add("residentialmenu");
        residentialTable.add("residentialappointment");

        List<String> retailerTable = new ArrayList<>();
        retailerTable.add("cashier");
        retailerTable.add("retailerorder");
        retailerTable.add("retailermenu");

        List<String> trainingTable = new ArrayList<>();
        trainingTable.add("assistant");
        trainingTable.add("trainingcoach");

        industryTable.put("automotivesale",automotiveSaleTable);
        industryTable.put("automotiveservice",automotiveServiceTable);
        industryTable.put("financialinsurance",financialInsuranceTable);
        industryTable.put("food",foodTable);
        industryTable.put("health",healthTable);
        industryTable.put("personalcare",personalCareTable);
        industryTable.put("realestate",realEstateTable);
        industryTable.put("residential",residentialTable);
        industryTable.put("retailer",retailerTable);
        industryTable.put("training",trainingTable);

        return businessRepository.findByBillingDay(region,day)
                .flatMap( biz -> {
                    log.info("billing biz:"+ biz.toString());
                    String industry = biz.getIndustry();
                    if (industryTable.containsKey(industry) ) {
                        return Flux.fromArray(industryTable.get(industry).toArray())
                                .flatMap(table -> {
                                    log.info("billing   table:" + table);
                                    return activeTable.get(table).counterTotal(biz.getId(), dateoption1, dateoption2);
                                });
                    } else {
                        return Flux.empty();
                    }
                });
    }

    @PostMapping("/create/{bizid}/{date}/")
    public Flux<Billing> createById(
            @PathVariable("bizid") String bizid,
            @PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date){

        Map<String, PlanOption> plans =
                planOptionRepository.findAll()
                        .collectMap(
                                plan -> { return (String)plan.getIndustry();},
                                plan -> { return plan; }
                        ).block();
        log.info("create billing plans:"+ plans.keySet().toString());
        /*
        businessRepository.findByBillingDay(day)
                .flatMap( biz -> {
                    Billing billing = new Billing(

                    )
                })
        return billingService.findByDate(date1, date2);

         */

        return Flux.empty();
    }
    @GetMapping("/report/day/{region}/{date1}/{date2}")
    public Flux<Billing> findDayReport(
            @PathVariable("region") String region,
            @PathVariable("date1") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date1,
            @PathVariable("date2") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date2){
        return billingService.findDayReport(region,date1, date2);
    }

    @GetMapping("/report/month/{region}/{date1}/{date2}")
    public Flux<Billing> findMonthReport(
            @PathVariable("region") String region,
            @PathVariable("date1") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date1,
            @PathVariable("date2") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date2){
        return billingService.findMonthReport(region,date1, date2);
    }

    @GetMapping("/report/day/{region}/{date1}/{date2}/{timestamp}")
    public Flux<Billing> findDayReportInc(
            @PathVariable("region") String region,
            @PathVariable("date1") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date1,
            @PathVariable("date2") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date2,
            @PathVariable("timestamp") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date timestamp){

        log.info("sys report day region:"+ region);
        log.info("sys report day date1:"+ date1);
        log.info("sys report day date2:"+ date2);
        log.info("sys report day stamp:"+ timestamp);
        return billingService.findDayReportInc(region,date1, date2, timestamp);
    }

    @GetMapping("/report/month/{region}/{date1}/{date2}/{timestamp}")
    public Flux<Billing> findMonthReportInc(
            @PathVariable("region") String region,
            @PathVariable("date1") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date1,
            @PathVariable("date2") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date2,
            @PathVariable("timestamp") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date timestamp){
        return billingService.findMonthReportInc(region,date1, date2, timestamp);
    }

/*
    @GetMapping("/report/day/{region}/{date1}/{date2}")
    public Flux<SysReportBillingResponse> findDayReport(
            @PathVariable("region") String region,
            @PathVariable("date1") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date1,
            @PathVariable("date2") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date2){
        return billingService.findDayReport(region,date1, date2);
    }

    @GetMapping("/report/month/{region}/{date1}/{date2}")
    public Flux<SysReportBillingResponse> findMonthReport(
            @PathVariable("region") String region,
            @PathVariable("date1") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date1,
            @PathVariable("date2") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date2){
        return billingService.findMonthReport(region,date1, date2);
    }

    @GetMapping("/report/day/{region}/{date1}/{date2}/{timestamp}")
    public Flux<SysReportBillingResponse> findDayReportInc(
            @PathVariable("region") String region,
            @PathVariable("date1") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date1,
            @PathVariable("date2") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date2,
            @PathVariable("timestamp") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date timestamp){

        log.info("sys report day region:"+ region);
        log.info("sys report day date1:"+ date1);
        log.info("sys report day date2:"+ date2);
        log.info("sys report day stamp:"+ timestamp);
        return billingService.findDayReportInc(region,date1, date2, timestamp);
    }

    @GetMapping("/report/month/{region}/{date1}/{date2}/{timestamp}")
    public Flux<SysReportBillingResponse> findMonthReportInc(
            @PathVariable("region") String region,
            @PathVariable("date1") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date1,
            @PathVariable("date2") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date2,
            @PathVariable("timestamp") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date timestamp){
        return billingService.findMonthReportInc(region,date1, date2, timestamp);
    }

 */
}
