import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.omg.IOP.ExceptionDetailMessage;

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
        System.out.print(doc.title());
        prices.forEach(p->System.out.println(p.text()));
    }
}
