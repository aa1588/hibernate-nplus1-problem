# spring-jpa-nplus1-demo

A small Spring Boot project to demonstrate the **N+1 query problem** in Spring Data JPA and how to fix it using **@EntityGraph**.

## What is the N+1 problem?

In JPA, when you load a list of parent entities (like `Customer`) and then access a lazy collection (like `orders`) inside a loop, Hibernate may execute:

- 1 query to load all customers  
- N additional queries to load orders for each customer  

This results in **1 + N queries**, which can seriously hurt performance as your data grows.

## Example

```java
List<Customer> customers = customerRepository.findAll();

for (Customer c : customers) {
    c.getOrders().forEach(order -> {
        System.out.println(order.getProductName());
    });
}
```

## Solution using @EntityGraph

To fix this, we tell JPA to fetch the required relationship in the same query:
```java
@EntityGraph(attributePaths = "orders")
List<Customer> findAll();
```

With this, customers and their orders are loaded together, avoiding the extra queries and eliminating the N+1 problem.
