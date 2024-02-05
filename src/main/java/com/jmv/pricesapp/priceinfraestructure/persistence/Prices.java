package com.jmv.pricesapp.priceinfraestructure.persistence;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "prices")
@IdClass(PricesId.class)
public class Prices {

    @Id
    @Column(name="BRAND_ID", nullable = false)
    private long brandId;

    @Id
    @Column(name="PRODUCT_ID", nullable = false)
    private long productId;

    @Id
    @Column(name="START_DATE", nullable = false, columnDefinition = "TIMESTAMP")
    private LocalDateTime startDate;

    @Id
    @Column(name="END_DATE", nullable = false, columnDefinition = "TIMESTAMP")
    private LocalDateTime endDate;

    @Id
    @Column(name="PRIORITY", nullable = false)
    private long priority;


    @Column(name="PRICE_LIST")
    private long priceId;

    @Column(name="PRICE", nullable = false)
    private BigDecimal price;

    @Column(name="CURR", nullable = false)
    private String curr;

    public long getBrandId() {
        return brandId;
    }

    public void setBrandId(long brandId) {
        this.brandId = brandId;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public long getPriority() {
        return priority;
    }

    public void setPriority(long priority) {
        this.priority = priority;
    }

    public long getPriceId() {
        return priceId;
    }

    public void setPriceId(long priceId) {
        this.priceId = priceId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getCurr() {
        return curr;
    }

    public void setCurr(String curr) {
        this.curr = curr;
    }
}
