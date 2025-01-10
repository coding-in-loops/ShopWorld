package com.shopworld.servicesimpl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopworld.entities.Invoice;
import com.shopworld.entities.OrderRequest;
import com.shopworld.entities.ProductOrder;
import com.shopworld.repositories.InvoiceRepository;
import com.shopworld.services.InvoiceService;

@Service
public class InvoiceServiceImpl implements InvoiceService{

	@Autowired
	InvoiceRepository invoiceRepository;
	
	 @Override
	    public Invoice generateInvoice(List<ProductOrder> productOrders, OrderRequest orderRequest, Double totalAmount, String paymentType) {
	        // Create a new invoice
	        Invoice invoice = new Invoice();
	        invoice.setInvoiceNumber("INV" + System.currentTimeMillis()); // Generate unique invoice number
	        invoice.setInvoiceDate(LocalDate.now());
	        invoice.setTotalAmount(totalAmount);
	        invoice.setPaymentType(paymentType);
	        invoice.setProductOrders(productOrders);
	        invoice.setOrderRequest(orderRequest);  // Associating the order details

	        return invoiceRepository.save(invoice);  // Save to the database
	    }

	    @Override
	    public Invoice getInvoiceById(Integer invoiceId) {
	        return invoiceRepository.findById(invoiceId).orElse(null);  // Fetch invoice by ID
	    }

	    @Override
	    public List<Invoice> getAllInvoices() {
	        return invoiceRepository.findAll();  // Get all invoices
	    }
}
