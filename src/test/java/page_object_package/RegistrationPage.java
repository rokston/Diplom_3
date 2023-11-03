package page_object_package;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class RegistrationPage {
    private WebDriver driver;

    //заголовок "Регистрация" над формой
    private By registrationTitle = By.xpath(".//div[@class='Auth_login__3hAey']/h2[text()='Регистрация']");

   // private By fieldName = By.xpath(".//div/input[@name = 'name']");
   private By fieldName = By.xpath(".//div/input[@name = 'name']");
   private By fieldEmail = By.xpath(".//div/main/div/form/fieldset[2]/div/div/input");


    public RegistrationPage(WebDriver driver){
        this.driver = driver;
    }

    public void fillInRegistrationForm(String name, String email){
        driver.findElement(fieldName).sendKeys(name);
        driver.findElement(fieldEmail).sendKeys(email);

    }


}
