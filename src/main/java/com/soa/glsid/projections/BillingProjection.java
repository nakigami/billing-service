package com.soa.glsid.projections;

import com.soa.glsid.entities.Bill;
import com.soa.glsid.entities.ProductItem;
import org.springframework.data.rest.core.config.Projection;

import java.util.Collection;
import java.util.Date;


@Projection(name="fullbill", types = { Bill.class })
interface BillingProjection {
    public Long getId();
    public Date getDate();
    public Long getCustomerId();
    public Collection<ProductItem> getProductItems();
}
