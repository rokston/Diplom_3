import org.hamcrest.MatcherAssert;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import page_object_package.*;
import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import static io.restassured.RestAssured.given;
import java.io.File;
import java.time.Duration;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.junit.Assert.assertEquals;
import static page_object_package.Constants.*;


public class ConstructorTest {

    public void setUpBrowser(){
        String browserType = Browser.BROWSER;
        if (browserType.equals("Yandex")){
            setUpYandex();
        } else {
            setUpChrome();
        }
    }
    public void setUpChrome(){
        System.setProperty("webdriver.http.factory", "jdk-http-client");
        ChromeDriverService service = new ChromeDriverService.Builder()
                .usingDriverExecutable(new File(CHROME_TEST_CHROMEDRIVER))
                .build();

        ChromeOptions options = new ChromeOptions()
                .setBinary(CHROME_TEST_BROWSER);

        driver = new ChromeDriver(service, options);}

    public void setUpYandex(){
        System.setProperty("webdriver.http.factory", "jdk-http-client");
        ChromeDriverService service = new ChromeDriverService.Builder()
                .usingDriverExecutable(new File(YANDEX_BROWSER_CHROMEDRIVER))
                .build();
        ChromeOptions options = new ChromeOptions()
                .setBinary(YANDEX_BROWSER_PATH);
        driver = new ChromeDriver(service, options);
    }

    WebDriver driver;

    @Test
    public void ConstructorTest(){
        // setUpYandex();
        setUpBrowser();
        driver.get(MAIN_PAGE_URL);
        MainPage objMainPage = new MainPage(driver);
        objMainPage.clickToFillings();
        new WebDriverWait(driver, Duration.ofSeconds(10)).
                until(ExpectedConditions.attributeContains(By.xpath(".//div/main/section[1]/div[1]/div[3]"), "class", "current"));

        objMainPage.clickToSauces();
        new WebDriverWait(driver, Duration.ofSeconds(10)).
                until(ExpectedConditions.attributeContains(By.xpath(".//div/main/section[1]/div[1]/div[2]"), "class", "current"));

         objMainPage.clickToBuns();
         new WebDriverWait(driver, Duration.ofSeconds(10)).
           until(ExpectedConditions.attributeContains(By.xpath(".//div/main/section[1]/div[1]/div[1]"), "class", "current"));

    }

    @After
    public void teardown() {
        // Закрываем браузер
        driver.quit();
    }
}
