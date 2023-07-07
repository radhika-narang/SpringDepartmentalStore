package com.admin.SpringBootDepartmentalStore.repository;

import com.admin.SpringBootDepartmentalStore.bean.ProductInventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductInventoryRepository extends JpaRepository<ProductInventory, Long> {
}
