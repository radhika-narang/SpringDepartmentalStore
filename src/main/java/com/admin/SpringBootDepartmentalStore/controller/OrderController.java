package com.admin.SpringBootDepartmentalStore.controller;

import com.admin.SpringBootDepartmentalStore.bean.Order;
import com.admin.SpringBootDepartmentalStore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/order")
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long orderId)
    {
       Order order = orderService.getOrderById(orderId);
       return ResponseEntity.ok(order);
    }

    @PostMapping("/order")
    public void addOrder(@RequestBody Order order)
    {
        orderService.addOrder(order);
    }

    @PutMapping("/order/{orderId}")
    public ResponseEntity<String> updateOrder(@RequestBody Order order) {
        orderService.updateOrder(order);
        return ResponseEntity.ok("Order has been updated successfully.");
    }

    @DeleteMapping("/order/{orderId}")
    public ResponseEntity<String> deleteOrder(@PathVariable Long orderId, Order order) {
        orderService.deleteOrder(orderId, order);
        return ResponseEntity.ok("Order has been deleted successfully.");
    }

    @GetMapping("Backorders")
    public HashMap<Long, List<Order>> BackOrders()
    {
        return orderService.BackOrders();
    }
}