import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.ArrayList;

public class SeleniumTests {

    private WebDriver driver;
    private StringBuffer errors = new StringBuffer();

    @Before
    public void setup() throws Exception {
        System.setProperty(
                "webdriver.chrome.driver",
                "chromedriver-107.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    }

    @Test
    public void isrtx4080OnAmazonTest() throws Exception {
        driver.get("https://www.amazon.pl/");
        driver.findElement(By.id("twotabsearchtextbox")).click();
        driver.findElement(By.id("twotabsearchtextbox")).sendKeys("rtx4080");
        driver.findElement(By.id("twotabsearchtextbox")).sendKeys(Keys.ENTER);
    }

    @Test
    public void addToCartBlackFridaysDealTest() throws Exception {
        driver.get("https://www.amazon.pl/");
        driver.findElement(By.id("nav_cs_gb_td_bf_dt_cr")).click();
        driver.findElement(By.xpath("//a[@href=\"https://www.amazon.pl/kindle-paperwhite-8-gb-teraz-z-wyswietlaczem-68-i-regulowanym-podswietleniem-w-cieplym-kolorze-bez-reklam/dp/B08N36XNTT?ref=dlx_black_gd_dcl_tlt_0_baab1e5b_dt_sl6_d5\"]")).click();
        driver.findElement(By.id("add-to-cart-button")).click();
        driver.findElement(By.id("nav-cart-count-container")).click();
    }

    @After
    public void teardown() throws Exception {
        driver.quit();
        String verificationErrorString = errors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }

}
