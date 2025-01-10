package com.shopworld.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Product {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(length=500)
	private String title;
	
	@Column(length=5000)
	private String description;
	
	private String category;
	
	private Double price;
	
	private Integer stock;
	
	private String image;
	
	private int discount;
	
	private Double discountPrice;
	
	private Boolean isActive;
	
	
	
}
