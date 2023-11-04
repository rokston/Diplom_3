package page_object_package;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class LoginPage {
    private WebDriver driver;
    private By entryTitle = By.xpath(".//div[@class='Auth_login__3hAey']/h2[text()='Вход']");

    private By fieldEmail = By.xpath(".//div/main/div/form/fieldset[1]/div/div/input");
    private By fieldPassword = By.xpath(".//div/main/div/form/fieldset[2]/div/div/input");
    private By buttonLogin = By.xpath(".//form/button[text()='Войти']");


    public LoginPage(WebDriver driver){
        this.driver = driver;
    }
    public void findEntryTitle(){
       String temp = driver.findElement(entryTitle).getText();
       System.out.println(temp);
    }

    public void fillInLoginForm(String email, String password){
        driver.findElement(fieldEmail).sendKeys(email);
        driver.findElement(fieldPassword).sendKeys(password);
    }

    public void clickOnEntryButton(){
        WebElement element = driver.findElement(buttonLogin);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        driver.findElement(buttonLogin).click();
    }

    public By getEntryTitle() {
        return entryTitle;
    }

}
