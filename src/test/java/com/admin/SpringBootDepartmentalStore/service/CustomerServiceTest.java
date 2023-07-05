package com.admin.SpringBootDepartmentalStore.service;

import com.admin.SpringBootDepartmentalStore.bean.Customer;
import com.admin.SpringBootDepartmentalStore.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

    @SpringBootTest
    public class CustomerServiceTest {

        @Mock
        private CustomerRepository customerRepository;

        @InjectMocks
        private CustomerService customerService;

        @Test
        void testGetAllCustomers() {
            // Create a list of customers for mocking repository behavior
            List<Customer> customers = new ArrayList<>();
            customers.add(new Customer());
            customers.add(new Customer());

            // Mock the behavior of the customerRepository.findAll() method
            when(customerRepository.findAll()).thenReturn(customers);

            // Call the method under test
            List<Customer> result = customerService.getAllCustomers();

            // Verify the returned list matches the mocked list
            assertEquals(customers, result);
        }

        @Test
        void testGetCustomerById() {
            Long customerId = 1L;
            Customer customer = new Customer();

            // Mock the behavior of the customerRepository.findById() method
            when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));

            // Call the method under test
            Customer result = customerService.getCustomerById(customerId);

            // Verify the returned customer is the same as the mocked customer
            assertEquals(customer, result);
        }

        @Test
        void testGetCustomerById_CustomerNotFound() {
            Long customerId = 1L;

            // Mock the behavior of the customerRepository.findById() method to return an empty Optional
            when(customerRepository.findById(customerId)).thenReturn(Optional.empty());

            // Call the method under test and verify the NoSuchElementException is thrown
            assertThrows(NoSuchElementException.class, () -> customerService.getCustomerById(customerId));
        }

        @Test
        void testAddCustomer() {
            Customer customer = new Customer();
            customer.setEmail(null); // Set email as null to trigger the exception
            customer.setContactNumber("123"); // Set an invalid contact number

            // Call the method under test and verify the exception is handled
            assertThrows(NullPointerException.class, () -> customerService.addCustomer(customer));

            // Verify that customerRepository.save() method is never invoked
            verify(customerRepository, never()).save(customer);
        }

        @Test
        void testUpdateCustomer() {
            Long customerId = 1L;
            Customer customer = new Customer();
            customer.setFullName("John Doe");
            customer.setEmail("john.doe@example.com"); // Set a valid email address here
            customer.setContactNumber("1234567890"); // Set a valid contact number

            // Mock the behavior of the customerRepository.findById() method
            when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));

            // Call the method under test
            customerService.updateCustomer(customerId, customer);

            // Verify the customerRepository.save() method is invoked
            verify(customerRepository, times(1)).save(customer);
        }

        @Test
        void testUpdateCustomer_CustomerNotFound() {
            Long customerId = 1L;
            Customer customer = new Customer();
            customer.setFullName("John Doe");
            customer.setEmail("johndoe@example.com"); // Set a valid email address
            customer.setContactNumber("1234567890"); // Set a valid contact number


            // Mock the behavior of the customerRepository.findById() method to return an empty Optional
            when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));

            // Call the method under test
            customerService.updateCustomer(customerId, customer);

            // Verify the customerRepository.save() method is invoked
            verify(customerRepository, times(1)).save(customer);
        }

        @Test
        void testDeleteCustomer() {
            Long customerId = 1L;

            // Call the method under test
            customerService.deleteCustomer(customerId);

            // Verify the customerRepository.deleteById() method is invoked
            verify(customerRepository, times(1)).deleteById(customerId);
        }
    }


