package com.hampcode.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.hampcode.model.domain.Invoice;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

}
