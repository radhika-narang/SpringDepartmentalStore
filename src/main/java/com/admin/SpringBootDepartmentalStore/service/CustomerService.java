package com.admin.SpringBootDepartmentalStore.service;

import com.admin.SpringBootDepartmentalStore.bean.Customer;
import com.admin.SpringBootDepartmentalStore.bean.Order;
import com.admin.SpringBootDepartmentalStore.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> getAllCustomers()
    {
        return customerRepository.findAll();
    }

    public Customer getCustomerById(Long customerId) {
        return customerRepository.findById(customerId).orElseThrow(NoSuchElementException::new);
    }

    public void addCustomer(Customer customer) {
        String validEmail = customer.getEmail();
        String emailRegex = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        if(validEmail.matches(emailRegex)){
            customerRepository.save(customer);
        }
        else{
            throw new IllegalArgumentException("Invalid email");
        }
    }

    public void updateCustomer(Customer customer) {

        String emailId = customer.getEmail();
        String validEmail = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        if(emailId.matches(validEmail)){
            customerRepository.save(customer);
        }
        else{
            throw new IllegalArgumentException("Invalid email");
        }
    }

    public void deleteCustomer(Long customerId) {
        customerRepository.deleteById(customerId);
    }
}