package com.hampcode.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.hampcode.model.domain.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}