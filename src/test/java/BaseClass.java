import io.qameta.allure.Step;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import page_object_package.ApiEndpoint;
import page_object_package.Browser;

import java.io.File;

import static page_object_package.Constants.*;

public class BaseClass {
    WebDriver driver;
    @Before

     public void setUp() {
       RestAssured.baseURI = ApiEndpoint.BASE_ADDRESS;
    }

    @Step("Сетап браузера")
    @Before
    public void setUpBrowser() {
        String browserType = Browser.BROWSER;
        if (browserType.equals("Yandex")) {
            setUpYandex();
        } else {
            setUpChrome();
        }
    }

    @Step("Сетап браузера Хром")
    public void setUpChrome() {
        System.setProperty("webdriver.http.factory", "jdk-http-client");
        ChromeDriverService service = new ChromeDriverService.Builder()
                .usingDriverExecutable(new File(CHROME_TEST_CHROMEDRIVER))
                .build();

        ChromeOptions options = new ChromeOptions()
                .setBinary(CHROME_TEST_BROWSER);

        driver = new ChromeDriver(service, options);
    }

    @Step("Сетап браузера Яндекс")
    public void setUpYandex() {
        System.setProperty("webdriver.http.factory", "jdk-http-client");
        ChromeDriverService service = new ChromeDriverService.Builder()
                .usingDriverExecutable(new File(YANDEX_BROWSER_CHROMEDRIVER))
                .build();
        ChromeOptions options = new ChromeOptions()
                .setBinary(YANDEX_BROWSER_PATH);
        driver = new ChromeDriver(service, options);
    }

    @After
    public void teardown() {
        // Закрываем браузер
        driver.quit();
    }
}
