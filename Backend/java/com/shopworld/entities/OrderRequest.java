package com.shopworld.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class OrderRequest {

	private String firstName;
	
	private String lastName;
	@Id
	private String email;
	
	private String mobileNumber;
	
	private String address;
	
	private String city;
	
	private String state;
	
	private String pincode;
	
	private String paymentType;
	
	
	
}
