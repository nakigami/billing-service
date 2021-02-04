package com.soa.glsid;

import com.soa.glsid.entities.Bill;
import com.soa.glsid.entities.Customer;
import com.soa.glsid.entities.Product;
import com.soa.glsid.entities.ProductItem;
import com.soa.glsid.respositories.BillRepository;
import com.soa.glsid.respositories.ProductItemRepository;
import com.soa.glsid.services.CustomerService;
import com.soa.glsid.services.ProductService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.Projection;
import org.springframework.hateoas.PagedModel;

import java.util.Collection;
import java.util.Date;

@Projection(name="fullbill", types = { Bill.class })
interface BillingProjection {
    public Long getId();
    public Date getBillingDate();
    public Long getCustomerId();
    public Collection<ProductItem> getProductItems();
}

@SpringBootApplication
@EnableFeignClients
public class GlsidApplication {

    public static void main(String[] args) {
        SpringApplication.run(GlsidApplication.class, args);
    }

    @Bean
    CommandLineRunner start(BillRepository billRepository,
                            ProductItemRepository productItemRepository,
                            CustomerService customerService,
                            ProductService productService) {
        return args -> {
          Customer c1 = customerService.findClientById(1L);
          System.out.println("*******************");
          System.out.println("Customer ID : " + c1.getId());
          System.out.println("Customer Name : " + c1.getName());
          System.out.println("Customer Email : " + c1.getEmail());
          System.out.println("*******************");

          Bill b1 = billRepository.save(new Bill(null, new Date(), c1.getId(), null, null));
          PagedModel<Product> products = productService.findAllProducts();
          products.getContent().forEach(p -> {
              productItemRepository.save(new ProductItem(null, p.getId(),null, p.getPrice(), 200, b1));
          });
        };
    }
}
