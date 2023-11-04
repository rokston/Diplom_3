import org.hamcrest.MatcherAssert;
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
        setUpChrome();
        driver.get(MAIN_PAGE_URL);
        MainPage objMainPage = new MainPage(driver);
        //  objMainPage.clickToFillings();
       //String tempString =
        boolean isVisible = driver.findElement(objMainPage.getMeteorBeef()).isDisplayed();
        //MatcherAssert.assertThat(tempString, startsWith("Говяжий"));
        System.out.println(isVisible);

        // objMainPage.clickOnMeteorBeef();
     /*   objMainPage.clickToBuns();
        tempString =
                driver.findElement(objMainPage.getBunsTitle()).getText();
        MatcherAssert.assertThat(tempString, startsWith("Булки"));

        //objMainPage.clickToSauces();
        tempString =
                driver.findElement(objMainPage.getSaucesTitle() ).getText();
        MatcherAssert.assertThat(tempString, startsWith("Соусы"));

*/

    }


    //@After
    public void teardown() {
        // Закрываем браузер
        driver.quit();
    }
}
