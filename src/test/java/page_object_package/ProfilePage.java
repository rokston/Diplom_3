package page_object_package;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProfilePage {
    //текст Профиль на странице как маркер открытия страницы
    private By profile = By.xpath(".//main/div/nav/ul/li/a[text()='Профиль']");
    //линк История заказов
    private By orderList = By.xpath(".//main/div/nav/ul/li/a[text()='История заказов']");
    //линк Выход
    private By exitButton = By.xpath(".//main/div/nav/ul/li/button[text()='Выход']");

    //заголовок Stellar Burgers
    private By headerStellarBurgers = By.xpath(".//header/nav/div/a");
    //линк в заголовке на Конструктор
    private By constructorLink = By.xpath(".//header/nav/ul/li/a/p[text()='Конструктор']");
    private WebDriver driver;

    public ProfilePage(WebDriver driver) {
        this.driver = driver;
    }

    public By getProfile() {
        return profile;
    }

    public By getOrderList() {
        return orderList;
    }

    public void clickOnExitButton() {
        driver.findElement(exitButton).click();
    } //клик на Выход

    public void clickOnStellarBurgers() {
        driver.findElement(headerStellarBurgers).click();
    } //клик на заголовок Stellar Burgers

    //клик на Конструктор
    public void clickOnConstructorLink() {
        driver.findElement(constructorLink).click();
    }
}
