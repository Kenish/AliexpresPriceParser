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
            List<BigDecimal> prices = getPricesSafely(name);

            ProductInfo productInfo = productInfoRepository.findByName(name);
            InfoData data = InfoData.computePrices(prices);
            infoDataRepository.save(data);
            productInfo.addInfoData(data);
            productInfoRepository.save(productInfo);

        }).run();
    }

    private List<BigDecimal> getPricesSafely(String name) {
        List<BigDecimal> prices;
        try {
            prices = aliexpressApiService.getProductsPrices(name);
            if (prices.size() == 0) {
                prices.add(new BigDecimal("0"));

            }
        } catch (IOException | InterruptedException e) {
            throw new ProductCannotBeObtainedException();
        }
        return prices;
    }



}
