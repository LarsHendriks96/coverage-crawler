import com.larshen.coverage.crawler.CoverageCrawler;
import com.larshen.coverage.crawler.internal.dictionary.Pattern;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

public class CrawlerTest {

    private final static CoverageCrawler crawler = new CoverageCrawler();


    @AfterEach
    public void cleanUp() {
        crawler.reset();
    }

    @Test
    public void testDivider() {
        //TODO: support primitives
//        crawler.forClass(Divider.class)
//                .withSpecificThrowable("divide", ArithmeticException.class)
//                .crawl();
    }

    @Test
    public void testProduct() {
        crawler.forClass(Product.class)
                .hasPattern(Pattern.BUILDER)
                .withSpecificFieldParameter("name", "hello")
                .withSpecificFieldParameter(Product.Builder.class, "quantity", 50.0D)
                .withSpecificMethodParameter(Product.Builder.class, "quantity", 50.0D)
                .crawl();
    }

    @Test
    public void testVehicle() {
//        crawler.forClass(Vehicle.class)
//                .crawl();
    }
}
