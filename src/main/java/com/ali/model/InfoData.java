package com.ali.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.List;

@Entity
public class InfoData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Temporal(TemporalType.DATE)
    private java.util.Calendar date;

    private BigDecimal average;

    private BigDecimal min;

    private BigDecimal max;

    public InfoData() {
    }

    private InfoData(Calendar date, BigDecimal average, BigDecimal min, BigDecimal max) {
        this.date = date;
        this.average = average;
        this.min = min;
        this.max = max;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public BigDecimal getAverage() {
        return average;
    }

    public void setAverage(BigDecimal average) {
        this.average = average;
    }

    public BigDecimal getMin() {
        return min;
    }

    public void setMin(BigDecimal min) {
        this.min = min;
    }


    public BigDecimal getMax() {
        return max;
    }

    @JsonIgnore
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setMax(BigDecimal max) {
        this.max = max;
    }

    public static InfoData computePrices(List<BigDecimal> prices) {
        BigDecimal min = prices.get(0);
        BigDecimal max = prices.get(0);
        BigDecimal sum = new BigDecimal(0);
        new BigDecimal("0");
        for (BigDecimal price : prices) {

            if (price.compareTo(min) == -1) {
                min = price;
            }
            if (price.compareTo(max) == 1) {
                max = price;
            }
            sum = sum.add(price);

        }
        BigDecimal avg = sum.divide(new BigDecimal(prices.size()), RoundingMode.CEILING);
        return new InfoData(Calendar.getInstance(), avg, min, max);
    }
}
