package com.admin.SpringBootDepartmentalStore.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import lombok.Data;


import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Customer")
@Data
@SuppressFBWarnings(value = {"EI_EXPOSE_REP", "EI_EXPOSE_REP2"})
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

	@Column(name = "email")
	private String email;

	@OneToMany(cascade = CascadeType.ALL)
	@JsonIgnore
	@JoinColumn(name="customerId")
	private List<Order> orders= new ArrayList<>();

}