package com.admin.SpringBootDepartmentalStore.controller;

import com.admin.SpringBootDepartmentalStore.bean.ProductInventory;
import com.admin.SpringBootDepartmentalStore.service.ProductInventoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductInventoryController {

    @Autowired
    private ProductInventoryService productInventoryService;

    /**
     Retrieves all products in the inventory.
     @return A list of all products in the inventory.
     */

    @Operation(operationId = "getProduct", summary = "get Product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the Product"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")})

    @GetMapping("/productInventory")
    public List<ProductInventory> getAllProducts() {
        return productInventoryService.getAllProducts();
    }

    /**
    Retrieves a specific product from the inventory by its ID.
    @param productId The ID of the product to retrieve.
    @return A response entity containing the retrieved product if found.
     */

    @Operation(operationId = "getProduct", summary = "get Product with Product id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the Product"),
            @ApiResponse(responseCode = "400", description = "Product with given id not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")})

    @GetMapping("/productInventory/{productId}")
    public ResponseEntity<ProductInventory> getProductById(@PathVariable Long productId)
    {
        ProductInventory productInventory = productInventoryService.getProductById(productId);
        return ResponseEntity.ok(productInventory);
    }

    /**
     Adds a new product to the inventory.
     @param productInventory The product inventory object to add.
     @throws IllegalArgumentException if the product inventory object is not valid.
     */

    @Operation(operationId = "addProduct", summary = "add Product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully added the Product"),
            @ApiResponse(responseCode = "400", description = "Product with given id not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")})

    @PostMapping("/productInventory")
    public void addProduct(@RequestBody ProductInventory productInventory) {
        productInventoryService.addProduct(productInventory);
    }

    /**

     Updates a specific product in the inventory by its ID.
     @param productInventory The updated product inventory object.
     @throws IllegalArgumentException if the product inventory object is not valid.
     */

    @Operation(operationId = "updateProduct", summary = "update Product with Product id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated the product"),
            @ApiResponse(responseCode = "400", description = "Product with given id not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")})

    @PutMapping("/productInventory/{productId}")
    public void updateProduct(@RequestBody ProductInventory productInventory) {
        productInventoryService.updateProduct(productInventory);
    }

    /**

     Deletes a specific product from the inventory by its ID.
     @param productId The ID of the product to delete.
     @throws IllegalArgumentException if the product ID is not valid.
     */

    @Operation(operationId = "deleteOrder", summary = "delete Product with Product id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully deleted the Product"),
            @ApiResponse(responseCode = "400", description = "Product with given id not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")})

    @DeleteMapping("/productInventory/{productId}")
    public void deleteProduct(@PathVariable Long productId) {
        productInventoryService.deleteProduct(productId);
    }
}