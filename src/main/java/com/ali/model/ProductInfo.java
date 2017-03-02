package com.ali.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Calendar;

@Entity
public class ProductInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Temporal(TemporalType.DATE)
    private java.util.Calendar date;

    private BigDecimal averagePrice;

    private BigDecimal mode;

    private BigDecimal lowestPrice;

    private BigDecimal highestPrice;

    protected ProductInfo() {}

    public ProductInfo(Calendar date, BigDecimal averagePrice, BigDecimal mode, BigDecimal lowestPrice, BigDecimal highestPrice) {
        this.date = date;
        this.averagePrice = averagePrice;
        this.mode = mode;
        this.lowestPrice = lowestPrice;
        this.highestPrice = highestPrice;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public BigDecimal getAveragePrice() {
        return averagePrice;
    }

    public void setAveragePrice(BigDecimal averagePrice) {
        this.averagePrice = averagePrice;
    }

    public BigDecimal getMode() {
        return mode;
    }

    public void setMode(BigDecimal mode) {
        this.mode = mode;
    }

    public BigDecimal getLowestPrice() {
        return lowestPrice;
    }

    public void setLowestPrice(BigDecimal lowestPrice) {
        this.lowestPrice = lowestPrice;
    }

    public BigDecimal getHighestPrice() {
        return highestPrice;
    }

    public void setHighestPrice(BigDecimal highestPrice) {
        this.highestPrice = highestPrice;
    }
}
