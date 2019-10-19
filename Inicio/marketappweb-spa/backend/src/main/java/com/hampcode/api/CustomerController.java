package com.hampcode.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.hampcode.model.domain.Customer;

import com.hampcode.service.impl.CustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@GetMapping
	public List<Customer> listCustomers() throws Exception {
		return customerService.getAll();
	}

	@GetMapping("/{id}")
	public Customer getCustomer(@PathVariable("id") Long id) throws Exception {
		return customerService.getOne(id).get();
	}
	
	@PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createCustomer(@RequestBody Customer customer) throws Exception {
		customerService.saveOrUpdate(customer);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCustomer(@PathVariable("id") Long id, @RequestBody Customer customer) throws Exception {
        Customer existingCustomer = customerService.getOne(id).get();
        existingCustomer.setFirstName(customer.getFirstName());
        existingCustomer.setLastName(customer.getLastName());
        existingCustomer.setDni(customer.getDni());
        customerService.saveOrUpdate(existingCustomer);
    }
    
    
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(@PathVariable("id") Long id) throws Exception {
    	customerService.deleteById(id);
    }
	
}
