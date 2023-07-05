package com.admin.SpringBootDepartmentalStore.service;

import com.admin.SpringBootDepartmentalStore.bean.BackOrder;
import com.admin.SpringBootDepartmentalStore.bean.Order;
import com.admin.SpringBootDepartmentalStore.repository.BackOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class BackOrderService {

    @Autowired
    private BackOrderRepository backorderRepository;

    public List<BackOrder> getAllBackOrders() {
        return backorderRepository.findAll();
    }

    public BackOrder getBackOrderById(Long backOrderId) {
        return backorderRepository.findById(backOrderId).orElseThrow(
                () -> new IllegalArgumentException("Backorder id: " + backOrderId + " not found")
        );
    }

    public void createBackOrder(BackOrder backorder) {
        backorderRepository.save(backorder);
    }

    public void deleteBackOrder(Long backOrderId) {
        backorderRepository.deleteById(backOrderId);
    }


}
