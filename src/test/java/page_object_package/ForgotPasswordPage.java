package page_object_package;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ForgotPasswordPage {
    private WebDriver driver;


    private By entryLink = By.className("Auth_link__1fOlj");


    public ForgotPasswordPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickOnEntryLink() {
        driver.findElement(entryLink).click();
    }
}
