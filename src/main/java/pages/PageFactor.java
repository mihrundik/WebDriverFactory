package pages;

import config.EnvConfig;
import driverAutomation.AbstractWebDriver;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;


public class PageFactor extends AbstractWebDriver  {

    private PageFactor page;

    // используемые в тестах элементы страницы
    @FindBy(id = "username")
    private WebElement inputName;

    @FindBy(id = "email")
    private WebElement inputEmail;

    @FindBy(id = "password")
    private WebElement inputPassword;

    @FindBy(id = "confirm_password")
    private WebElement passwordConfirmation;

    @FindBy(id = "birthdate")
    private WebElement inputBirthdate;

    @FindBy(id = "language_level")
    private WebElement inputLanguageLevel;

    @FindBy(css = "#registrationForm > input[type=\"submit\"]")
    private WebElement inputForm;

    @FindBy(id = "output")
    private WebElement outputForm;

    // инициализируем класс и связываем объекты элементов на странице
    public PageFactor(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    // единая функция для заполнения полей
    private boolean fillField(WebElement element, String value) {
        element.clear();
        element.sendKeys(value);
        return true;
    }

    public boolean fillInputName(String username) {
        return fillField(inputName, username);
    }

    public boolean fillInputEmail(String email) {
        return fillField(inputEmail, email);
    }

    public boolean fillInputPassword(String password) {
        return fillField(inputPassword, password);
    }

    public boolean fillPasswordConfirmation(String cPassword) {
        return fillField(passwordConfirmation, cPassword);
    }

    public boolean fillBirthDate(String dateValue) {
        // Определяем браузер
        String browserName = ((org.openqa.selenium.remote.RemoteWebDriver)driver).getCapabilities().getBrowserName().toLowerCase();

        // отработка формы даты для safari отличается
        if (browserName.contains("safari")) {
            String[] parts = dateValue.split("\\.");
            String day = parts[0];
            String month = parts[1];
            String year = parts[2];

            inputBirthdate.click();

            // день
            actions.sendKeys(Keys.BACK_SPACE).perform();
            actions.sendKeys(day).perform();
            actions.sendKeys(Keys.ARROW_RIGHT).perform();

            // месяц
            actions.sendKeys(Keys.BACK_SPACE).perform();
            actions.sendKeys(month).perform();
            actions.sendKeys(Keys.ARROW_RIGHT).perform();

            // год
            actions.sendKeys(Keys.BACK_SPACE).perform();
            actions.sendKeys(year).perform();

            actions.sendKeys(Keys.ENTER).perform();
            return true;
        } else {
            String[] parts = dateValue.split("\\.");
            String day = parts[0];
            String month = parts[1];
            String year = parts[2];

            inputBirthdate.click();

            // день
            actions.sendKeys(day).perform();
            // месяц
            actions.sendKeys(month).perform();
            // год
            actions.sendKeys(year).perform();

            actions.sendKeys(Keys.ENTER).perform();
            return true;
        }
    }

    // функция выбора уровня владения языком
    public void selectInputLanguageLevel(String level) {
        Select dropdown = new Select(inputLanguageLevel);
        dropdown.selectByVisibleText(level);
    }

    // проверка значений поля ввода
    public boolean checkPasswordsMatch(String password, String confirmPassword) {
        String actualPassword = inputPassword.getAttribute("value");
        String actualConfirmPassword = passwordConfirmation.getAttribute("value");
        return actualPassword.equals(actualConfirmPassword);
    }

    // заполнение полей формы значениями из EnvConfig
    public void fillFormWithConfig() {
        String birthdate = EnvConfig.getBirthDay() + "." +
                EnvConfig.getBirthMonth() + "." +
                EnvConfig.getBirthYear();

        fillForm(
                EnvConfig.getUsername(),
                EnvConfig.getEmail(),
                EnvConfig.getPassword(),
                EnvConfig.getCPassword(),
                birthdate,
                EnvConfig.getLevel()
        );
    }

    // перегруженный метод для заполнения полей с параметрами
    public void fillForm(String name, String email, String password, String cPassword,
                         String birthdate, String language) {
        fillInputName(name);
        fillInputEmail(email);
        fillInputPassword(password);
        fillPasswordConfirmation(cPassword);
        fillBirthDate(birthdate);
        selectInputLanguageLevel(language);
    }

    // отправка заполненной формы
    public void submitForm() throws Exception {
        if (!checkPasswordsMatch(EnvConfig.getPassword(), EnvConfig.getCPassword())) {
            throw new Exception("Пароли не совпадают!");
        }
        inputForm.click();
    }

    // перегруженный метод для отправки формы с проверкой паролей
    public void submitForm(String password, String cPassword) throws Exception {
        if (!checkPasswordsMatch(password, cPassword)) {
            throw new Exception("Пароли не совпадают!");
        }
        inputForm.click();
    }

    // проверка результата отправки формы
    public String readOutputForm() {
        return outputForm.getText();
    }
}