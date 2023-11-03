package page_object_package;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class MainPage {
    private WebDriver driver;

    //ссылка на Личный кабинет в хедере
    private By personalCabinetLink = By.xpath("//*[@id='root']/div/header/nav/a");
    //кнопка входа в аккаунт на странице
    private By toAccountButton = By.xpath("//*[@id='root']/div/main/section[2]/div/button");


    public MainPage(WebDriver driver){
        this.driver = driver;
    }
    public void clickOnPersonalCabinet() {
        driver.findElement(personalCabinetLink).click(); //клик на кнопку входа в личный кабинет в заголовке
    }

    public void clickToAccountButton() {
        driver.findElement(toAccountButton).click(); //клик на кнопку входа в аккаунт на странице
    }

}
