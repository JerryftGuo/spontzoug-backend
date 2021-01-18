package com.spontzoug.server.service;

import com.spontzoug.server.model.Assistant;
import com.spontzoug.server.model.Business;
import com.spontzoug.server.model.Customer;
import com.spontzoug.server.repository.ICustomerRepository;
import com.spontzoug.server.util.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@Service
public class CustomerService implements ICustomerService {
    @Autowired
    private ICustomerRepository customerRepository;

    public void create(Customer customer) {
        customerRepository.save(customer).subscribe();
    }

    public Mono<Customer> update(Customer customer){
        return customerRepository.save(customer);
    }

    public Mono<Void> deleteById(final String id){
        return customerRepository.deleteById(id);
    }

    public Mono<Customer> findById(final String id){
        return customerRepository.findById(id);
    }
    public Mono<Customer> findByCustomername(final String customername) {
        return customerRepository.findByCustomername(customername);
    }

    public Flux<Customer> findAllInc(final Date date) {
        return customerRepository.findAllInc(date);
    }
    public Flux<Customer> findAll(){
        return customerRepository.findAll();
    }

}