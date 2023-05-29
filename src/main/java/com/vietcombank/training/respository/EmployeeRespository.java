package com.vietcombank.training.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vietcombank.training.model.Employee;

@Repository
public interface EmployeeRespository extends JpaRepository<Employee, Long> {

}
