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

    @Before
    public void setUp() {
        System.setProperty(
                "webdriver.chrome.driver",
                "./src/test/resources/chromedriver.exe");



        this.driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(Config.loginRute);
    }

    @Test
    public void testGooglePage() {
        WebElement botonLogin = driver.findElement(By.id("btnLogin"));


        WebDriverWait timer = new WebDriverWait(driver, 10);

        botonLogin.click();
        WebDriverWait timer1 = new WebDriverWait(driver, 10);

        WebElement msgWarning = driver.findElement(By.id("warning"));
        String msgTextWarning = getInnerText(driver, msgWarning);
        

        assertEquals("Por favor, ingrese una contraseña válida", msgTextWarning);



    }

    public static String getInnerText(WebDriver driver, WebElement element) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        return (String) executor.executeScript("return arguments[0].innerText", element);
    }


}