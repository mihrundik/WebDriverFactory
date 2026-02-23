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

    protected Capabilities getOptions(String browserName) {
        return null;
    }

    public void statusTest(boolean isCorrect, String message) {
        if (!isCorrect) {
            log.error("{}: Ошибка!", message);
        } else {
            log.info("{}: Успешно пройден.", message);
        }
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

    // обрабатываем опции
    private Capabilities createOptionsFromString(String browserName, String optionsString) {
        String[] optionsArray = optionsString.split(",");

        switch (browserName.toLowerCase()) {
            case "chrome":
                org.openqa.selenium.chrome.ChromeOptions chromeOptions =
                        new org.openqa.selenium.chrome.ChromeOptions();
                chromeOptions.addArguments(optionsArray);
                return chromeOptions;
            case "firefox":
                org.openqa.selenium.firefox.FirefoxOptions firefoxOptions =
                        new org.openqa.selenium.firefox.FirefoxOptions();
                firefoxOptions.addArguments(optionsArray);
                return firefoxOptions;
            case "safari":  // у safari могут быть ососбенности в работе с selenium
                org.openqa.selenium.safari.SafariOptions safariOptions =
                        new org.openqa.selenium.safari.SafariOptions();
                return safariOptions;
            default:
                throw new IllegalArgumentException("Неподдерживаемый браузер: " + browserName);
        }
    }
}