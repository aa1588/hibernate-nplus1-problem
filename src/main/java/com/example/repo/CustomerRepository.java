package com.example.repo;

import com.example.entity.Customer;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    /*
    Solution: It’s the list of fields (associations) you want fetched eagerly in a single query:

    @EntityGraph(attributePaths = "orders")
    List<Customer> findAll();

     */
}
