import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class LoginTest {
    private WebDriver driver;
    WebElement buttonLogin;
    WebElement inputEmail;
    WebElement inputPassword;
    WebElement msgWarning;

    @Before
    public void setUp() {
        System.setProperty(
                "webdriver.chrome.driver",
                "./src/test/resources/chromedriver.exe");



        this.driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(Config.loginRute);
        buttonLogin = driver.findElement(By.id("btnLogin"));
        inputEmail = driver.findElement(By.id("tfEmailLogin"));
        inputPassword = driver.findElement(By.id("tfPasswordLogin"));
        msgWarning = driver.findElement(By.id("warning"));

    }

    @Test
    public void testInputsEmpty() {

        waitTest(2);
        buttonLogin.click();
        waitTest(2);
        String msgTextWarning = getInnerText(driver, msgWarning);
        assertEquals("Por favor, ingrese una contraseña válida", msgTextWarning);
        driver.close(); //Para cerrar las ventanas del navegador

    }

    @Test
    public  void testInvalidEmail(){
        inputEmail.sendKeys("luissss");
        inputPassword.sendKeys("Beethoven1");
        waitTest(2);
        buttonLogin.click();
        waitTest(2);
        String msgTextWarning = getInnerText(driver, msgWarning);
        assertEquals("Por favor, ingrese un email válido", msgTextWarning);
        driver.close();
    }

    @Test
    public  void testInvalidPassword(){
        waitTest(2);
        inputEmail.sendKeys("rendon.luisgerardo@gmail.com");
        inputPassword.sendKeys("123456");
        waitTest(2);
        buttonLogin.click();
        waitTest(2);
        String msgTextWarning = getInnerText(driver, msgWarning);
        assertEquals("Por favor, ingrese una contraseña válida", msgTextWarning);
        driver.close();
    }

    public static String getInnerText(WebDriver driver, WebElement element) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        return (String) executor.executeScript("return arguments[0].innerText", element);
    }
    public static void waitTest(int seconds){
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }




}