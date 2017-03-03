package com.ali.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Entity
public class ProductInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "PRODUCT_INFO_ID")
    private List<InfoData> infoDataList = new ArrayList<>();

    protected ProductInfo() {}

    public ProductInfo(String name, List<InfoData> infoData) {
        this.name = name;
        this.infoDataList = infoData;
    }

    public ProductInfo(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<InfoData> getInfoData() {
        return infoDataList;
    }

    public void addInfoData(InfoData info){
        infoDataList.add(info);
    }
}
