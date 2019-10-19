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


import com.hampcode.model.domain.Employee;
import com.hampcode.model.repository.EmployeeRepository;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

	@Autowired
	private EmployeeRepository employeeRepository;

	@GetMapping
	public List<Employee> listEmployee() {
		return employeeRepository.findAll();
	}

	@GetMapping("/{id}")
	public Employee getEmployee(@PathVariable("id") Long id) {
		return employeeRepository.findById(id).orElse(new Employee());
	}
	
	@PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createEmployee(@RequestBody Employee employee) {
		employeeRepository.save(new Employee(employee.getName(), employee.getEmail()));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateEmployee(@PathVariable("id") Long id, @RequestBody Employee employee) {
        Employee existingEmployee = employeeRepository.findById(id).orElse(new Employee());
        existingEmployee.setName(employee.getName());
        existingEmployee.setEmail(employee.getEmail());
        employeeRepository.save(existingEmployee);
    }
    
    
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployee(@PathVariable("id") Long id) {
        employeeRepository.deleteById(id);
    }
	
}
