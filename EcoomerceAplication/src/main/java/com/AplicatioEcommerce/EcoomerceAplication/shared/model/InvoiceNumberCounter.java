package com.AplicatioEcommerce.EcoomerceAplication.shared.model;

import jakarta.persistence.*;

@Entity
@Table(name = "invoice_number_counter",
       uniqueConstraints = @UniqueConstraint(columnNames = {"series", "year", "customer_id"}))
public class InvoiceNumberCounter {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "inv_counter_generator")
    @SequenceGenerator(name = "inv_counter_generator", sequenceName = "inv_counter_seq", allocationSize = 1)
    private Long id;

    @Column(nullable = false, length = 5)
    private String series;

    @Column(nullable = false)
    private int year;

    @Column(nullable = false)
    private int lastNumber = 0;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    public InvoiceNumberCounter() {}

    public Long getId() { return id; }

    public String getSeries() { return series; }
    public void setSeries(String series) { this.series = series; }

    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }

    public int getLastNumber() { return lastNumber; }
    public void setLastNumber(int lastNumber) { this.lastNumber = lastNumber; }

    public Customer getCustomer() { return customer; }
    public void setCustomer(Customer customer) { this.customer = customer; }
}
