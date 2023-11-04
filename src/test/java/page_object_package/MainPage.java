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

    private By bunsLink = By.xpath(".//div/main/section/div/div/span[text()='Булки']");
    private By saucesLink = By.xpath(".//div/main/section/div/div/span[text()='Соусы']");
    private By fillingsLink = By.xpath(".//div/main/section/div/div/span[text()='Начинки']");

    private By saucesTitle = By.xpath(".//div/main/section/div/h2[text()='Соусы']");
    private By fillingsTitle = By.xpath(".//div/main/section/div/h2[text()='Начинки']");
    private By bunsTitle = By.xpath(".//div/main/section/div/h2[text()='Булки']");

    private By meteorBeef = By.xpath(".//div/main/section[1]/div[2]/ul[3]/a[2]/p");

    public MainPage(WebDriver driver){
        this.driver = driver;
    }
    public void clickOnPersonalCabinet() {
        driver.findElement(personalCabinetLink).click(); //клик на кнопку входа в личный кабинет в заголовке
    }

    public void clickToAccountButton() {
        driver.findElement(toAccountButton).click(); //клик на кнопку входа в аккаунт на странице
    }

    public void clickToBuns() {
        driver.findElement(bunsLink).click(); //клик на кнопку
    }


    public void clickToSauces() {
        driver.findElement(saucesLink).click();

    }

    public By getSaucesTitle() {
        return saucesTitle;
    }

    public By getFillingsTitle() {
        return fillingsTitle;
    }

    public By getBunsTitle() {
        return bunsTitle;
    }

    public void clickToFillings() {
        driver.findElement(fillingsLink).click(); //клик на кнопку
     //   System.out.println(driver.findElement(fillingsLink).getText());
    }

    public By getMeteorBeef() {
        return meteorBeef;
    }
    public void clickOnMeteorBeef(){
        driver.findElement(meteorBeef).click();
    }
}
