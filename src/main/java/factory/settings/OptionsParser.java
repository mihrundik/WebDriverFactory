package factory.settings;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariOptions;
import org.openqa.selenium.remote.AbstractDriverOptions;


public class OptionsParser {

    public static AbstractDriverOptions<?> parse(String browserName, String optionsString) {
        String[] optionsArray = optionsString.split(",");

        switch (browserName.toLowerCase()) {
            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments(optionsArray);
                return chromeOptions;

            case "firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments(optionsArray);
                return firefoxOptions;

            case "safari":
                // Safari может не поддерживать все аргументы командной строки
                return new SafariOptions();

            default:
                throw new IllegalArgumentException("Неподдерживаемый браузер: " + browserName);
        }
    }
}