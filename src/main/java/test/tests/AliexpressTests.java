import com.ali.Repository.InfoDataRepository;
import com.ali.Repository.ProductInfoRepository;
import com.ali.model.InfoData;
import com.ali.service.ProductInfoService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import com.ali.service.AliexpressApiService;
import org.mockito.Mockito;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AliexpressTests {

    private AliexpressApiService service;
    ProductInfoService productInfoService;

    @Before
    public void setUP() {
        service = new AliexpressApiService();
        productInfoService = new ProductInfoService(service, Mockito.mock(ProductInfoRepository.class), Mockito.mock(InfoDataRepository.class));
    }

    @Test
    public void getPrices() {
        try {
            List<BigDecimal> prices = service.getProductsMaxPagePrices("ring", 2);
            Assert.assertTrue(prices.size() > 0);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }

}
