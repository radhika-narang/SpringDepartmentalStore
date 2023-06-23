package com.admin.SpringBootDepartmentalStore.service;
import com.admin.SpringBootDepartmentalStore.bean.ProductInventory;
import com.admin.SpringBootDepartmentalStore.repository.ProductInventoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductInventoryServiceTest {

    @Mock
    private ProductInventoryRepository productRepository;

    @InjectMocks
    private ProductInventoryService productInventoryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllProducts() {
        // Create a list of dummy products
        List<ProductInventory> products = new ArrayList<>();
        products.add(new ProductInventory());
        products.add(new ProductInventory());

        // Mock the behavior of the productRepository.findAll() method
        when(productRepository.findAll()).thenReturn(products);

        // Call the method under test
        List<ProductInventory> result = productInventoryService.getAllProducts();

        // Verify the result
        assertEquals(products.size(), result.size());
        assertEquals(products, result);
        verify(productRepository, times(1)).findAll();
    }

    @Test
    void testGetProductById() {
        // Create a dummy product
        ProductInventory product = new ProductInventory();
        Long productId = 1L;
        product.setProductId(productId);

        // Mock the behavior of the productRepository.findById() method
        when(productRepository.findById(productId)).thenReturn(Optional.of(product));

        // Call the method under test
        ProductInventory result = productInventoryService.getProductById(productId);

        // Verify the result
        assertNotNull(result);
        assertEquals(productId, result.getProductId());
        verify(productRepository, times(1)).findById(productId);
    }

    @Test
    void testGetProductById_ProductNotFound() {
        Long productId = 1L;

        // Mock the behavior of the productRepository.findById() method
        when(productRepository.findById(productId)).thenReturn(Optional.empty());

        // Call the method under test and verify that it throws NoSuchElementException
        assertThrows(NoSuchElementException.class, () -> productInventoryService.getProductById(productId));
        verify(productRepository, times(1)).findById(productId);
    }

    @Test
    void testAddProduct() {
        // Create a dummy product
        ProductInventory product = new ProductInventory();

        // Call the method under test
        productInventoryService.addProduct(product);

        // Verify that the productRepository.save() method was called once
        verify(productRepository, times(1)).save(product);
    }

    @Test
    void testUpdateProduct() {
        // Create a dummy product
        Long productId = 1L;
        ProductInventory product = new ProductInventory();
        product.setProductId(productId);

        // Mock the behavior of the productRepository.findById() method
        when(productRepository.findById(productId)).thenReturn(Optional.of(product));

        // Call the method under test
        productInventoryService.updateProduct(productId, product);

        // Verify that the productRepository.save() method was called once
        verify(productRepository, times(1)).save(product);
    }

    @Test
    void testUpdateProduct_ProductNotFound() {
        Long productId = 1L;

        // Mock the behavior of the productRepository.findById() method
        when(productRepository.findById(productId)).thenReturn(Optional.empty());

        // Create a dummy product
        ProductInventory product = new ProductInventory();
        product.setProductId(productId);

        // Call the method under test and verify that it throws NoSuchElementException
        assertThrows(NoSuchElementException.class, () -> productInventoryService.updateProduct(productId, product));
        verify(productRepository, times(0)).save(product);
    }

    @Test
    void testDeleteProduct() {
        // Create a dummy product
        Long productId = 1L;

        // Call the method under test
        productInventoryService.deleteProduct(productId);

        // Verify that the productRepository.deleteById() method was called once with the correct parameter
        verify(productRepository, times(1)).deleteById(productId);
    }

}

