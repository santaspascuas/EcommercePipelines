package com.AplicatioEcommerce.EcoomerceAplication.module.billing.service;

import java.time.LocalDate;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.AplicatioEcommerce.EcoomerceAplication.shared.model.Customer;
import com.AplicatioEcommerce.EcoomerceAplication.shared.model.InvoiceNumberCounter;
import com.AplicatioEcommerce.EcoomerceAplication.module.billing.repository.InvoiceNumberCounterDao;

@Service
public class InvoiceNumberGenerator {

    private final InvoiceNumberCounterDao counterDao;

    public InvoiceNumberGenerator(InvoiceNumberCounterDao counterDao) {
        this.counterDao = counterDao;
    }

    // Formato: F-2025-0001
    @Transactional
    public String next(Customer customer, String series) {
        int year = LocalDate.now().getYear();

        InvoiceNumberCounter counter = counterDao
                .findForUpdate(customer.getId(), series, year)
                .orElseGet(() -> {
                    InvoiceNumberCounter c = new InvoiceNumberCounter();
                    c.setSeries(series);
                    c.setYear(year);
                    c.setCustomer(customer);
                    return counterDao.save(c);
                });

        counter.setLastNumber(counter.getLastNumber() + 1);
        counterDao.save(counter);

        return String.format("%s-%d-%04d", series, year, counter.getLastNumber());
    }
}
