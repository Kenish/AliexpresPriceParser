package com.ali.service;

import com.ali.Repository.InfoDataRepository;
import com.ali.Repository.ProductInfoRepository;
import com.ali.model.InfoData;
import com.ali.model.ProductInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;
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


    public void productInfoTask(String name) {
        new Thread(() -> {
            List<BigDecimal> prices;
            try {
                prices = aliexpressApiService.getProductsPrices(name);
                if (prices.size() == 0) {
                    prices.add(new BigDecimal("0"));
                }
            } catch (IOException | InterruptedException e) {
                throw new ProductCannotBeObtainedException();
            }
            ProductInfo productInfo = productInfoRepository.findByName(name);
            System.out.print(productInfo.getId());
            InfoData data = computePrices(prices);
            infoDataRepository.save(data);
            productInfo.addInfoData(data);
            productInfoRepository.save(productInfo);

        }).run();
    }


    private InfoData computePrices(List<BigDecimal> prices) {
        BigDecimal min = prices.get(0);
        BigDecimal max = prices.get(0);
        BigDecimal sum = new BigDecimal(0);
        new BigDecimal("0");
        for (BigDecimal price:prices) {

        if (price.compareTo(min)==-1){
            min = price;
        }
        if (price.compareTo(max)==1){
            max=price;
        }
        sum = sum.add(price);

        }
        BigDecimal avg = sum.divide(new BigDecimal(prices.size()), RoundingMode.DOWN);
        return new InfoData(Calendar.getInstance(), avg, min, max);
    }

}
