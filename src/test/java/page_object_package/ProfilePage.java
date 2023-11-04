package page_object_package;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProfilePage {

    private By profile = By.xpath(".//main/div/nav/ul/li/a[text()='Профиль']");

    private By orderList = By.xpath(".//main/div/nav/ul/li/a[text()='История заказов']");
    private By exitButton = By.xpath(".//main/div/nav/ul/li/button[text()='Выход']");
    private By headerStellarBurgers = By.xpath(".//header/nav/div/a");
    private By constructorLink = By.xpath(".//header/nav/ul/li/a/p[text()='Конструктор']");


    private WebDriver driver;


    public ProfilePage(WebDriver driver){
        this.driver = driver;
    }

    public By getProfile() {
        return profile;
    }

    public By getOrderList() {
        return orderList;
    }

    public void clickOnExitButton(){
        driver.findElement(exitButton).click();
    }

    public void clickOnStellarBurgers(){
        driver.findElement(headerStellarBurgers).click();
    }

    public void clickOnConstructorLink(){
        driver.findElement(constructorLink).click();
    }
}
