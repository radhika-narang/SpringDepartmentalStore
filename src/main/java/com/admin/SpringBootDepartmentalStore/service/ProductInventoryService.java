package com.admin.SpringBootDepartmentalStore.service;

import com.admin.SpringBootDepartmentalStore.bean.ProductInventory;
import com.admin.SpringBootDepartmentalStore.repository.ProductInventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ProductInventoryService {

    @Autowired
    private ProductInventoryRepository productRepository;

    public List<ProductInventory> getAllProducts()
    {
        return productRepository.findAll();
    }

    public ProductInventory getProductById(Long productId) {
        return productRepository.findById(productId).orElseThrow(NoSuchElementException::new);
    }

    public void addProduct(ProductInventory productInventory) {

        productRepository.save(productInventory);
    }

    public void updateProduct(Long productId,ProductInventory productInventory) {
        ProductInventory productObj = getProductById(productId);
        if(productObj != null) {
            productObj.setProductId(productId);
            productObj.setProductDesc(productInventory.getProductDesc());
            productObj.setProductName(productInventory.getProductName());
            productObj.setPrice(productInventory.getPrice());
            productObj.setExpiry(productInventory.getExpiry());
            productObj.setCount(productInventory.getCount());
            productObj.setAvailability(productInventory.isAvailability());
        }
        productRepository.save(productObj);
    }

    public void deleteProduct(Long orderId) {
        productRepository.deleteById(orderId);
    }
}