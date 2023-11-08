package page_object_package;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;

public class LoginPage {
    private WebDriver driver;
    //надпись Вход над формой
    private By entryTitle = By.xpath(".//div[@class='Auth_login__3hAey']/h2[text()='Вход']");
    // поле Email
    private By fieldEmail = By.xpath(".//div/div/input[@name = 'name']");
    // поле Пароль
    private By fieldPassword = By.xpath("//div/div/input[@name = 'Пароль']");
    // кнопка входа
    private By buttonLogin = By.xpath(".//form/button[text()='Войти']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void findEntryTitle() { //найти элемент заголовка над формой и получить текст
        String temp = driver.findElement(entryTitle).getText();
        System.out.println(temp);
    }
    public void fillInLoginForm(String email, String password) { //заполнить форму логина данными
        driver.findElement(fieldEmail).sendKeys(email);
        driver.findElement(fieldPassword).sendKeys(password);
    }
    public void clickOnEntryButton() { //прокрутить страницу до кнопки отправки данных и нажать ее
        WebElement element = driver.findElement(buttonLogin);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        driver.findElement(buttonLogin).click();
    }
    public By getEntryTitle() {
        return entryTitle;
    }

}
