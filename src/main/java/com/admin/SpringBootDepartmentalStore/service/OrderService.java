package com.admin.SpringBootDepartmentalStore.service;

import com.admin.SpringBootDepartmentalStore.bean.Customer;
import com.admin.SpringBootDepartmentalStore.bean.Order;
import com.admin.SpringBootDepartmentalStore.bean.ProductInventory;
import com.admin.SpringBootDepartmentalStore.repository.CustomerRepository;
import com.admin.SpringBootDepartmentalStore.repository.OrderRepository;
import com.admin.SpringBootDepartmentalStore.repository.ProductInventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductInventoryRepository productInventoryRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductInventoryService productInventoryService;

    HashMap<Long,List<Order>> backOrder = new HashMap<>();

    public List<Order> getAllOrders()
    {
        List<Order> orders = new ArrayList<>();
        orderRepository.findAll();
        return orders;
    }

    public Order getOrderById(Long orderId) {
        return orderRepository.findById(orderId).orElseThrow(NoSuchElementException::new);
    }

    /*public void updateOtherEntities(Order order) {
        Customer customer = customerRepository.findById(order.getCustomer().getCustomerId()).orElse(null);
        ProductInventory productInventory = productInventoryRepository.findById(order.getProduct().getProductId()).orElse(null);
        order.setCustomer(customer);
        order.setProduct(productInventory);
    }*/

    public void discount(Order order){
        ProductInventory productInventory = order.getProduct();
        double productPrice = productInventory.getPrice();

        double discountedPrice = productPrice - (productPrice * (order.getDiscount() / 100.0));
        order.setDiscountedPrice(discountedPrice);

        double totalPrice = discountedPrice * order.getQuantity();

        order.setTotalPrice(totalPrice);
    }


    public void addOrder(Order order) {
       //updateOtherEntities(order);

        Customer customer = order.getCustomer();
        List<Order> li=List.of(order);
        customer.setOrders(li);
        customerRepository.save(customer);

        List<Order> orders=order.getCustomer().getOrders();

        Order ordObj= orders.get(orders.size()-1);
        int demandCount=ordObj.getQuantity();
        Long prodId=ordObj.getProduct().getProductId();
        Long ordId=ordObj.getOrderId();

        Optional<ProductInventory> prodObj = productInventoryService.getProductById(prodId);
        boolean isAvail=prodObj.get().isAvailability();
        int prodCount=prodObj.get().getCount();

        if( isAvail && demandCount>prodCount)
        {
            List<Order> ordList = backOrder.get(prodId);
            if(ordList == null)
            {
                List<Order> backOrdList= new ArrayList<>();
                backOrdList.add(order);
                backOrder.put(prodId,backOrdList);
            }
            else{
                backOrder.get(prodId).add(order);
            }

            System.out.println("Your order has been placed but due to unavailability, it has been sent to backrder.");

        }
        else{
            orderRepository.save(order);
            System.out.println("Your order has been placed successfully.");
        }



        discount(order);
        orderRepository.save(order);

    }

    public void updateOrder(Order order) {
        orderRepository.save(order);
    }

    public void deleteOrder(Long orderId, Order order) {
        Customer customer = order.getCustomer();
        orderRepository.deleteById(orderId);
    }

    public HashMap<Long,List<Order>> BackOrders()
    {
        return backOrder;
    }

}