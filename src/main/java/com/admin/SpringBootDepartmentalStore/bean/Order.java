package com.admin.SpringBootDepartmentalStore.bean;
import javax.persistence.*;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name="Orders")
@Data
@SuppressFBWarnings(value = {"EI_EXPOSE_REP", "EI_EXPOSE_REP2"})
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
}