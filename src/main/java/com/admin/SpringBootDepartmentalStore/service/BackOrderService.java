package com.admin.SpringBootDepartmentalStore.service;

import com.admin.SpringBootDepartmentalStore.bean.BackOrder;
import com.admin.SpringBootDepartmentalStore.repository.BackOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BackOrderService {

    @Autowired
    private BackOrderRepository backorderRepository;

    public final List<BackOrder> getAllBackOrders() {
        return backorderRepository.findAll();
    }

    public final BackOrder getBackOrderById(final Long backOrderId) {
        return backorderRepository.findById(backOrderId).orElseThrow(
                () -> new IllegalArgumentException("Backorder id: " + backOrderId + " not found")
        );
    }

    public final void createBackOrder(final BackOrder backorder) {
        backorderRepository.save(backorder);
    }

    public final void deleteBackOrder(final Long backOrderId) {
        backorderRepository.deleteById(backOrderId);
    }


}
