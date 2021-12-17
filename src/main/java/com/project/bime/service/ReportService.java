package com.project.bime.service;

import com.project.bime.model.Bime;
import com.project.bime.model.BimeType;
import com.project.bime.model.Customer;
import com.project.bime.model.Ghest;
import com.project.bime.payload.ReportResponse;
import com.project.bime.repository.BimeRepository;
import com.project.bime.repository.CustomerRepository;
import com.project.bime.repository.GhestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class ReportService {
    private CustomerRepository customerRepository;
    private BimeRepository bimeRepository;
    private GhestRepository ghestRepository;

    @Autowired
    public ReportService(CustomerRepository customerRepository, BimeRepository bimeRepository, GhestRepository ghestRepository){
        this.customerRepository = customerRepository;
        this.bimeRepository = bimeRepository;
        this.ghestRepository = ghestRepository;
    }

    public ReportResponse getReport() {
        ReportResponse report = new ReportResponse();
        List<Customer> customerList = customerRepository.findAll();
        List<Bime> bimeList = bimeRepository.findAll();
        List<Ghest> ghestList = ghestRepository.findAll();

        report.setCustomers(customerList.size());
        report.setBime(bimeList.size());

        AtomicInteger bimeSales = new AtomicInteger();
        AtomicInteger bimeBadane = new AtomicInteger();
        AtomicInteger both = new AtomicInteger();
        for (int i=0; i < customerList.size(); i++){
            Customer customer = customerList.get(i);
            AtomicReference<Boolean> sales = new AtomicReference<>(false);
            AtomicReference<Boolean> badane = new AtomicReference<>(false);
            customer.getBimeList().stream().forEach(b -> {
                if(b.getType() == BimeType.BIME_BADANE) {
                    bimeBadane.getAndIncrement();
                    badane.set(true);
                }
                if(b.getType() == BimeType.BIME_SALES){
                    bimeSales.getAndIncrement();
                    sales.set(true);
                }
            });
            if(sales.get() && badane.get()) {
                both.getAndIncrement();
            }
        }
        report.setBimeSales(bimeSales.get());
        report.setBimeBadane(bimeBadane.get());
        report.setBoth(both.get());

        return report;
    }
}
