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

    public LoginPage(WebDriver driver){
        this.driver = driver;
    }
    public void findEntryTitle(){
       String temp = driver.findElement(entryTitle).getText();
       System.out.println(temp);
    }
 }
