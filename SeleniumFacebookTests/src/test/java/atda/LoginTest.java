package atda;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import facebook.LoginPage;
import facebook.ProductPage;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;


public class LoginTest {


    private WebDriver driver;

    @BeforeTest
    public void setup() {
        driver = getDriver();
    }



    @Test
    public void shouldLogin() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        assertTrue(loginPage.isLoaded());
        loginPage.login("jana.mitrovska@gmail.com", ":)");
        assertTrue(new ProductPage(driver).isLoaded());
    }

    @Test
    public void canNotLoginWithInvalidUserName() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        assertTrue(loginPage.isLoaded());
        loginPage.login("standard_user@gmail.com", "secret");
        String errorMessage = loginPage.getEmailErrorMessage();
        assertEquals(errorMessage, "The email you entered isn’t connected to an account. Find your account and log in.");
    }

    @Test
    public void canNotLoginWithInvalidPassword() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        assertTrue(loginPage.isLoaded());
        loginPage.login("jana.mitrovska@gmail.com", "password");
        String errorMessage = loginPage.getPasswordErrorMessage();
        assertEquals(errorMessage, "The password you’ve entered is incorrect. Forgot Password?");
    }

    @Test
    public void emptyUsername() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        assertTrue(loginPage.isLoaded());
        loginPage.login("", "password");
        String errorMessage = loginPage.getEmailErrorMessage();
        assertEquals(errorMessage, "The email or mobile number you entered isn’t connected to an account. Find your account and log in.");
    }

    private WebDriver getDriver() {
        System.setProperty("webdriver.chrome.driver","C:\\Users\\mitro\\Desktop\\SeleniumFacebookTests\\src\\main\\resources\\drivers\\chromedriver.exe");
        return new ChromeDriver();
    }

    @AfterTest
    public void teardown() {
        driver.quit();
    }

}
