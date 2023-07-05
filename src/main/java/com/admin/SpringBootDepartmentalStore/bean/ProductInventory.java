package com.admin.SpringBootDepartmentalStore.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="ProductInventory")
@Data
@SuppressFBWarnings(value = {"EI_EXPOSE_REP", "EI_EXPOSE_REP2"})
public class ProductInventory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long productId;
	private String productDesc;
	private String productName;
	private double price;
	private int quantity;

	@OneToMany(cascade = CascadeType.ALL)
	@JsonIgnore
	@JoinColumn(name="productId")
	private List<Order> orders= new ArrayList<>();

}