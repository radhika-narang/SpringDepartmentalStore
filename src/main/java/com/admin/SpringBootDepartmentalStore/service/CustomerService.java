package com.admin.SpringBootDepartmentalStore.service;

import com.admin.SpringBootDepartmentalStore.bean.Customer;
import com.admin.SpringBootDepartmentalStore.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

    private void validateEmail(String email) {
        String valid = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        if (!email.matches(valid)) {
            throw new IllegalArgumentException("Invalid email");
        }
    }

    public void validateContactNumber(String contactNumber) {
        if (contactNumber == null) {
            throw new IllegalArgumentException("Contact number cannot be null");
        }

        // Regular expression pattern for a 10-digit contact number
        String pattern = "\\d{10}";

        if (!contactNumber.matches(pattern)) {
            throw new IllegalArgumentException("Invalid contact number");
        }
    }

    public void addCustomer(Customer customer) {
        validateEmail(customer.getEmail());
        validateContactNumber(customer.getContactNumber());
        customerRepository.save(customer);
    }


    public void updateCustomer(Long customerId,Customer customer) {
        Customer customerObj = getCustomerById(customerId);
        if (customerObj != null) {
            String updatedEmail = customer.getEmail();
            if (updatedEmail != null && !updatedEmail.isEmpty()) {
                validateEmail(updatedEmail); // Perform email validation
                validateContactNumber(customer.getContactNumber());
                customerObj.setCustomerId(customerId);
                customerObj.setFullName(customer.getFullName());
                customerObj.setAddress(customer.getAddress());
                customerObj.setContactNumber(customer.getContactNumber());
                customerObj.setEmail(updatedEmail);
                customerRepository.save(customerObj);
            } else {
                throw new IllegalArgumentException("Email cannot be null or empty");
            }
        } else {
            throw new IllegalArgumentException("Customer not found");
        }
    }

    public void deleteCustomer(Long customerId) {
        customerRepository.deleteById(customerId);
    }


}