public class EnvConfig {
    public static final String CHROME_DRIVER = System.getProperty("webdriver.chrome.driver", "/opt/chromedriver/chromedriver");
    public static final String CHROME_BINARY = System.getProperty("webdriver.chrome.binary", "C://Praktikum/chrome-win64/chrome.exe");
    public static final String YANDEX_DRIVER = System.getProperty("webdriver.yandex.driver", "src/test/resources/yandex/chromedriver.exe");
    public static final String YANDEX_BINARY = System.getProperty("webdriver.yandex.binary", "src/test/resources/yandex/Application/browser.exe");
}
