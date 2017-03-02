import org.jsoup.select.Elements;
import org.junit.Assert;
import org.junit.Test;
import service.AliexpressApiService;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
public class AliexpressTests {
    private AliexpressApiService service = new AliexpressApiService();
    @Test
   public void getPrices(){
        try {
            List<BigDecimal> elementsList = service.getProductsMaxPagePrices("rings",2);
            Assert.assertTrue(elementsList.size()>0);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }
    @Test
    public void ConvertPrices(){
        List<String> strings = new ArrayList<>(Collections.singletonList("US $0.65 US $1.78 US $1.62 US $1.25 US $2.35 US $2.86 US $0.89 US $0.94 US $0.89 US $3.53 US $8.08 US $1.00 US $1.99 US $4.19 US $0.56 US $0.65 US $0.62 - 0.64 US $6.93 US $3.06 US $2.21 US $2.94 US $1.58 US $1.33 US $2.07 US $2.98 US $2.06 US $0.59 US $0.89 US $4.19 US $1.61 US $1.98 US $0.38 US $56.99 US $1.65 - 1.85 US $1.55 US $0.71 US $0.38 US $1.12 US $0.37 US $3.70 US $1.99 US $1.97 US $1.99 US $0.48END\n"));
        service.converToBigDecimalPrices(strings).forEach(System.out::println);

    }
}
