package com.admin.SpringBootDepartmentalStore.controller;

import com.admin.SpringBootDepartmentalStore.bean.ProductInventory;
import com.admin.SpringBootDepartmentalStore.service.ProductInventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductInventoryController {

    @Autowired
    private ProductInventoryService productInventoryService;

    @GetMapping("/productInventory")
    public List<ProductInventory> getAllProducts() {
        return productInventoryService.getAllProducts();
    }

    @PostMapping("/productInventory")
    public void addProduct(@RequestBody ProductInventory productInventory) {
        productInventoryService.addProduct(productInventory);
    }

    @PutMapping("/productInventory/{productId}")
    public void updateProduct(@RequestBody ProductInventory productInventory) {
        productInventoryService.updateProduct(productInventory);
    }

    @DeleteMapping("/productInventory/{productId}")
    public void deleteProduct(@PathVariable Long productId) {
        productInventoryService.deleteProduct(productId);
    }
}