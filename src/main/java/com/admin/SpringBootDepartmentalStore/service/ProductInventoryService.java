package com.admin.SpringBootDepartmentalStore.service;

import com.admin.SpringBootDepartmentalStore.bean.ProductInventory;
import com.admin.SpringBootDepartmentalStore.repository.ProductInventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductInventoryService {

    @Autowired
    private ProductInventoryRepository productRepository;

    public List<ProductInventory> getAllProducts()
    {
        return productRepository.findAll();
    }

    public Optional<ProductInventory> getProductById(long id)
    {
        Optional<ProductInventory> prod = productRepository.findById(id);
        return prod;
    }

    public void addProduct(ProductInventory productInventory) {

        productRepository.save(productInventory);
    }

    public void updateProduct(ProductInventory productInventory) {

        productRepository.save(productInventory);
    }

    public void deleteProduct(Long orderId) {
        productRepository.deleteById(orderId);
    }
}