package webdriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

public class WebDriverFactory {

    public static WebDriver create(String webDriverName, Capabilities options) {
        String browser = webDriverName.toLowerCase();

        switch (browser) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                // если опции предоставлены и они являются ChromeOptions, используем их
                if (options instanceof ChromeOptions) {
                    return new ChromeDriver((ChromeOptions) options);
                } else {
                    return new ChromeDriver(); // иначе создаем с опциями по умолчанию
                }

            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                // если опции предоставлены и они являются FirefoxOptions, используем их
                if (options instanceof FirefoxOptions) {
                    return new FirefoxDriver((FirefoxOptions) options);
                } else {
                    return new FirefoxDriver();  // иначе создаем с опциями по умолчанию
                }

            case "safari":
                WebDriverManager.safaridriver().setup();
                // если опции предоставлены и они являются SafariOptions, используем их
                if (options instanceof SafariOptions) {
                    return new SafariDriver((SafariOptions) options);
                } else {
                    return new SafariDriver(); // иначе создаем с опциями по умолчанию
                }

            default:
                throw new IllegalArgumentException("Неподдерживаемое имя браузера: " + webDriverName);
        }
    }

    // перегружаем метод для создания WebDriver без дополнительных опций
    public static WebDriver create(String webDriverName) {
        return create(webDriverName, null);
    }
}
