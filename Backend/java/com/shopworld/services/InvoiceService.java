package com.shopworld.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.shopworld.entities.Invoice;
import com.shopworld.entities.OrderRequest;
import com.shopworld.entities.ProductOrder;

@Service
public interface InvoiceService {

    Invoice generateInvoice(List<ProductOrder> productOrders, OrderRequest orderRequest, Double totalAmount, String paymentType);
    Invoice getInvoiceById(Integer invoiceId);
    List<Invoice> getAllInvoices();
}
