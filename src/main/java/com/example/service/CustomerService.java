package com.example.service;

import com.example.entity.Customer;
import com.example.repo.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository repo;

    public CustomerService(CustomerRepository repo) {
        this.repo = repo;
    }

    @Transactional(readOnly = true)
    public void printCustomersAndOrders() {
        List<Customer> customers = repo.findAll();   // 1 query

        for (Customer c : customers) {
            System.out.println("Customer: " + c.getName());
            // Each call triggers a new query!
            c.getOrders().forEach(o ->
                System.out.println("  Order: " + o.getProductName()));
        }
    }
}



/*

List<Customer> customers = repo.findAll();   // 1 query for Parent Entity

        for (Customer c : customers) {
            System.out.println("Customer: " + c.getName());
        }

1st Query Hibernate:
                            select
                                c1_0.id,
                                c1_0.email,
                                c1_0.name
                            from
                                customers c1_0


This fetches 3 customers.       N = 3
Customer: Amrit Adhikari
Customer: Maria Lopez
Customer: John Carter




        for (Customer c : customers) {
            System.out.println("Customer: " + c.getName());

            // Each call triggers a new query!
            c.getOrders().forEach(o ->
                System.out.println("  Order: " + o.getProductName()));
        }


2nd Query:
Hibernate:
    select
        c1_0.id,
        c1_0.email,
        c1_0.name
    from
        customers c1_0
Customer: Amrit Adhikari
Hibernate:
    select
        o1_0.customer_id,
        o1_0.id,
        o1_0.price,
        o1_0.product_name,
        o1_0.quantity,
        o1_0.status
    from
        orders o1_0
    where
        o1_0.customer_id=?
  Order: Wireless Mouse
  Order: Mechanical Keyboard
Customer: Maria Lopez
Hibernate:
    select
        o1_0.customer_id,
        o1_0.id,
        o1_0.price,
        o1_0.product_name,
        o1_0.quantity,
        o1_0.status
    from
        orders o1_0
    where
        o1_0.customer_id=?
  Order: USB-C Charger
  Order: Laptop Stand
Customer: John Carter
Hibernate:
    select
        o1_0.customer_id,
        o1_0.id,
        o1_0.price,
        o1_0.product_name,
        o1_0.quantity,
        o1_0.status
    from
        orders o1_0
    where
        o1_0.customer_id=?
  Order: 27-inch Monitor
  Order: Noise Cancelling Headphones















First 1 query to retrieve N parent entities, then

    1 query for each parent  = N query for N parents (N+1)


    so If we have 3 customers → 1 + 3 = 4 queries
       If we had 100 → 101 queries
 */