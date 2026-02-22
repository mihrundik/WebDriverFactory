package webdriver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;

public abstract class AbstractClass {

    static final Logger log = LogManager.getLogger(AbstractClass.class);
    protected WebDriver driver;
    protected static PageFactor page;
    private static final String URL = EnvConfig.getUrl();

    // по умолчанию опции не заданы
    protected Capabilities getOptions(String browserName) {
        return null;
    }

    // логирование успешности/неуспешности прохождения теста
    public void statusTest(boolean isCorrect, String message) {
        if (!isCorrect) {
            log.error("{}: Ошибка!", message);
        } else {
            log.info("{}: Успешно пройден.", message);
        }
    }

    @BeforeAll
    public static void startTests() {
        log.info("Начало тестирования");
    }

    @BeforeEach
    public void driverStart(TestInfo testInfo) {
        // получаем имя браузера из переменных (о умолчанию "chrome")
        // можно ли запускать тесты можно командой: mvn clean test -Dbrowser=firefox?
        String browserName = System.getProperty("browser", "safari");
        log.info("Запуск теста: {} в браузере {}",
                testInfo.getDisplayName(), browserName);

        // получаем опции из метода, который может быть переопределен в дочернем классе
        Capabilities options = getOptions(browserName);

        driver = WebDriverFactory.create(browserName, options);

        driver.get(URL);
        page = new PageFactor(driver);
    }

    @AfterEach
    public void driverClose() {
        if (driver != null) {
            driver.quit();
            log.info("Браузер закрыт.");
            driver = null;
        }
    }

    @AfterAll
    public static void endTests() {
        log.info("Конец тестирования\n");
    }
}