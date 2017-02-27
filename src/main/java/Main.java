import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.math.BigDecimal;
import java.util.List;

public class Main {
    public static void main(String args[]){
        List<Elements> prices = null;
        AliexpressApiService aliUrl = new AliexpressApiService();
        try {
           prices = aliUrl.getProducts("smok");
        }catch (Exception ex){
        System.out.println("aa");
        }
        if (prices != null) {
            double price =prices.stream().mapToInt(p->transformToInt(p.text())).average().getAsDouble();
            String money = Double.toString(price/100);
            System.out.println(money);
        }
    }
    private static int transformToInt(String text){
        String cents = text.substring(4,6)+text.substring(7,9);
       return Integer.parseInt(cents);
    }
}
