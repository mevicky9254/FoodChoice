package com.foodchoice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.foodchoice.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    public Customer findByUsername(String username);
    
}