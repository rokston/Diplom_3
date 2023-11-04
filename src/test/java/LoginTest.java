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
public class LoginTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = ApiEndpoint.BASE_ADDRESS;
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
    String name = "Daria";
    String email = "dodo112@ya.ru";
    String password = "пякпяк111";

    public void registerUser(WebDriver driver){
        driver.get(REGISTRATION_PAGE_URL);
        RegistrationPage objRegPage = new RegistrationPage(driver);
        new WebDriverWait(driver, Duration.ofSeconds(10)).
                until(ExpectedConditions.visibilityOfElementLocated(objRegPage.getButtonRegister()));
        objRegPage.fillInRegistrationForm(name, email, password);
        objRegPage.clickOnRegisterButton();
        }

    @Test
    public void loginFromMainPageAccountButtonTest(){
        setUpYandex();
        registerUser(driver);
        driver.get(MAIN_PAGE_URL);
        MainPage objMainPage =  new MainPage(driver);

        objMainPage.clickToAccountButton();
        LoginPage objLoginPage = new LoginPage(driver);
        new WebDriverWait(driver, Duration.ofSeconds(10)).
                until(ExpectedConditions.visibilityOfElementLocated(objLoginPage.getEntryTitle()));
        objLoginPage.findEntryTitle();
        objLoginPage.fillInLoginForm(email, password);
        objLoginPage.clickOnEntryButton();


        MainPageAuth objMainAuth = new MainPageAuth(driver);
        new WebDriverWait(driver, Duration.ofSeconds(10)).
                until(ExpectedConditions.visibilityOfElementLocated(objMainAuth.getButtonOrder()));
        objMainAuth.scrollToOrderButton();
        String tempString =
                driver.findElement(objMainAuth.getButtonOrder()).getText();
        MatcherAssert.assertThat(tempString, startsWith("Оформить заказ"));

    }

    @Test
    public void loginFromMainPagePersonalCabinetTest(){
        setUpYandex();
        registerUser(driver);
        driver.get(MAIN_PAGE_URL);
        MainPage objMainPage =  new MainPage(driver);

        objMainPage.clickOnPersonalCabinet();
        LoginPage objLoginPage = new LoginPage(driver);
        new WebDriverWait(driver, Duration.ofSeconds(10)).
                until(ExpectedConditions.visibilityOfElementLocated(objLoginPage.getEntryTitle()));
        objLoginPage.findEntryTitle();
        objLoginPage.fillInLoginForm(email, password);
        objLoginPage.clickOnEntryButton();


        MainPageAuth objMainAuth = new MainPageAuth(driver);
        new WebDriverWait(driver, Duration.ofSeconds(10)).
                until(ExpectedConditions.visibilityOfElementLocated(objMainAuth.getButtonOrder()));
        objMainAuth.scrollToOrderButton();
        String tempString =
                driver.findElement(objMainAuth.getButtonOrder()).getText();
        MatcherAssert.assertThat(tempString, startsWith("Оформить заказ"));

    }


    @Test
    public void loginFromRegistrationPageLinkTest(){
        setUpYandex();
        registerUser(driver);
        driver.get(MAIN_PAGE_URL);
        driver.get(REGISTRATION_PAGE_URL);
        RegistrationPage objRegPage =  new RegistrationPage(driver);

        objRegPage.clickOnEntryLinkButton();
        LoginPage objLoginPage = new LoginPage(driver);
        new WebDriverWait(driver, Duration.ofSeconds(10)).
                until(ExpectedConditions.visibilityOfElementLocated(objLoginPage.getEntryTitle()));
        objLoginPage.findEntryTitle();
        objLoginPage.fillInLoginForm(email, password);
        objLoginPage.clickOnEntryButton();


        MainPageAuth objMainAuth = new MainPageAuth(driver);
        new WebDriverWait(driver, Duration.ofSeconds(10)).
                until(ExpectedConditions.visibilityOfElementLocated(objMainAuth.getButtonOrder()));
        objMainAuth.scrollToOrderButton();
        String tempString =
                driver.findElement(objMainAuth.getButtonOrder()).getText();
        MatcherAssert.assertThat(tempString, startsWith("Оформить заказ"));

    }

    @Test
    public void loginFromForgotPasswordPageLinkTest(){
        setUpYandex();
        registerUser(driver);
        driver.get(FORGOT_PASSWORD_URL);
        ForgotPasswordPage objForgotPassPage =  new ForgotPasswordPage(driver);

        objForgotPassPage.clickOnEntryLink();
        LoginPage objLoginPage = new LoginPage(driver);
        new WebDriverWait(driver, Duration.ofSeconds(10)).
                until(ExpectedConditions.visibilityOfElementLocated(objLoginPage.getEntryTitle()));
        objLoginPage.findEntryTitle();
        objLoginPage.fillInLoginForm(email, password);
        objLoginPage.clickOnEntryButton();


        MainPageAuth objMainAuth = new MainPageAuth(driver);
        new WebDriverWait(driver, Duration.ofSeconds(10)).
                until(ExpectedConditions.visibilityOfElementLocated(objMainAuth.getButtonOrder()));
        objMainAuth.scrollToOrderButton();
        String tempString =
                driver.findElement(objMainAuth.getButtonOrder()).getText();
        MatcherAssert.assertThat(tempString, startsWith("Оформить заказ"));

    }





    public String loginUser(String email, String password){ //авторизация пользователя, с целью получения токена

        Credentials credentials = new Credentials(email, password);
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .and()
                        .body(credentials)
                        .when()
                        .post(ApiEndpoint.LOGIN_USER);
        int code = response.then().
                extract().statusCode();
        String userToken;
        if (code == 200) {
            userToken = response
                    .then().extract().body().path("accessToken");}
        else {
            userToken = null;
        }
        return userToken;
    }

    public void deleteUser(String email, String password) {
        String userToken = loginUser(email, password);
        if (userToken != null)  {
            Response response = given()
                    .header("Content-type", "application/json")
                    .header("Authorization", userToken)
                    .when()
                    .delete(ApiEndpoint.DELETE_USER);

            response.then().log().all()
                    .assertThat()
                    .statusCode(202);

        }
        else  {
            System.out.println("Cannot delete not existing user");
        }

    }

    @After
    public void userDelete(){

        deleteUser(email, password);
     }
    @After
    public void teardown() {
        // Закрываем браузер
        driver.quit();
    }

}
