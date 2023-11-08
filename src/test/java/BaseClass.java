import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.rules.ExternalResource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import page_object_package.ApiEndpoint;

import java.io.File;
import java.time.Duration;

public class BaseClass extends ExternalResource {
    protected static WebDriver driver;
    WebDriverManager WebDriverManager;
    @Before
    public void setUp() {
        RestAssured.baseURI = ApiEndpoint.BASE_ADDRESS;
    }

    @Override
    @Before
    @Step("Сетап браузера")
    public void before() throws Throwable {
        System.setProperty("webdriver.http.factory", "jdk-http-client");

        if ("yandex".equals(System.getProperty("browser")))
            setUpYandex();
        else
            setUpChrome();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Step("Сетап браузера Хром")
    public void setUpChrome() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @Step("Сетап браузера Яндекс")
    public void setUpYandex() {
        ChromeDriverService service = new ChromeDriverService.Builder()
                .usingDriverExecutable(new File(EnvConfig.YANDEX_DRIVER))
                .build();
        ChromeOptions options = new ChromeOptions()
                .setBinary(EnvConfig.YANDEX_BINARY);

        driver = new ChromeDriver(service, options);
    }

    @After
    @Step("Закрыть браузер")
    public void teardown() {
        // Закрываем браузер
        driver.quit();
    }
}
