package page_object_package;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MainPageAuth {

    private By buttonOrder = By.xpath(".//div/button[text()='Оформить заказ']");
    //ссылка на Личный кабинет в хедере
    private By personalCabinetLink = By.xpath(".//div/header/nav/a");

    private WebDriver driver;


    public MainPageAuth(WebDriver driver){
        this.driver = driver;
    }

    public By getButtonOrder() {
        return buttonOrder;
    }

    public void scrollToOrderButton() {
        WebElement element = driver.findElement(buttonOrder);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
    }

    public void clickOnPersonalCabinetLink(){
        driver.findElement(personalCabinetLink).click();
    }


}
