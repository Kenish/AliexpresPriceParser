package com.ali.service;


import com.ali.Repository.ProductInfoRepository;
import com.ali.model.ProductInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EventExecutorService {
    private final ProductInfoRepository productInfoRepository;
    private final ProductInfoService productInfoService;

    @Autowired
    public EventExecutorService(ProductInfoRepository productInfoRepository, ProductInfoService productInfoService) {
        this.productInfoRepository = productInfoRepository;
        this.productInfoService = productInfoService;
    }

    public void addTask(String productName) {
        productInfoRepository.save(new ProductInfo(productName));
    }

    @Scheduled(initialDelay = 50000, fixedDelay = 86400000)
    public void runTasks() {
        List<ProductInfo> productInfos = productInfoRepository.findAll();
        if (productInfos.size() > 0) {
            productInfos.forEach(productInfo -> productInfoService.productInfoTask(productInfo.getName()));
        }
    }
}
