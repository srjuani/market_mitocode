package com.hampcode.api;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.hampcode.exception.ModeloNotFoundException;
import com.hampcode.model.domain.Invoice;
import com.hampcode.service.IInvoiceService;

@RestController
@RequestMapping("/invoices")
public class InvoiceController {

	@Autowired
	private IInvoiceService invoiceService;

	@GetMapping
	public ResponseEntity<List<Invoice>> getAllInvoice() {
		List<Invoice> invoices = new ArrayList<>();
		try {
			invoices = invoiceService.getAll();
		} catch (Exception e) {
			throw new ModeloNotFoundException(" " + e.getMessage());
		}
		return new ResponseEntity<List<Invoice>>(invoices, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Invoice> saveInvoice(@Valid @RequestBody Invoice invoice) {
		Invoice invoiceNew = new Invoice();
		URI location;
		try {
			invoiceNew = invoiceService.saveOrUpdate(invoice);
			location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(invoiceNew.getId())
					.toUri();

		} catch (Exception e) {
			throw new ModeloNotFoundException(" " + e.getMessage());
		}
		return ResponseEntity.created(location).build();
	}

}
