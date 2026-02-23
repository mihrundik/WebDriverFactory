package methodForTest;

import config.EnvConfig;
import org.junit.jupiter.api.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;


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

}