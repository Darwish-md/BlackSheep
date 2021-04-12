package com.blacksheep.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blacksheep.domain.Customer;

@Repository
public interface ProfileRepository extends JpaRepository<Customer, Long> {
	Optional<Customer> findById(Long id);
}
