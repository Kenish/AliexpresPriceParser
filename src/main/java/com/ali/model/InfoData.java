package com.ali.model;

import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.math.BigDecimal;
import java.util.Calendar;

@Entity
public class InfoData {
    @Id
    @GeneratedValue
    private long id;
    @Temporal(TemporalType.DATE)
    private java.util.Calendar date;

    private BigDecimal average;

    private BigDecimal min;

    private BigDecimal max;

    public InfoData() {
    }

    public InfoData(Calendar date, BigDecimal average, BigDecimal min, BigDecimal max) {
        this.date = date;
        this.average = average;
        this.min = min;
        this.max = max;
    }

    public InfoData(BigDecimal average, BigDecimal min, BigDecimal max) {
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

    public void setMax(BigDecimal max) {
        this.max = max;
    }
}
