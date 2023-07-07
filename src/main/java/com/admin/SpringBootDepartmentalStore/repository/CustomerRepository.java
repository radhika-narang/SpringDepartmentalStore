package com.admin.SpringBootDepartmentalStore.repository;

import com.admin.SpringBootDepartmentalStore.bean.Customer;
import org.springframework.data.jpa.repository.JpaRepository;



public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
