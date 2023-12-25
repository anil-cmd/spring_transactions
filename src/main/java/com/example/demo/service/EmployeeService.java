package com.example.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Employee;
import com.example.demo.entity2.EmployeeAddress;
import com.example.demo.repo2.AddressRepo;
import com.example.demo.repo.EmployeeRepo;


@Service
public class EmployeeService {
	
	private EmployeeRepo employeeRepo;
	
	private AddressRepo addressRepo;

	public EmployeeService(EmployeeRepo employeeRepo, AddressRepo addressRepo) {
		this.employeeRepo = employeeRepo;
		this.addressRepo = addressRepo;
	}
	
	@Transactional(rollbackFor = Exception.class)
	public void saveData() {
		
		Employee employee = new Employee();
		employee.setEmpname("Carly");
		employee.setEmpSalary(13000.00);
		
		employeeRepo.save(employee);
		

		
		EmployeeAddress address = new EmployeeAddress();
		address.setCity("Toronto");
		address.setState("Ontario");
		address.settype("Permanent");
		address.setEmpId(employee.getEmpId());
		
		addressRepo.save(address);
		
	}
	
	

}
