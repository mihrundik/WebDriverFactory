package pages;

import driverAutomation.AbstractWebDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;


public abstract class AbstractBaseMethod extends AbstractWebDriver {

    public Logger log = LogManager.getLogger(AbstractBaseMethod.class);
    public PageFactor page;

    public WebDriver getCurrentDriver() {
        return super.getDriver();
    }

    public void statusTest(boolean isCorrect, String message) {
        if (!isCorrect) {
            log.error("{}: Ошибка!", message);
        } else {
            log.info("{}: Успешно пройден.", message);
        }
    }

    // обрабатываем опции
    public Capabilities createOptionsFromString(String browserName, String optionsString) {
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