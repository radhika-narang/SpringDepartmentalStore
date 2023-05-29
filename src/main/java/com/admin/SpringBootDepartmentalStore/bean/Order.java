package com.admin.SpringBootDepartmentalStore.bean;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name="Orders")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long orderId;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "productId", referencedColumnName = "productId")
	private ProductInventory product;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "customerId", referencedColumnName = "customerId")
	private Customer customer;

	private LocalDateTime orderTimestamp;
	private int quantity;
	private double discount;
	private double totalPrice;
	private double discountedPrice;

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public void setDiscountedPrice(double discountedPrice) {
		this.discountedPrice= discountedPrice;
	}

}