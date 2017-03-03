package com.ali.Controller;

import com.ali.Repository.ProductInfoRepository;
import com.ali.model.InfoData;
import com.ali.model.ProductInfo;
import com.ali.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.ali.service.ServiceApi;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@RestController
public class ParserController {
    private final ProductInfoService api;
    @Autowired
    private ProductInfoRepository repository;

    @Autowired
    public ParserController(ProductInfoService api) {
        this.api = api;
    }

    @RequestMapping(value = "api/aliexpress",method = RequestMethod.GET)
    List<InfoData> getPrices() {
        repository.save(new ProductInfo("smok"));
        api.productInfoTask("smok");
        return repository.findByName("smok").getInfoData();
    }
}
