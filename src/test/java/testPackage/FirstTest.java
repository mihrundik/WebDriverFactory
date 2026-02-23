package testPackage;

import factory.settings.*;
import methodForTest.AbstractBaseTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import config.EnvConfig;
import org.openqa.selenium.Capabilities;

import static org.junit.jupiter.api.Assertions.*;


public class FirstTest extends AbstractBaseTest {

    @Override
    protected Capabilities getOptions(String browserName) {
        // проверяем, есть ли опции в командной строке
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

        // если есть - парсим их
        if (optionsFromCmd != null && !optionsFromCmd.isEmpty()) {
            return OptionsParser.parse(browserName, optionsFromCmd);
        }

        // или используем стандартные опции
        IsOptions options = null;
        switch (browserName.toLowerCase()) {
            case "chrome":
                options = new ChromeOptions();
                break;
            case "firefox":
                options = new FirefoxOptions();
                break;
            case "safari":
                options = new SafariOptions();
                break;
            default:
                throw new IllegalArgumentException("Неподдерживаемый браузер: " + browserName);
        }

        return options.webDriverOptions();
    }


    @Test
    @DisplayName("Проверка формы регистрации")
    void sendOutput() throws Exception {

        String date = String.format("%s.%s.%s", EnvConfig.getBirthDay(), EnvConfig.getBirthMonth(), EnvConfig.getBirthYear());
        String birthdate = String.format("%s-%s-%s", EnvConfig.getBirthYear(), EnvConfig.getBirthMonth(), EnvConfig.getBirthDay());

        page.fillForm(EnvConfig.getUsername(), EnvConfig.getEmail(), EnvConfig.getPassword(),
                EnvConfig.getCPassword(), date, EnvConfig.getLevel());
        page.submitForm(EnvConfig.getPassword(), EnvConfig.getCPassword());

        String resultMessage = page.readOutputForm();

        boolean checkName = resultMessage.contains(EnvConfig.getUsername());
        boolean checkEmail = resultMessage.contains(EnvConfig.getEmail());
        boolean checkBirthdate = resultMessage.contains(birthdate);
        boolean checkLanguage = resultMessage.contains(EnvConfig.getLevelIngl());

        statusTest(checkName && checkEmail && checkBirthdate && checkLanguage,
                "Проверка формы регистрации 'sendOutput'");

        assertAll(
                () -> assertTrue(resultMessage.contains(EnvConfig.getUsername()), "Имя пользователя не найдено в результате"),
                () -> assertTrue(resultMessage.contains(EnvConfig.getEmail()), "Электронная почта не найдена в результате"),
                () -> assertTrue(resultMessage.contains(birthdate), "Дата рождения не найдена в результате"),
                () -> assertTrue(resultMessage.contains(EnvConfig.getLevelIngl()), "Уровень языка не найден в результате")
        );
    }
}