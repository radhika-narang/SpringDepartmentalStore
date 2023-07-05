package com.admin.SpringBootDepartmentalStore.service;

import com.admin.SpringBootDepartmentalStore.bean.*;
import com.admin.SpringBootDepartmentalStore.repository.BackOrderRepository;
import com.admin.SpringBootDepartmentalStore.repository.CustomerRepository;
import com.admin.SpringBootDepartmentalStore.repository.OrderRepository;
import com.admin.SpringBootDepartmentalStore.repository.ProductInventoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.when;


import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class OrderServiceTest {
    @Mock
    private OrderRepository orderRepository;
    @Mock
    private BackOrderRepository backOrderRepository;

    @Mock
    private ProductInventoryRepository productInventoryRepository;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private ProductInventoryService productInventoryService;

    @Mock
    private BackOrderService backOrderService;

    @InjectMocks
    private OrderService orderService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllOrders() {
        // Mocking repository behavior
        when(orderRepository.findAll()).thenReturn(Collections.emptyList());

        // Calling the service method
        List<Order> orders = orderService.getAllOrders();

        // Verifying the repository method was called
        verify(orderRepository, times(1)).findAll();

        // Asserting the result
        assertNotNull(orders);
        assertEquals(0, orders.size());
    }

    @Test
    void testGetOrderById_existingOrderId() {
        Long orderId = 1L;
        Order order = new Order();
        order.setOrderId(orderId);

        // Mocking repository behavior
        when(orderRepository.findById(orderId)).thenReturn(Optional.of(order));

        // Calling the service method
        Order result = orderService.getOrderById(orderId);

        // Verifying the repository method was called
        verify(orderRepository, times(1)).findById(orderId);

        // Asserting the result
        assertNotNull(result);
        assertEquals(orderId, result.getOrderId());
    }

    @Test
    void testGetOrderById_nonExistingOrderId() {
        Long orderId = 1L;

        // Mocking repository behavior
        when(orderRepository.findById(orderId)).thenReturn(Optional.empty());

        // Calling the service method and asserting the exception
        assertThrows(NoSuchElementException.class, () -> orderService.getOrderById(orderId));

        // Verifying the repository method was called
        verify(orderRepository, times(1)).findById(orderId);
    }

    @Test
    void testCheckProductAvailability_WithAvailableProduct() {
        // Arrange
        Order order = new Order();
        ProductInventory productInventory = new ProductInventory();
        productInventory.setQuantity(5);
        order.setProduct(productInventory);
        order.setQuantity(2);

        // Mock the repository save methods
        when(orderRepository.save(order)).thenReturn(order);
        when(productInventoryRepository.save(productInventory)).thenReturn(productInventory);

        // Act
        orderService.checkProductAvailability(order);

        // Assert
        assertEquals(3, productInventory.getQuantity());
        verify(orderRepository, times(1)).save(order);
        verify(productInventoryRepository, times(1)).save(productInventory);
    }

    @Test
    void testAddOrder_ValidOrder_OrderSaved() {
        // Create a valid order
        Order order = testCreateOrder(1L);
        // Set up order properties

        // Mock the behavior of dependencies
        when(orderRepository.save(any(Order.class))).thenReturn(order);
        when(productInventoryRepository.findById(order.getProduct().getProductId())).thenReturn(Optional.of(order.getProduct()));
        when(customerRepository.findById(order.getCustomer().getCustomerId())).thenReturn(Optional.of(order.getCustomer()));

        // Call the method under test
        orderService.addOrder(order);

        // Verify the order is saved
        verify(orderRepository, times(2)).save(order);
        verify(productInventoryRepository, times(1)).findById(order.getProduct().getProductId());
        verify(customerRepository, times(1)).findById(order.getCustomer().getCustomerId());
    }

    @Test
    public void testCheckProductAvailability_OrderOutOfStock_BackOrderCreated() {
        // Arrange
        Order order = new Order();
        ProductInventory productInventory = new ProductInventory();
        productInventory.setQuantity(0);
        order.setProduct(productInventory);
        order.setQuantity(2);

        // Mock the necessary dependencies and their behavior
        when(orderRepository.save(any(Order.class))).thenReturn(order);
        doNothing().when(backOrderService).createBackOrder(any(BackOrder.class));

        // Act and Assert
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            orderService.checkProductAvailability(order);
        });

        assertEquals("Order placed successfully but out of stock. We will notify you once it is in stock", exception.getMessage());
        verify(orderRepository, times(1)).save(order);
        verify(backOrderService, times(1)).createBackOrder(any(BackOrder.class));
        // Add more assertions as needed
    }


    @Test
    void testUpdateOrder() {
        // Create an order to update
        Order order = new Order();
        // Set up order properties

        // Mock the behavior of dependencies
        when(orderRepository.save(order)).thenReturn(order);

        // Call the method under test
        orderService.updateOrder(order);

        // Verify the order is saved
        verify(orderRepository, times(1)).save(order);
    }

    @Test
    void testDeleteOrder() {
        Long orderId = 1L;
        Order order = new Order();
        order.setOrderId(orderId);

        // Mock the behavior of dependencies
        when(orderRepository.findById(orderId)).thenReturn(Optional.empty()); // Simulate the case where order is not found

        // Call the method under test and verify the exception
        assertThrows(NoSuchElementException.class, () -> orderService.deleteOrder(orderId, order));

        // Verify that the deleteById method is not invoked
        verify(orderRepository, never()).deleteById(orderId);
        verify(backOrderRepository, never()).findByOrder(any(Order.class));
        verify(backOrderService, never()).deleteBackOrder(anyLong());
    }

    @Test
    private Order testCreateOrder(Long orderId) {
        // Create a sample customer
        Customer customer = new Customer();
        customer.setFullName("John Doe");
        customer.setAddress("123 Main Street");
        customer.setContactNumber("1234567890");
        customer.setEmail("john.doe@example.com");

        // Create a sample product
        ProductInventory product = new ProductInventory();
        product.setProductName("Product 1");
        product.setProductDesc("Product description");
        product.setPrice(9.99);
        product.setQuantity(10);

        // Create an order
        Order order = new Order();
        order.setOrderId(orderId);
        order.setOrderTimestamp(LocalDateTime.now());
        order.setCustomer(customer);
        order.setProduct(product);
        order.setQuantity(2);
        order.setDiscount(10.0);
        order.setDiscountedPrice(90.0);

        return order;

    }
}


