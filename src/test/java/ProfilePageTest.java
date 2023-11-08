import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import page_object_package.*;
import java.time.Duration;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.startsWith;
import static page_object_package.Constants.*;

public class ProfilePageTest extends BaseClass{
    private String name = "Daria";//данные тестового пользователя
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
    public void loginPersonalCabinetTest(){//логин в Персональный кабинет, страница профиля и выход
        registerUser(driver);
        driver.get(MAIN_PAGE_URL);
        MainPage objMainPage =  new MainPage(driver);

        objMainPage.clickOnPersonalCabinet();//клик на Персональный кабинет
        LoginPage objLoginPage = new LoginPage(driver);
        new WebDriverWait(driver, Duration.ofSeconds(10)).
                until(ExpectedConditions.visibilityOfElementLocated(objLoginPage.getEntryTitle()));
        objLoginPage.findEntryTitle();
        objLoginPage.fillInLoginForm(email, password);//заполнение формы
        objLoginPage.clickOnEntryButton();//клик на кнопку входа


        MainPageAuth objMainAuth = new MainPageAuth(driver);
        new WebDriverWait(driver, Duration.ofSeconds(10)).//логин успешен, если нашли эту кнопку
                until(ExpectedConditions.visibilityOfElementLocated(objMainAuth.getButtonOrder()));

        objMainAuth.clickOnPersonalCabinetLink();//клик на линк Персональный кабинет

        ProfilePage objProfilePage = new ProfilePage(driver);
        new WebDriverWait(driver, Duration.ofSeconds(10)).//ждем открытия страницы Профиля
                until(ExpectedConditions.visibilityOfElementLocated(objProfilePage.getProfile()));
        String tempString =
                driver.findElement(objProfilePage.getProfile()).getText();
        MatcherAssert.assertThat(tempString, startsWith("Профиль"));//вход успешен, если нашли эту кнопку
        tempString =
                driver.findElement(objProfilePage.getOrderList()).getText();
        MatcherAssert.assertThat(tempString, startsWith("История заказов"));//вход успешен, если нашли эту кнопку
        objProfilePage.clickOnExitButton();//жмем на кнопку Выход
        objLoginPage = new LoginPage(driver);
        new WebDriverWait(driver, Duration.ofSeconds(10)).
                until(ExpectedConditions.visibilityOfElementLocated(objLoginPage.getEntryTitle()));
        tempString =
                driver.findElement(objLoginPage.getEntryTitle()).getText();
        MatcherAssert.assertThat(tempString, startsWith("Вход"));//выход успешен, если нашли эту кнопку

    }

    @Test
    public void fromPersonalCabinetToMainPageTest(){
        registerUser(driver);
        driver.get(MAIN_PAGE_URL);
        MainPage objMainPage =  new MainPage(driver);

        objMainPage.clickOnPersonalCabinet();//клик на линк Персональный кабинет
        LoginPage objLoginPage = new LoginPage(driver);
        new WebDriverWait(driver, Duration.ofSeconds(10)).
                until(ExpectedConditions.visibilityOfElementLocated(objLoginPage.getEntryTitle()));
        objLoginPage.findEntryTitle();
        objLoginPage.fillInLoginForm(email, password);
        objLoginPage.clickOnEntryButton();//заполнение формы и нажатие на кнопку входа


        MainPageAuth objMainAuth = new MainPageAuth(driver);
        new WebDriverWait(driver, Duration.ofSeconds(10)).//логин успешен, если нашли эту кнопку
                until(ExpectedConditions.visibilityOfElementLocated(objMainAuth.getButtonOrder()));

        objMainAuth.clickOnPersonalCabinetLink();//переход в Персональный кабинет

        ProfilePage objProfilePage = new ProfilePage(driver);
        new WebDriverWait(driver, Duration.ofSeconds(10)).//успешен, если нашли эту кнопку
                until(ExpectedConditions.visibilityOfElementLocated(objProfilePage.getProfile()));

        objProfilePage.clickOnStellarBurgers(); //клик на Stellar Burgers в заголовке
        objMainAuth = new MainPageAuth(driver);
        new WebDriverWait(driver, Duration.ofSeconds(10)).//успешно, если нашли эту кнопку
                until(ExpectedConditions.visibilityOfElementLocated(objMainAuth.getButtonOrder()));
        objMainAuth.scrollToOrderButton();
        String tempString =
                driver.findElement(objMainAuth.getButtonOrder()).getText();
        MatcherAssert.assertThat(tempString, startsWith("Оформить заказ"));


    }

    @Test
    public void fromPersonalCabinetToConstructorTest(){
        registerUser(driver);
        driver.get(MAIN_PAGE_URL);
        MainPage objMainPage =  new MainPage(driver);

        objMainPage.clickOnPersonalCabinet();//клик на линк Персональный кабинет
        LoginPage objLoginPage = new LoginPage(driver);
        new WebDriverWait(driver, Duration.ofSeconds(10)).
                until(ExpectedConditions.visibilityOfElementLocated(objLoginPage.getEntryTitle()));
        objLoginPage.findEntryTitle();
        objLoginPage.fillInLoginForm(email, password);
        objLoginPage.clickOnEntryButton();//заполнение формы и нажатие на кнопку входа


        MainPageAuth objMainAuth = new MainPageAuth(driver);
        new WebDriverWait(driver, Duration.ofSeconds(10)).//логин успешен, если нашли эту кнопку
                until(ExpectedConditions.visibilityOfElementLocated(objMainAuth.getButtonOrder()));

        objMainAuth.clickOnPersonalCabinetLink();//переход в Персональный кабинет

        ProfilePage objProfilePage = new ProfilePage(driver);
        new WebDriverWait(driver, Duration.ofSeconds(10)).//успешен, если нашли эту кнопку
                until(ExpectedConditions.visibilityOfElementLocated(objProfilePage.getProfile()));

        objProfilePage.clickOnConstructorLink();// клик на линк Конструктор
        objMainAuth = new MainPageAuth(driver);
        new WebDriverWait(driver, Duration.ofSeconds(10)).
                until(ExpectedConditions.visibilityOfElementLocated(objMainAuth.getButtonOrder()));
        objMainAuth.scrollToOrderButton();
        String tempString =
                driver.findElement(objMainAuth.getButtonOrder()).getText();
        MatcherAssert.assertThat(tempString, startsWith("Оформить заказ")); //успешен, если нашли эту кнопку


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
