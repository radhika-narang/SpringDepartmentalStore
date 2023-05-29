package com.admin.SpringBootDepartmentalStore.repository;

import com.admin.SpringBootDepartmentalStore.bean.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {
}
