import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class SignUpTest {
    private WebDriver driver;


    WebElement buttonSignUp;
    WebElement inputEmail;
    WebElement inputPassword;
    WebElement inputRepeatPassword;
    WebElement inputNickname;
    WebElement inputBirthday;
    WebElement inputSchedule;

    @Before
    public void setUp() {
        System.setProperty(
                "webdriver.chrome.driver",
                "./src/test/resources/chromedriver.exe");

        this.driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(Config.signupRute);

        inputEmail = driver.findElement(By.id("tfEmail"));
        inputPassword = driver.findElement(By.id("tfPassword"));
        inputRepeatPassword = driver.findElement(By.id("tfRepeatPassword"));
        inputNickname = driver.findElement(By.id("tfNickname"));
        inputBirthday = driver.findElement(By.id("dpBirthday"));
        inputSchedule = driver.findElement(By.id("radio-for-test"));

        msgWarning = driver.findElement(By.id("warning"));
        buttonSignUp = driver.findElement(By.id("btnSignUp"));
    }

    WebElement msgWarning;

    @Test
    public void testInputsEmpty() {
        try {
            TimeUnit.SECONDS.sleep(3);
            WebDriverWait timer = new WebDriverWait(driver, 10);
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].click()", buttonSignUp);
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String msgTextWarning = getInnerText(driver, msgWarning);

        assertEquals("Llena todos los campos obligatorios (*)", msgTextWarning);
        driver.close(); //Para cerrar las ventanas del navegador
    }

    @Test
    public void testCorrectSignUp() {
        try {
            TimeUnit.SECONDS.sleep(3);
            WebDriverWait timer = new WebDriverWait(driver, 10);
            inputEmail.sendKeys("email@test.com");
            inputNickname.sendKeys("TestUser");
            inputPassword.sendKeys("Beethoven1");
            inputRepeatPassword.sendKeys("Beethoven1");
            inputBirthday.sendKeys("11101999");
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].click()", inputSchedule);
            executor.executeScript("arguments[0].click()", buttonSignUp);
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String msgTextWarning = getInnerText(driver, msgWarning);
        System.out.println(msgTextWarning);
        assertEquals("El nickname o el email existen, prueba cambi??ndolos*", msgTextWarning);
        driver.close();
    }

    @Test
    public void testNoEmail() {
        try {
            TimeUnit.SECONDS.sleep(3);

            inputNickname.sendKeys("TestUser");
            inputPassword.sendKeys("Beethoven1");
            inputRepeatPassword.sendKeys("Beethoven1");
            inputBirthday.sendKeys("11101999");

            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].click()", inputSchedule);
            executor.executeScript("arguments[0].click()", buttonSignUp);

            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String msgTextWarning = getInnerText(driver, msgWarning);
        assertEquals("Llena todos los campos obligatorios (*)", msgTextWarning);
        driver.close();
    }

    @Test
    public void testNoNickname() {
        try {
            TimeUnit.SECONDS.sleep(3);
            inputEmail.sendKeys("email@test.com");

            inputPassword.sendKeys("Beethoven1");
            inputRepeatPassword.sendKeys("Beethoven1");
            inputBirthday.sendKeys("11101999");
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].click()", inputSchedule);
            executor.executeScript("arguments[0].click()", buttonSignUp);
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String msgTextWarning = getInnerText(driver, msgWarning);
        assertEquals("Llena todos los campos obligatorios (*)", msgTextWarning);
        driver.close();
    }

    @Test
    public void testNoPassword() {
        try {
            TimeUnit.SECONDS.sleep(3);
            WebDriverWait timer = new WebDriverWait(driver, 10);
            inputEmail.sendKeys("email@test.com");
            inputNickname.sendKeys("TestUser");

            inputRepeatPassword.sendKeys("Beethoven1");
            inputBirthday.sendKeys("11101999");
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].click()", inputSchedule);
            executor.executeScript("arguments[0].click()", buttonSignUp);
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String msgTextWarning = getInnerText(driver, msgWarning);
        assertEquals("Llena todos los campos obligatorios (*)", msgTextWarning);
        driver.close();
    }

    @Test
    public void testNoRepeatPassword() {
        try {
            TimeUnit.SECONDS.sleep(3);
            WebDriverWait timer = new WebDriverWait(driver, 10);
            inputEmail.sendKeys("email@test.com");
            inputNickname.sendKeys("TestUser");
            inputPassword.sendKeys("Beethoven1");

            inputBirthday.sendKeys("11101999");
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].click()", inputSchedule);
            executor.executeScript("arguments[0].click()", buttonSignUp);
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String msgTextWarning = getInnerText(driver, msgWarning);
        assertEquals("Llena todos los campos obligatorios (*)", msgTextWarning);
        driver.close();
    }

    @Test
    public void testNoBirthday() {
        try {
            TimeUnit.SECONDS.sleep(3);
            WebDriverWait timer = new WebDriverWait(driver, 10);
            inputEmail.sendKeys("email@test.com");
            inputNickname.sendKeys("TestUser");
            inputPassword.sendKeys("Beethoven1");
            inputRepeatPassword.sendKeys("Beethoven1");
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].click()", inputSchedule);
            executor.executeScript("arguments[0].click()", buttonSignUp);
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String msgTextWarning = getInnerText(driver, msgWarning);
        assertEquals("Llena todos los campos obligatorios (*)", msgTextWarning);
        driver.close();
    }

    @Test
    public void testNoShedule() {
        try {
            TimeUnit.SECONDS.sleep(3);
            WebDriverWait timer = new WebDriverWait(driver, 10);
            inputEmail.sendKeys("email@test.com");
            inputNickname.sendKeys("TestUser");
            inputPassword.sendKeys("Beethoven1");
            inputRepeatPassword.sendKeys("Beethoven1");
            inputBirthday.sendKeys("11101999");

            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].click()", buttonSignUp);
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String msgTextWarning = getInnerText(driver, msgWarning);
        assertEquals("Llena todos los campos obligatorios (*)", msgTextWarning);
        driver.close();
    }

    public static String getInnerText(WebDriver driver, WebElement element) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        return (String) executor.executeScript("return arguments[0].innerText", element);
    }
}