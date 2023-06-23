package com.admin.SpringBootDepartmentalStore.repository;

import com.admin.SpringBootDepartmentalStore.bean.BackOrder;
import com.admin.SpringBootDepartmentalStore.bean.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BackOrderRepository extends JpaRepository<BackOrder,Long> {
    BackOrder findByOrder(Order order);

}
