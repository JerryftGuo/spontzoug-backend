package com.spontzoug.server.service;

import com.spontzoug.server.model.Invoice;
import com.spontzoug.server.repository.IInvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@Service
public class InvoiceService implements IInvoiceService {
    @Autowired
    private IInvoiceRepository invoiceRepository;

    public void create(Invoice invoice) {
        invoiceRepository.save(invoice).subscribe();
    }

    public Mono<Invoice> update(Invoice invoice){
        return invoiceRepository.save(invoice);
    }

    public Mono<Void> deleteById(final String id){
        return invoiceRepository.deleteById(id);
    }

    public Mono<Invoice> findById(final String id){
        return invoiceRepository.findById(id);
    }
    public Flux<Invoice> findByBusinessidAndDate(final String id, final Date date1, final Date date2) {
        return invoiceRepository.findByBusinessidAndDate(id, date1, date2);
    }
    public Flux<Invoice> findByClientidAndDate(final String id, final Date date1, final Date date2) {
        return invoiceRepository.findByClientidAndDate(id, date1, date2);
    }
    public Mono<Invoice> findByAppointmentid(final String id) {
        return invoiceRepository.findByAppointmentid(id);
    }
}