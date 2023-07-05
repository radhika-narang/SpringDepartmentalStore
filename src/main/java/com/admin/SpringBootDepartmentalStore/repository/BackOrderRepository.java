package com.admin.SpringBootDepartmentalStore.repository;

import com.admin.SpringBootDepartmentalStore.bean.BackOrder;
import com.admin.SpringBootDepartmentalStore.bean.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BackOrderRepository extends JpaRepository<BackOrder,Long> {
    Optional<BackOrder> findByOrder(Order order);

    List<BackOrder> findByOrderProductProductId(Long productId);
}
