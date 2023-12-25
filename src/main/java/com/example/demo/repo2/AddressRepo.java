package com.example.demo.repo2;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity2.*;


public interface AddressRepo extends JpaRepository<EmployeeAddress, Integer>{

}
