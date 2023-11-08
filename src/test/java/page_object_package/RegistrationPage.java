package page_object_package;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;


public class RegistrationPage {
    private WebDriver driver;
    //заголовок "Регистрация" над формой
    private By registrationTitle = By.xpath(".//div[@class='Auth_login__3hAey']/h2[text()='Регистрация']");
    //поле Имя
    private By fieldName = By.xpath(".//div/input[@name = 'name']");
    //поле Email
    private By fieldEmail = By.xpath(".//div/main/div/form/fieldset[2]/div/div/input");
    //поле Пароль
    private By fieldPassword = By.xpath(".//div/input[@type = 'password']");
    //кнопка Зарегистрироваться
    private By buttonRegister = By.xpath(".//form/button[text()='Зарегистрироваться']");
    //линк Войти
    private By entryLinkButton = By.className("Auth_link__1fOlj");
    //текст ошибки при неверной длине пароля
    private By errorPassword = By.xpath(".//div/p[@class='input__error text_type_main-default']");


    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickOnEntryLinkButton() { //скролл до линка Войти и клик
        WebElement element = driver.findElement(entryLinkButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        driver.findElement(entryLinkButton).click();
    }

    //заполнение формы регистрации
    public void fillInRegistrationForm(String name, String email, String password) {
        driver.findElement(fieldName).sendKeys(name);
        driver.findElement(fieldEmail).sendKeys(email);
        driver.findElement(fieldPassword).sendKeys(password);
    }

    //проскроллить до кнопки отправки данных и нажать ее
    public void clickOnRegisterButton() {
        WebElement element = driver.findElement(buttonRegister);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        driver.findElement(buttonRegister).click();
    }

    public By getErrorPassword() {
        return errorPassword;
    }

    public By getButtonRegister() {
        return buttonRegister;
    }
}
