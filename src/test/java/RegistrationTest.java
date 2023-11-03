import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import page_object_package.LoginPage;
import page_object_package.MainPage;
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
import page_object_package.RegistrationPage;

import java.io.File;
import java.time.Duration;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static page_object_package.Constants.*;


public class RegistrationTest {
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

    /*@Test
    public void clickOnPersonalCabinet(){
       //setUpChrome();
        setUpYandex();
        driver.get(MAIN_PAGE_URL);
        MainPage objMainPage = new MainPage(driver);
        objMainPage.clickOnPersonalCabinet();
       // objMainPage.clickToAccountButton();
    }*/

  /*  @Test
    public void openLoginPage(){
        setUpYandex();
        driver.get(LOGIN_PAGE_URL);
        LoginPage objLoginPage =new LoginPage(driver);
        objLoginPage.findEntryTitle();
    }*/

    @Test
    public void openRegistrationPage(){
        setUpYandex();
        driver.get(REGISTRATION_PAGE_URL);
        RegistrationPage objRegPage = new RegistrationPage(driver);
        objRegPage.fillInRegistrationForm("Daria", "dodo@ya.ru");
        //objRegPage.
    }

 /*   @After
    public void teardown() {
        // Закрываем браузер
        driver.quit();
    }*/

}
