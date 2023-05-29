package com.vietcombank.training.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.vietcombank.training.exception.ResourceNotFoundException;
import com.vietcombank.training.model.Employee;
import com.vietcombank.training.respository.EmployeeRespository;
import com.vietcombank.training.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeRespository employeeRespository;

	public EmployeeServiceImpl(EmployeeRespository employeeRespository) {
		super();
		this.employeeRespository = employeeRespository;
	}

	@Override
	public Employee saveEmployee(Employee employee) {
		return employeeRespository.save(employee);
	}

	@Override
	public List<Employee> getAllEmployee() {
		return employeeRespository.findAll();
	}

	@Override
	public Employee getEmployeeById(long id) {
		/*
		 * Optional<Employee> employee = employeeRespository.findById(id); if
		 * (employee.isPresent()) { return employee.get(); }else { throw new
		 * ResourceNotFoundException("Employee", "Id", id); }
		 */
		
		return employeeRespository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Employee", "Id", id));
	}

	@Override
	public Employee updateEmployee(Employee employee, long id) {
		Employee exisEmployee = getEmployeeById(id);
		exisEmployee.setFirstName(employee.getFirstName());
		exisEmployee.setLastName(employee.getLastName());
		exisEmployee.setEmail(employee.getEmail());
		
		return saveEmployee(exisEmployee);
	}

	@Override
	public void deleteEmployee(long id) {
		//check whether a employee exit
		employeeRespository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Employee", "Id", id));
		employeeRespository.deleteById(id);
	}

}
