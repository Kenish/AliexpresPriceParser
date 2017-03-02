package service;

import org.jsoup.select.Elements;
import org.omg.PortableServer.LIFESPAN_POLICY_ID;

import javax.swing.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public interface ServiceApi {
    List<BigDecimal> getProductsPrices(String name) throws IOException, InterruptedException;
    List<BigDecimal> getProductsMaxPagePrices(String name, int page) throws IOException, InterruptedException;
    List<BigDecimal> getProductsPricesInRange(String name, BigDecimal min, BigDecimal max);

}
