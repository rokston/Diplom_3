package page_object_package;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProfilePage {

    private By profile = By.xpath(".//main/div/nav/ul/li/a[text()='Профиль']");

    private By orderList = By.xpath(".//main/div/nav/ul/li/a[text()='История заказов']");


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
}
