package com.ali.Controller;

import com.ali.Repository.ProductInfoRepository;
import com.ali.model.ProductInfo;
import com.ali.service.EventExecutorService;
import com.ali.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ParserController {

    private final ProductInfoRepository repository;
    private final EventExecutorService eventExecutorService;

    @Autowired
    public ParserController(ProductInfoRepository repository, EventExecutorService eventExecutorService) {
        this.repository = repository;
        this.eventExecutorService = eventExecutorService;
    }

    @RequestMapping(value = "api/aliexpress",method = RequestMethod.GET)
    List<ProductInfo> getPrices() {
        return repository.findAll();
    }

    @RequestMapping(value = "api/aliexpress", method = RequestMethod.POST)
    public HttpStatus addEvent(@RequestParam String name) {
        if (repository.findByName(name) != null) {
            return HttpStatus.CONFLICT;
        }
        eventExecutorService.addTask(name);
        return HttpStatus.ACCEPTED;
    }

}
