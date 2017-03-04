import com.ali.Repository.InfoDataRepository;
import com.ali.Repository.ProductInfoRepository;
import com.ali.model.InfoData;
import com.ali.model.ProductInfo;
import com.ali.service.ProductCannotBeObtainedException;
import com.ali.service.ProductInfoService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import com.ali.service.AliexpressApiService;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class AliexpressTests {

    private AliexpressApiService service;
    private ProductInfoService productInfoService;
    private AliexpressApiService aliexpressApiServiceMock;
    private InfoDataRepository infoDataRepository;
    private ProductInfoRepository productInfoRepository;

    @Before
    public void setUP() throws IOException, InterruptedException {
        service = new AliexpressApiService();
        aliexpressApiServiceMock = Mockito.mock(AliexpressApiService.class);
        productInfoRepository = Mockito.mock(ProductInfoRepository.class);
        infoDataRepository = Mockito.mock(InfoDataRepository.class);
        Mockito.when(productInfoRepository.findByName(Mockito.anyString())).thenReturn(new ProductInfo("test"));
        Mockito.when(aliexpressApiServiceMock.getProductsPrices(Mockito.anyString())).thenReturn(new ArrayList<>(Arrays.asList(new BigDecimal("1.00"), new BigDecimal("2.00"))));
        productInfoService = new ProductInfoService(aliexpressApiServiceMock, productInfoRepository, infoDataRepository);
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

    @Test
    public void productInfoTask() {
        productInfoService.productInfoTask("test");
        Mockito.verify(infoDataRepository, Mockito.times(1)).save(Mockito.any(InfoData.class));
        Mockito.verify(productInfoRepository, Mockito.times(1)).findByName("test");
    }

    @Test(expected = ProductCannotBeObtainedException.class)
    public void productInfoTaskOnExceptionShouldRethrow() throws IOException, InterruptedException {
        aliexpressApiServiceMock = Mockito.mock(AliexpressApiService.class);
        productInfoService = new ProductInfoService(aliexpressApiServiceMock, productInfoRepository, infoDataRepository);
        Mockito.when(aliexpressApiServiceMock.getProductsPrices(Mockito.anyString())).thenThrow(new IOException());
        productInfoService.productInfoTask("test");
    }

    @Test
    public void PriceComputing() {
        List<BigDecimal> prices = new ArrayList<>(Arrays.asList(new BigDecimal("1.00"), new BigDecimal("1.50"), new BigDecimal(("2.00")), new BigDecimal(("3.00"))));
        InfoData data = InfoData.computePrices(prices);
        Assert.assertEquals(new BigDecimal("1.88"), data.getAverage());
        Assert.assertEquals(data.getMax(), new BigDecimal("3.00"));
        Assert.assertEquals(data.getMin(), new BigDecimal("1.00"));
    }

}
