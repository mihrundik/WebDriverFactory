package factory.settings;

import org.openqa.selenium.remote.AbstractDriverOptions;


public class SafariOptions implements IsOptions {

    @Override
    public AbstractDriverOptions webDriverOptions() {
        org.openqa.selenium.safari.SafariOptions safariOptions = new org.openqa.selenium.safari.SafariOptions();
        return safariOptions;   // будут воспроизведены опции по умолчанию
    }
}