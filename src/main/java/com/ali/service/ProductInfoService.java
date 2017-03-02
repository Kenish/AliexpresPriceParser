package com.ali.service;

import com.ali.Repository.InfoDataRepository;
import com.ali.Repository.ProductInfoRepository;
import com.ali.model.InfoData;
import com.ali.model.ProductInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
public class ProductInfoService {
private final AliexpressApiService aliexpressApiService;
private final ProductInfoRepository productInfoRepository;
private final InfoDataRepository infoDataRepository;

    @Autowired
    public ProductInfoService(AliexpressApiService aliexpressApiService, ProductInfoRepository repository,InfoDataRepository infoDataRepository) {
        this.aliexpressApiService = aliexpressApiService;
        this.productInfoRepository = repository;
        this.infoDataRepository = infoDataRepository;
    }

    ProductInfo AddProductInfo(String name, List<BigDecimal> prices){
        ProductInfo productInfo =productInfoRepository.findByName(name);
        if (productInfo.equals(null)){
            productInfoRepository.save(new ProductInfo(name));
            productInfo=productInfoRepository.findByName(name);
    }
        InfoData data = computePrices(prices);
        infoDataRepository.save(data);
        productInfo.addInfoData(data);
        return productInfo;
    }

    InfoData computePrices(List<BigDecimal> prices){
        InfoData dto = new InfoData();
        BigDecimal min = prices.get(0);
        BigDecimal max = prices.get(0);
        BigDecimal sum = new BigDecimal(0);
        BigDecimal avg = new BigDecimal("0");
        BigDecimal mode = new BigDecimal("0");
        for (BigDecimal price:prices) {

        if (price.compareTo(min)==-1){
            min = price;
        }
        if (price.compareTo(max)==1){
            max=price;
        }
        sum = sum.add(price);

        }
        avg = sum.divide(new BigDecimal(prices.size()), RoundingMode.DOWN);
        return new InfoData(avg,min,max);
    }

}
