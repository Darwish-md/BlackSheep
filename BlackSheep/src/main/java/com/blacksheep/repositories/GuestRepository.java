package com.blacksheep.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blacksheep.domain.Guest;

@Repository
public interface GuestRepository extends JpaRepository<Guest, Long> {

}
