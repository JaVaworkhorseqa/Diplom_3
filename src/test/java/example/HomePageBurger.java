package example;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class HomePageBurger {

    private WebDriver driver;
    private By personalCabinet = By.xpath(".//nav/a/p[text()='Личный Кабинет']");
    private By registration = By.xpath(".//div/p/a[text()='Зарегистрироваться']");
    private By buttonName = By.xpath(".//div[.//label[text() = 'Имя']]/input");
    private By buttonЕmail = By.xpath(".//div[.//label[text() = 'Email']]/input");
    private By buttonPassword = By.xpath(".//div[.//label[text() = 'Пароль']]/input");
    private By buttonLogAccount = By.xpath(".//button[text()='Войти в аккаунт']");
    private By littleButtonLogin = By.xpath(".//a[text()='Войти']");
    private By buttonRegistration = By.xpath(".//button[text()='Зарегистрироваться']");
    private By buttonLogIn = By.xpath(".//button[text()='Войти']");
    private By buttonCreateOrder = By.xpath(".//button[text()='Оформить заказ']");
    private By errorPassword = By.xpath(".//p[text()='Некорректный пароль']");
    private By buttonResstorePassword = By.xpath(".//a[text()='Восстановить пароль']");
    private By resstore = By.xpath(".//a[text()='Восстановить']");
    private By logoBurger = By.xpath(".//div[class()='AppHeader_header__logo__2D0X2']/a/svg");
    private By constructor = By.xpath(".//p[text()='Конструктор']");
    private By createBurger = By.xpath(".//h1[text()='Соберите бургер']");
    private By profile = By.xpath(".//a[text()='Профиль']");
    private By logout = By.xpath(".//li/button[text()='Выход']");
    private By bunSection  = By.xpath(".//span[text()='Булки']");
    private By saucesSection = By.xpath(".//span[text()='Соусы']");
    public By fillingSection = By.xpath(".//span[text()='Начинки']");
    private By bunCrator = By.xpath(".//p[text()='Краторная булка N-200i']");
    private By sauce = By.xpath(".//p[text()='Соус Spicy-X']");
    private By filling = By.xpath(".//p[text()='Говяжий метеорит (отбивная)']");






    @Step("Метод получение локатора")
    public By getButtonCreateBurger() {
        return createBurger;
    }
    @Step("Метод получение локатора")
    public By getBun() {
        return bunCrator;
    }
    @Step("Метод получение локатора")
    public By getSauce() {
        return sauce;
    }
    @Step("Метод получение локатора")
    public By getFilling() {
        return filling;
    }
    @Step("Метод получение локатора")
    public By getButtonProfile() {
        return profile;
    }
    @Step("Метод получение локатора")
    public By getButtonCreateOrder() {
        return buttonCreateOrder;
    }
    @Step("Метод получение локатора")
    public By getErrorPassword() {
        return errorPassword;
    }
    @Step("Метод получение локатора")
    public By getButtonLogIn() {
        return buttonLogIn;
    }

    public HomePageBurger(WebDriver driver){
        this.driver = driver;
    }
    @Step("Метод нажатия на секцию булочек")
    public void clickOnBunSection() {
        WebElement element = new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.elementToBeClickable(bunSection));
        driver.findElement(bunSection).click();
    }
    @Step("Метод нажатия на секцию соусов")
    public void clickOnSaucesSection() {
        WebElement element = new WebDriverWait(driver, Duration.ofSeconds(2))
                .until(ExpectedConditions.visibilityOfElementLocated(saucesSection));
        driver.findElement(saucesSection).click();
    }
    @Step("Метод нажатия на секцию начинок")
    public void clickOnFillingSection() {
        WebElement element = new WebDriverWait(driver, Duration.ofSeconds(2))
                .until(ExpectedConditions.visibilityOfElementLocated(fillingSection));
        driver.findElement(fillingSection).click();
    }
    @Step("Метод нажатия на кнопку конструктор")
    public void clickConstructor() {
        driver.findElement(constructor).click();
    }
    @Step("Метод нажатия на кнопку Выход")
    public void clickLogout() {
        WebElement element = new WebDriverWait(driver, Duration.ofSeconds(2))
                .until(ExpectedConditions.visibilityOfElementLocated(logout));
        driver.findElement(logout).click();
    }
    @Step("Метод нажатия на кнопку лого бургер")
    public void clickLogoBurger() {
        WebElement element = new WebDriverWait(driver, Duration.ofSeconds(2))
                .until(ExpectedConditions.visibilityOfElementLocated(logoBurger));
        driver.findElement(logoBurger).click();
    }
    @Step("Метод нажатия на кнопку личный кабинет")
    public void clickPersonalCabinet() {
        driver.findElement(personalCabinet).click();
    }
    @Step("Метод нажатия на кнопку маленькой кнопки войти")
    public void clickLittleButtonLogin() {
        driver.findElement(littleButtonLogin).click();
    }
    @Step("Метод нажатия на кнопку войти на главной странице")
    public void clickHomePageLoginButton() {
        driver.findElement(buttonLogAccount).click();
    }
    @Step("Метод регистрации через личный кабинет")
    public void setRegistration(String name, String email, String password) {
        clickPersonalCabinet();
        driver.findElement(registration).click();
        driver.findElement(buttonName).sendKeys(name);
        driver.findElement(buttonЕmail).sendKeys(email);
        driver.findElement(buttonPassword).sendKeys(password);
        driver.findElement(buttonRegistration).click();

    }
    @Step("Метод входа в аккаунт начинается с полей")
    public void logInButtonAccount(String email, String password) {
        driver.findElement(buttonЕmail).sendKeys(email);
        driver.findElement(buttonPassword).sendKeys(password);
        driver.findElement(buttonLogIn).click();

    }




}
