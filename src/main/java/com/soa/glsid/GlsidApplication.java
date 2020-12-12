package com.soa.glsid;

import com.soa.glsid.entities.Bill;
import com.soa.glsid.respositories.BillRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GlsidApplication {

    public static void main(String[] args) {
        SpringApplication.run(GlsidApplication.class, args);
    }

    @Bean
    CommandLineRunner start(BillRepository billRepository) {
        return args -> {
          billRepository.save(new Bill(null, 1222, 1));
          billRepository.save(new Bill(null, 5200, 4));
          billRepository.save(new Bill(null, 6220, 3));

          billRepository.findAll().forEach(bill -> {
              System.out.println("Bill Price : " + bill.getPrice() + " Customer ID: " + bill.getCustomerId() + " Bill Total Price : " + bill.getPrice());
          });
        };
    }

}
