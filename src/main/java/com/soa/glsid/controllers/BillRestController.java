package com.soa.glsid.controllers;

import com.soa.glsid.entities.Bill;
import com.soa.glsid.respositories.BillRepository;
import com.soa.glsid.respositories.ProductItemRepository;
import com.soa.glsid.services.CustomerService;
import com.soa.glsid.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BillRestController {
    @Autowired
    private ProductService productService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private BillRepository bilRepository;

    @Autowired
    private ProductItemRepository productItemRepository;

    @GetMapping("/findbill/{id}")
    public Bill findBillById(@PathVariable(name="id") Long id)
    {
        Bill bill = this.bilRepository.findById(id).get();
        bill.setCustomer(this.customerService.findClientById(bill.getCustomerId()));
        bill.getProductItems().forEach(p->{
            p.setProduct(this.productService.findProductById(p.getProductId()));
        });
        return bill;
    }
}
