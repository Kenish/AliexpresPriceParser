package com.ali.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class AliexpressApiService implements ServiceApi {
    private static final int MAX_QUERIES = 50;
    private static final int WAIT_TIME = 250; //ms
    public AliexpressApiService(){

    }
    private StringBuilder startComposing(){
        return new StringBuilder("https://www.aliexpress.com/wholesale?site=glo&g=y&");
    }
    private String addName(String name){
       return "SearchText="+productNameCompose(name);

    }
    private String choosePage(int page){
       return "&page="+page;
    }
    private String productNameCompose(String productName){
        productName=productName.replaceAll("\\s+","+");
        return productName;
    }
    private Elements getPrices (StringBuilder url) throws IOException {
        Document doc = Jsoup.connect(url.toString()).get();
        return doc.body().select("span[class=value]");
    }


    @Override
    public List<BigDecimal> getProductsPrices(String name) throws IOException, InterruptedException {
      return getProductsMaxPagePrices(name,MAX_QUERIES);
    }

    @Override
    public List<BigDecimal> getProductsMaxPagePrices(String name, int page) throws IOException, InterruptedException {
       return converToBigDecimalPrices(getPricesList(name,page));
    }

    @Override
    public List<BigDecimal> getProductsPricesInRange(String name, BigDecimal min, BigDecimal max) {
        return null;
    }

    private List<String> getPricesList(String name,int page) throws InterruptedException, IOException {
        List<String> prices = new ArrayList<>();
        int counter=1;
        Elements pricepage = getPrices(startComposing().append(addName(name)).append(choosePage(counter)));
        prices.add(pricepage.text());
        while (pricepage.size()>0) {
            counter++;
            prices.add(pricepage.text());
            pricepage= getPrices(startComposing().append(addName(name)).append(choosePage(counter)));
            if (counter==page){
                return prices;
            }
            Thread.sleep(WAIT_TIME);
        }
        return prices;
    }
    public List<BigDecimal> converToBigDecimalPrices(List<String> prices){
        Pattern p =Pattern.compile(("(\\d+).\\d{2}"));
        List<String> matched = new ArrayList<>();
        List<BigDecimal> decimalPrices = new ArrayList<>();
        for(String price:prices){
            Matcher m = p.matcher(price);
            while (m.find()){
                decimalPrices.add(new BigDecimal(m.group()));
            }
        }
        return decimalPrices;

    }
}
