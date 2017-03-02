package com.ali.Controller;

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
    private final ServiceApi api;

    @Autowired
    public ParserController(ServiceApi api) {
        this.api = api;
    }

    @RequestMapping(value = "api/aliexpress",method = RequestMethod.GET)
    List<BigDecimal> getPrices(){
        try {
            return api.getProductsPrices("smok");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
