package factory.settings;

import org.openqa.selenium.remote.AbstractDriverOptions;

public class FirefoxOptions implements IsOptions {

    @Override
    public AbstractDriverOptions webDriverOptions() {
        org.openqa.selenium.firefox.FirefoxOptions firefoxOptions = new org.openqa.selenium.firefox.FirefoxOptions();
        firefoxOptions.addArguments("--headless");
        return firefoxOptions;
    }
}