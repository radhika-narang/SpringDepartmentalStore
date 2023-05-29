package com.admin.SpringBootDepartmentalStore.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="ProductInventory")
@NoArgsConstructor
@Data
@AllArgsConstructor
public class ProductInventory {
	@Id
	//@GeneratedValue(strategy = GenerationType.AUTO)
	private Long productId;
	private String productDesc;
	private String productName;
	private double price;
	private Date expiry;
	private int count;
	private boolean availability;

	@OneToMany(cascade = CascadeType.ALL)
	@JsonIgnore
	@JoinColumn(name="productId")
	private List<Order> orders= new ArrayList<>();

}