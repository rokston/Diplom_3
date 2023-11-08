import io.qameta.allure.Step;
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

import static java.util.function.Predicate.isEqual;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.junit.Assert.assertEquals;
import static page_object_package.Constants.*;
public class LoginTest extends BaseClass {

    private String name = "Daria"; //данные тестового пользователя
    private String email = "dodo112@ya.ru";
    private String password = "пякпяк111";

    @Step("Регистрируемся как пользователь")
    public void registerUser(WebDriver driver){
        driver.get(REGISTRATION_PAGE_URL);
        RegistrationPage objRegPage = new RegistrationPage(driver);
        new WebDriverWait(driver, Duration.ofSeconds(10)).
                until(ExpectedConditions.visibilityOfElementLocated(objRegPage.getButtonRegister()));
        objRegPage.fillInRegistrationForm(name, email, password);
        objRegPage.clickOnRegisterButton();
        }

    @Test
    public void loginFromMainPageAccountButtonTest(){//логин через кнопку Войти в аккаунт
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
        MatcherAssert.assertThat(tempString, startsWith("Оформить заказ"));//логин успешен, если нашли эту кнопку

    }

    @Test
    public void loginFromMainPagePersonalCabinetTest(){//логин через Персональный кабинет

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
        MatcherAssert.assertThat(tempString, startsWith("Оформить заказ"));//логин успешен, если нашли эту кнопку

    }



    @Test
    public void loginFromRegistrationPageLinkTest(){//логин через ссылку на странице регистрации

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
        MatcherAssert.assertThat(tempString, startsWith("Оформить заказ"));//логин успешен, если нашли эту кнопку

    }

    @Test
    public void loginFromForgotPasswordPageLinkTest(){//логин успешен по ссылке со страницы забытого пароля
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
        MatcherAssert.assertThat(tempString, startsWith("Оформить заказ"));//логин успешен, если нашли эту кнопку

    }


    @Step("Авторизация пользователя с целью получения токена")
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
    @Step("Удаление пользователя с токеном")
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

}
