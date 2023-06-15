package com.admin.SpringBootDepartmentalStore.controller;

import com.admin.SpringBootDepartmentalStore.bean.Order;
import com.admin.SpringBootDepartmentalStore.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**

     Retrieves all orders.
     @return A list of all orders.
     */
    @Operation(operationId = "getOrder", summary = "get Order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the Order"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")})

    @GetMapping("/order")
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    /**
    Retrieves a specific order by its ID.
    @param orderId The ID of the order to retrieve.
    @return A response entity containing the retrieved order if found.
     */

    @Operation(operationId = "getOrder", summary = "get Order with Order id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the Order"),
            @ApiResponse(responseCode = "400", description = "Order with given id not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")})

    @GetMapping("/order/{orderId}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long orderId)
    {
       Order order = orderService.getOrderById(orderId);
       return ResponseEntity.ok(order);
    }

    /**

    Adds a new order.
    @param order The order object to add.
    @throws IllegalArgumentException if the order object is not valid.
    @return A response entity indicating the success of the operation.
     */

    @Operation(operationId = "addOrder", summary = "add Order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully added the Order"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")})


    @PostMapping("/order")
    public void addOrder(@RequestBody Order order)
    {
        orderService.addOrder(order);
    }

    /**

    Updates a specific order by its ID.
    @param order The updated order object.
    @throws IllegalArgumentException if the order object is not valid.
    @return A response entity indicating the success of the update operation.
     */

    @Operation(operationId = "updateOrder", summary = "update Order with Order id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated the Order"),
            @ApiResponse(responseCode = "400", description = "Order with given id not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")})

    @PutMapping("/order/{orderId}")
    public ResponseEntity<String> updateOrder(@RequestBody Order order) {
        orderService.updateOrder(order);
        return ResponseEntity.ok("Order has been updated successfully.");
    }

    /**

    Deletes a specific order by its ID.
    @param orderId The ID of the order to delete.
    @param order The order object.
    @throws IllegalArgumentException if the order ID or the order object is not valid.
    @return A response entity indicating the success of the deletion operation.
     */

    @Operation(operationId = "deleteOrder", summary = "delete Order with Order id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully deleted the Order"),
            @ApiResponse(responseCode = "400", description = "Order with given id not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")})

    @DeleteMapping("/order/{orderId}")
    public ResponseEntity<String> deleteOrder(@PathVariable Long orderId, Order order) {
        orderService.deleteOrder(orderId, order);
        return ResponseEntity.ok("Order has been deleted successfully.");
    }

}