package com.admin.SpringBootDepartmentalStore.controller;

import com.admin.SpringBootDepartmentalStore.bean.Customer;
import com.admin.SpringBootDepartmentalStore.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    /**
     * Retrieves all customers.
     *
     * @return A list of all customers.
     */

    @Operation(operationId = "getCustomer", summary = "get Customer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the Customer"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")})

    @GetMapping("/customer")
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    /**
     Retrieves a specific customer by its ID.
     @param customerId The ID of the customer to retrieve.
     @return A response entity containing the retrieved customer if found.
     */

    @Operation(operationId = "getCustomer", summary = "get Customer with Customer id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the Customer"),
            @ApiResponse(responseCode = "400", description = "Customer with given id not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")})

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long customerId) {
        Customer customer = customerService.getCustomerById(customerId);
        return ResponseEntity.ok(customer);
    }

    /**

    Adds a new customer.
    @param customer The customer object to add.
    @throws IllegalArgumentException if the customer object is not valid.
    @return A response entity indicating the success of the operation.
     */

    @Operation(operationId = "addCustomer", summary = "add Customer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully added the Customer"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")})

    @PostMapping("/customer")
    @ResponseStatus(HttpStatus.CREATED)
    public void addCustomer(@RequestBody Customer customer) {
        customerService.addCustomer(customer);
    }

    /**
    Updates a specific customer by its ID.
    @param customer The updated customer object.
    @throws IllegalArgumentException if the customer object is not valid.
     @return A response entity indicating the success of the update operation.
     */

    @Operation(operationId = "updateCustomer", summary = "update Customer with Customer id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated the Customer"),
            @ApiResponse(responseCode = "400", description = "Customer with given id not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")})

    @PutMapping("/customer/{customerId}")
    public void updateCustomer( @PathVariable Long customerId, @RequestBody Customer customer) {
        customerService.updateCustomer(customerId, customer);
    }

    /**

    Deletes a specific customer by its ID.
    @param customerId The ID of the customer to delete.
    @throws IllegalArgumentException if the customer ID is not valid.
    @return A response entity indicating the success of the deletion operation.
     */

    @Operation(operationId = "deleteCustomer", summary = "delete Customer with Customer id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully deleted the Customer"),
            @ApiResponse(responseCode = "400", description = "Customer with given id not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")})

    @DeleteMapping("/customer/{customerId}")
    public void deleteCustomer(@PathVariable Long customerId) {
        customerService.deleteCustomer(customerId);
    }
}