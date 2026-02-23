package methodForTest;

import config.EnvConfig;
import factory.WebDriverFactory;
import org.junit.jupiter.api.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.AbstractDriverOptions;
import pages.AbstractBaseMethod;
import pages.PageFactor;


public abstract class AbstractBaseTest extends AbstractBaseMethod {

    protected WebDriver driver;
    protected static final Logger log = LogManager.getLogger(AbstractBaseTest.class);
    private final String URL = EnvConfig.getUrl();


    @BeforeAll
    public static void startTests() {
        log.info("Начало тестирования");
    }

    @BeforeEach
    public void setUp(TestInfo testInfo) {
        driverStart(testInfo);
        this.driver = getCurrentDriver();
    }

    @AfterEach
    public void driverClose() {
        if (driver != null) {
            driver.quit();
        }
        setDriver(null); // очищение в родительском классе
        this.driver = null; // очищение локальной ссылки
    }

    @AfterAll
    public static void endTests() {
        log.info("Конец тестирования\n");
    }


    protected Capabilities getOptions(String browserName) {
        return null;
    }

    public void driverStart(TestInfo testInfo) {

        String browserName = System.getProperty("browser", "chrome");

        // проверяем опции в командной строке
        String optionsFromCmd = null;
        switch (browserName.toLowerCase()) {
            case "chrome":
                optionsFromCmd = System.getProperty("chromeOptions");
                break;
            case "firefox":
                optionsFromCmd = System.getProperty("firefoxOptions");
                break;
            case "safari":
                optionsFromCmd = System.getProperty("safariOptions");
                break;
        }

        // получаем опции из метода, который может быть переопределен в дочернем классе
        Capabilities options = getOptions(browserName);

        // если есть опции из командной строки - создаем новые опции
        if (optionsFromCmd != null && !optionsFromCmd.isEmpty()) {
            options = createOptionsFromString(browserName, optionsFromCmd);
        }

        log.info("Запуск теста: {} в браузере {}:\n{}",
                testInfo.getDisplayName(), browserName, options);

        // создаем параметризированный драйвер
        WebDriver newDriver = WebDriverFactory.create(browserName, (AbstractDriverOptions<?>) options);

        // устанавливаем параметризированный драйвер через родительский метод setDriver
        setDriver(newDriver);

        newDriver.get(URL);
        page = new PageFactor(newDriver);
    }

}