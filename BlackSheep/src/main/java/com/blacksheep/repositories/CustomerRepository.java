package com.blacksheep.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blacksheep.domain.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

	Customer findByEmail(String email);

}
