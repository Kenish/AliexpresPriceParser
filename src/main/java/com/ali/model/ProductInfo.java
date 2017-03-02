package com.ali.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

@Entity
public class ProductInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;

    @OneToMany
    private List<InfoData> infoDataList;

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
