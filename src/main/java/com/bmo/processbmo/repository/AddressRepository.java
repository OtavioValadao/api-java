package com.bmo.processbmo.repository;

import com.bmo.processbmo.model.Address;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Integer> {
}
