import org.junit.Assert;
import org.junit.Test;
import com.ali.service.AliexpressApiService;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class AliexpressTests {
    private AliexpressApiService service = new AliexpressApiService();
    @Test
   public void getPrices(){
        try {
            List<BigDecimal> prices = service.getProductsMaxPagePrices("rings",2);
            Assert.assertTrue(prices.size()>0);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }
}
