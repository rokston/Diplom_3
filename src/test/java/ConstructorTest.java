import io.qameta.allure.Step;
import org.hamcrest.MatcherAssert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import page_object_package.*;
import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.File;
import java.time.Duration;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.startsWith;
import static page_object_package.Constants.*;


public class ConstructorTest extends BaseClass{
     String testStr;

    @Test
    public void ConstructorTestBuns(){
        driver.get(MAIN_PAGE_URL);//открыть главную страницу
        MainPage objMainPage = new MainPage(driver);
        objMainPage.clickToFillings();//клик на линк Начинки

        objMainPage.clickToBuns();//клик на линк Булки
        new WebDriverWait(driver, Duration.ofSeconds(10)). //ждем, что стиль на странице поменяется
           until(ExpectedConditions.attributeContains(objMainPage.getStyleBuns(), "class", "current"));
        //если драйвер не дождется изменения стиля, тест упадет с ошибкой
        testStr = driver.findElement(objMainPage.getStyleBuns()).getAttribute("class");
        MatcherAssert.assertThat(testStr, containsString("current"));
        //проверяем ассертом то же самое
    }

    @Test
    public void ConstructorTestFillings(){
        driver.get(MAIN_PAGE_URL);//открыть главную страницу
        MainPage objMainPage = new MainPage(driver);
        objMainPage.clickToFillings();//клик на линк Начинки
        new WebDriverWait(driver, Duration.ofSeconds(10)). //ждем, что стиль на странице поменяется
                until(ExpectedConditions.attributeContains(objMainPage.getStyleFillings(), "class", "current"));
        //если драйвер не дождется изменения стиля, тест упадет с ошибкой
        testStr = driver.findElement(objMainPage.getStyleFillings()).getAttribute("class");
        MatcherAssert.assertThat(testStr, containsString("current"));
        //проверяем ассертом то же самое

    }

    @Test
    public void ConstructorTestSauces(){
        driver.get(MAIN_PAGE_URL);//открыть главную страницу
        MainPage objMainPage = new MainPage(driver);
        objMainPage.clickToSauces(); //клик на линк Соусы
        new WebDriverWait(driver, Duration.ofSeconds(10)). //ждем, что стиль на странице поменяется
                until(ExpectedConditions.attributeContains(objMainPage.getStyleSauces(), "class", "current"));
        //если драйвер не дождется изменения стиля, тест упадет с ошибкой
        testStr = driver.findElement(objMainPage.getStyleSauces()).getAttribute("class");
        MatcherAssert.assertThat(testStr, containsString("current"));
        //проверяем ассертом то же самое

    }

}
