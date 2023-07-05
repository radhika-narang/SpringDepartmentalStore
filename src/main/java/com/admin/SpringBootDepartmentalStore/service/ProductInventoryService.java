package com.admin.SpringBootDepartmentalStore.service;

import com.admin.SpringBootDepartmentalStore.bean.BackOrder;
import com.admin.SpringBootDepartmentalStore.bean.Order;
import com.admin.SpringBootDepartmentalStore.bean.ProductInventory;
import com.admin.SpringBootDepartmentalStore.helper.ExcelHelper;
import com.admin.SpringBootDepartmentalStore.repository.BackOrderRepository;
import com.admin.SpringBootDepartmentalStore.repository.ProductInventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ProductInventoryService {

    @Autowired
    private ProductInventoryRepository productRepository;

    @Autowired
    private BackOrderService backOrderService;

    @Autowired
    private BackOrderRepository backOrderRepository;

    public void save(MultipartFile file) {

        try {
            List<ProductInventory> products = ExcelHelper.convertExcelToList(file.getInputStream());
            this.productRepository.saveAll(products);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

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

    public void updateProductQuantity(Long productId, int newQuantity) {
        ProductInventory productInventory = getProductById(productId);
        productInventory.setQuantity(newQuantity);
        productRepository.save(productInventory);

        // Check if any backorders exist for this product
        List<BackOrder> backOrders = backOrderRepository.findByOrderProductProductId(productId);
        for (BackOrder backOrder : backOrders) {
            Order order = backOrder.getOrder();
            if (newQuantity >= order.getQuantity()) {
                // Sufficient quantity available, delete the backorder
                backOrderService.deleteBackOrder(backOrder.getBackOrderId());
                productInventory.setQuantity(productInventory.getQuantity()-order.getQuantity());
            }
        }
    }

    public void updateProduct(Long productId,ProductInventory productInventory) {
        ProductInventory productObj = getProductById(productId);
        if (productObj != null) {
            productObj.setProductId(productId);
            productObj.setProductDesc(productInventory.getProductDesc());
            productObj.setProductName(productInventory.getProductName());
            productObj.setPrice(productInventory.getPrice());
            productObj.setQuantity(productInventory.getQuantity());

            // Check if any backorders exist for this product
            List<BackOrder> backOrders = backOrderRepository.findByOrderProductProductId(productId);
            for (BackOrder backOrder : backOrders) {
                Order order = backOrder.getOrder();
                if (productObj.getQuantity() >= order.getQuantity()) {
                    // Sufficient quantity available, delete the backorder
                    backOrderService.deleteBackOrder(backOrder.getBackOrderId());
                    productInventory.setQuantity(productInventory.getQuantity()-order.getQuantity());
                }
            }

            productRepository.save(productObj);
        }
    }

    public void deleteProduct(Long orderId) {
        productRepository.deleteById(orderId);
    }
}