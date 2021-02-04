package com.soa.glsid.services;

import com.soa.glsid.entities.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="product-service")
public interface ProductService {
    @GetMapping("/products/{id}")
    public Product findProductById(@PathVariable(name="id") Long Id);
    @GetMapping("/products")
    public PagedModel<Product> findAllProducts();
}
