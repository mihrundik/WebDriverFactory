package factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.AbstractDriverOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

public class WebDriverFactory {

    public static WebDriver create(String webDriverName, AbstractDriverOptions<?> options) {
        String browser = webDriverName.toLowerCase();

        switch (browser) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                if (options != null) {
                    return new ChromeDriver((ChromeOptions) options);
                } else {
                    return new ChromeDriver();
                }

            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                if (options != null) {
                    return new FirefoxDriver((FirefoxOptions) options);
                } else {
                    return new FirefoxDriver();
                }

            case "safari":
                WebDriverManager.safaridriver().setup();
                if (options != null) {
                    return new SafariDriver((SafariOptions) options);
                } else {
                    return new SafariDriver();
                }

            default:
                throw new IllegalArgumentException("Неподдерживаемое имя браузера: " + webDriverName);
        }
    }

    public static WebDriver create(String webDriverName) {
        return create(webDriverName, null);
    }
}