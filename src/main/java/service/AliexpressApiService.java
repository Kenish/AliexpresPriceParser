package service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class AliexpressApiService implements ServiceApi {
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
    public List<Elements> getProducts(String name) throws IOException {
        List<Elements> prices = new ArrayList<>();
        int i=1;
        Elements pricepage = getPrices(startComposing().append(addName(name)).append(choosePage(i)));
        prices.add(pricepage);
/*        while (pricepage.size()>0) {
            i++;
            prices.add(pricepage);
            pricepage= getPrices(startComposing().append(addName(name)).append(choosePage(i)));
        }*/
        return prices;
    }

    @Override
    public List<Elements> getProductsMaxPage(String name, int page) {
        return null;
    }

    @Override
    public List<Elements> getProductsPriceRange(String name, BigDecimal min, BigDecimal max) {
        return null;
    }
}
