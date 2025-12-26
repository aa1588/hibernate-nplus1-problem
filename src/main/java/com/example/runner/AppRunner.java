package com.example.runner;

import com.example.service.CustomerService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AppRunner implements CommandLineRunner {

    private final CustomerService customerService;

    public AppRunner(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Override
    public void run(String... args) throws Exception {
        customerService.printCustomersAndOrders();
    }
}
