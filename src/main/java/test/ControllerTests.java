package test;

import com.ali.Main;
import com.ali.Repository.InfoDataRepository;
import com.ali.Repository.ProductInfoRepository;
import com.ali.model.InfoData;
import com.ali.model.ProductInfo;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Main.class)
@DataJpaTest
public class ControllerTests {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Autowired
    private InfoDataRepository infoDataRepository;

    @Test
    public void findByNameTest() {
        entityManager.persist(new ProductInfo("smok"));
        ProductInfo productInfo = productInfoRepository.findByName("smok");
        assertThat(productInfo.getName()).isEqualTo("smok");
    }

    @Test
    public void infoDataTest() {
        List<BigDecimal> prices = new ArrayList<>(Arrays.asList(new BigDecimal("2.00"), new BigDecimal("3.00"), new BigDecimal("5.55")));
        entityManager.persist(InfoData.computePrices(prices));
        entityManager.persist(new ProductInfo("smok"));
        ProductInfo productInfo = productInfoRepository.findByName("smok");
        productInfo.addInfoData(infoDataRepository.findOne(1L));
        entityManager.persist(productInfo);
        assertThat(productInfoRepository.findByName("smok").getInfoData()).size().isEqualTo(1);
    }

}
