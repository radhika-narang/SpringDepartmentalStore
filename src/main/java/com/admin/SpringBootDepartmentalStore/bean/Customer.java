package com.admin.SpringBootDepartmentalStore.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Customer")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
	@Id
	//@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="customerId")
	private Long customerId;
	@Column(name="fullName")
	private String fullName;
	@Column(name="address")
	private String address;
	@Column(name="contactNumber")
	private String contactNumber;


	@OneToMany(cascade = CascadeType.ALL)
	@JsonIgnore
	@JoinColumn(name="customerId")
	private List<Order> orders= new ArrayList<>();

}