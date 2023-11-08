public class EnvConfig {
    public static final String YANDEX_DRIVER = System.getProperty("webdriver.yandex.driver", "src/test/resources/yandex/chromedriver.exe");
    public static final String YANDEX_BINARY = System.getProperty("webdriver.yandex.binary", "src/test/resources/yandex/Application/browser.exe");
}
