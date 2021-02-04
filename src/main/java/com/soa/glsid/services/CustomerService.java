package com.soa.glsid.services;

import com.soa.glsid.entities.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="customer-service")
public interface CustomerService {
    @GetMapping("/customers/{id}")
    public Customer findClientById(@PathVariable(name = "id")  Long id);
}
