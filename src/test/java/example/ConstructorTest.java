package example;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
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


public class ConstructorTest {

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
    public void clickBunSectionTest(){
        homePageBurger.clickConstructor();
        homePageBurger.clickOnSaucesSection();
        homePageBurger.clickOnBunSection();


        By button = homePageBurger.getBun();
        WebElement element = new WebDriverWait(driver, Duration.ofSeconds(2))
                .until(ExpectedConditions.visibilityOfElementLocated(button));
        assertTrue("Element should be visible", element.isDisplayed());
    }
    @Test
    public void clickSaucesSectionTest(){
        homePageBurger.clickConstructor();
        homePageBurger.clickOnSaucesSection();

        By button = homePageBurger.getSauce();
        WebElement element = new WebDriverWait(driver, Duration.ofSeconds(2))
                .until(ExpectedConditions.visibilityOfElementLocated(button));
        assertTrue("Element should be visible", element.isDisplayed());
    }
    @Test
    public void clickFillingSectionTest(){
        homePageBurger.clickConstructor();
        homePageBurger.clickOnFillingSection();

        By button = homePageBurger.getFilling();
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
