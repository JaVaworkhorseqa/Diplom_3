package example;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertTrue;

import static example.Constant.NAME;
import static example.Constant.PASSWORD;
import static example.Constant.EMAIL;
import static example.Constant.BASEURL;


public class CollectionHomePageTest {

    private WebDriver driver;
    private WebDriverWait wait;
    HomePageBurger homePageBurger;
    private UserAPI userApi;
    private String token;

    @Before
    public void setUp() {
        // драйвер для браузера Chrome
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*"); // Разрешить внешние подключения
        options.addArguments("--disable-gpu"); // Отключение GPU для стабильности
        options.addArguments("--no-sandbox"); // Отключение песочницы
        options.addArguments("--disable-dev-shm-usage");
        userApi = new UserAPI();
        driver = new ChromeDriver(options);
        homePageBurger = new HomePageBurger(driver);
        userApi.createUser(NAME, EMAIL, PASSWORD);
        wait = new WebDriverWait(driver, Duration.ofSeconds(1));
        driver.get(BASEURL);
        homePageBurger.clickHomePageLoginButton();
        homePageBurger.logInButtonAccount(EMAIL, PASSWORD);
    }


    @Test
    public void clickButtonPersonalCabinetTest(){
        homePageBurger.clickPersonalCabinet();
        By button = homePageBurger.getButtonProfile();
        WebElement element = new WebDriverWait(driver, Duration.ofSeconds(2))
                .until(ExpectedConditions.visibilityOfElementLocated(button));
        assertTrue("Element should be visible", element.isDisplayed());
    }

    @Test
    public void clickButtonConstructorTest(){
        homePageBurger.clickConstructor();
        By button = homePageBurger.getButtonCreateBurger();
        WebElement element = new WebDriverWait(driver, Duration.ofSeconds(2))
                .until(ExpectedConditions.visibilityOfElementLocated(button));
        assertTrue("Element should be visible", element.isDisplayed());
    }
    @Test
    public void clickButtonLogoBurgerTest(){
        homePageBurger.clickConstructor();
        By button = homePageBurger.getButtonCreateBurger();
        WebElement element = new WebDriverWait(driver, Duration.ofSeconds(2))
                .until(ExpectedConditions.visibilityOfElementLocated(button));
        assertTrue("Element should be visible", element.isDisplayed());
    }
    @Test
    public void logoutAccountTest(){
        homePageBurger.clickPersonalCabinet();
        homePageBurger.clickLogout();
        By button = homePageBurger.getButtonLogIn();
        WebElement element = new WebDriverWait(driver, Duration.ofSeconds(2))
                .until(ExpectedConditions.visibilityOfElementLocated(button));
        assertTrue("Element should be visible", element.isDisplayed());
    }


    @After
    public void deleteUser() {
        try {

            token = userApi.getToken(EMAIL, NAME, PASSWORD);
            if (token != null) {
                userApi.deleteUser(token);
            }
        } catch (Exception e) {
            System.out.println("Удаление пользователя не удалось: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }
}
