package com.shopworld.entities;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
public class Invoice {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    private String invoiceNumber;
    
    private LocalDate invoiceDate;
    
    private Double totalAmount;
    
    private String paymentType;
    
    @OneToMany(cascade = CascadeType.ALL)
    private List<ProductOrder> productOrders; // List of products in the invoice

    @OneToMany(cascade = CascadeType.ALL)
    private List<OrderAddress> orderAddresses; 
    
    @OneToOne
    private OrderRequest orderRequest; 
}
