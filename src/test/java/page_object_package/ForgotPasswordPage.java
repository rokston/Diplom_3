package page_object_package;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ForgotPasswordPage {
    private WebDriver driver;
    private By entryLink = By.className("Auth_link__1fOlj"); //ссылка Войти под формой
    public ForgotPasswordPage(WebDriver driver) {
        this.driver = driver;
    }
    public void clickOnEntryLink() { //клик на линк Войти под формой
        driver.findElement(entryLink).click();
    }
}
