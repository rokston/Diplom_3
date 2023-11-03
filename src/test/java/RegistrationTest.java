import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import page_object_package.MainPage;
import static page_object_package.Constants.MAIN_PAGE_URL;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class RegistrationTest {

      //  System.setProperty("webdriver.http.factory", "jdk-http-client");
        ChromeDriverService service = new ChromeDriverService.Builder()
                .usingDriverExecutable(new File("C:/Praktikum/chromedriver-win64/chromedriver.exe"))
                .build();

        ChromeOptions options = new ChromeOptions()
                .setBinary("C:/Praktikum/chrome-win64/chrome.exe");

    WebDriver    driver = new ChromeDriver(service, options);

  //  WebDriver driver = new ChromeDriver();

    @Test
    public void clickOnPersonalCabinet(){
        driver.get(MAIN_PAGE_URL);
        MainPage objMainPage = new MainPage(driver);
       objMainPage.clickOnPersonalCabinet();
       // objMainPage.clickToAccountButton();
    }

    /*@After
    public void teardown() {
        // Закрываем браузер
        driver.quit();
    }
*/
}
