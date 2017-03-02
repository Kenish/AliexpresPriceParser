package service;

import org.jsoup.select.Elements;
import org.omg.PortableServer.LIFESPAN_POLICY_ID;

import javax.swing.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public interface ServiceApi {
    List<Elements> getProducts(String name) throws IOException;
    List<Elements> getProductsMaxPage(String name, int page);
    List<Elements> getProductsPriceRange(String name, BigDecimal min, BigDecimal max);

}
