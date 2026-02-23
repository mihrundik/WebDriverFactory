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
                    // если опции переданы и это ChromeOptions
                    if (options instanceof ChromeOptions) {
                        return new ChromeDriver((ChromeOptions) options);
                    } else {
                        // если опции из командной строки - создаем новые ChromeOptions и копируем capabilities
                        ChromeOptions chromeOptions = new ChromeOptions();
                        options.asMap().forEach(chromeOptions::setCapability);
                        return new ChromeDriver(chromeOptions);
                    }
                } else {
                    // если опции не переданы, создаем драйвер с опциями по умолчанию (null)
                    return new ChromeDriver(new ChromeOptions());
                }

            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                if (options != null) {
                    if (options instanceof FirefoxOptions) {
                        return new FirefoxDriver((FirefoxOptions) options);
                    } else {
                        FirefoxOptions firefoxOptions = new FirefoxOptions();
                        options.asMap().forEach(firefoxOptions::setCapability);
                        return new FirefoxDriver(firefoxOptions);
                    }
                } else {
                    return new FirefoxDriver(new FirefoxOptions());
                }

            case "safari":
                WebDriverManager.safaridriver().setup();
                if (options != null) {
                    if (options instanceof SafariOptions) {
                        return new SafariDriver((SafariOptions) options);
                    } else {
                        SafariOptions safariOptions = new SafariOptions();
                        options.asMap().forEach(safariOptions::setCapability);
                        return new SafariDriver(safariOptions);
                    }
                } else {
                    return new SafariDriver(new SafariOptions());
                }

            default:
                throw new IllegalArgumentException("Неподдерживаемое имя браузера: " + webDriverName);
        }
    }

    public static WebDriver create(String webDriverName) {
        return create(webDriverName, null);
    }
}