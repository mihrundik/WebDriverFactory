package methodForTest;

import driverAutomation.AbstractWebDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.AbstractDriverOptions;
import org.junit.jupiter.api.TestInfo;
import config.EnvConfig;
import factory.WebDriverFactory;
import pages.PageFactor;

public abstract class AbstractBaseMethod extends AbstractWebDriver {

    public Logger log = LogManager.getLogger(AbstractBaseMethod.class);
    public PageFactor page;
    private final String URL = EnvConfig.getUrl();


    public WebDriver getCurrentDriver() {
        return super.getDriver();
    }

    // логирование успешности/неуспешности прохождения теста
    public void statusTest(boolean isCorrect, String message) {
        if (!isCorrect) {
            log.error("{}: Ошибка!", message);
        } else {
            log.info("{}: Успешно пройден.", message);
        }
    }

    // по умолчанию опции не заданы
    protected Capabilities getOptions(String browserName) {
        return null;
    }

    public void driverStart(TestInfo testInfo) {
        // получаем имя браузера из переменных (по умолчанию "chrome")
        String browserName = System.getProperty("browser", "chrome");
        log.info("Запуск теста: {} в браузере {}",
                testInfo.getDisplayName(), browserName);

        // получаем опции из метода, который может быть переопределен в дочернем классе
        Capabilities options = getOptions(browserName);

        // cоздаем параметризированный драйвер
        WebDriver newDriver = WebDriverFactory.create(browserName, (AbstractDriverOptions<?>) options);

        // устанавливаем параметризированный драйвер через родительский метод setDriver
        setDriver(newDriver);

        newDriver.get(URL);
        page = new PageFactor(newDriver);
    }
}