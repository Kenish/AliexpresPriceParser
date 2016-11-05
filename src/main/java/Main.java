import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.math.BigDecimal;
import java.math.BigInteger;

public class Main {
    public static void main(String args[]){
        Document doc = null;
        Elements prices = null;
        AliexpressStringComposer aliUrl = new AliexpressStringComposer(3,"smok micro one");
        try {
             doc = Jsoup.connect(aliUrl.toString()).get();
             prices = doc.body().select("span[class=value]");
        }catch (Exception ex){

        }
        System.out.println(doc.title());
        prices.forEach(p->System.out.println(p.text()));
    }
    private static BigDecimal TransformToInt(String text){
        return new BigDecimal(text.substring(4,9));
    }
}
