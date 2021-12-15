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

public class UpdateTest {
    private WebDriver driver;

    WebElement inputLoginEmail;
    WebElement inputLoginPassword;
    WebElement buttonLogin;
    WebElement buttonMyProfile;
    WebElement buttonEdit;


    WebElement inputPassword;
    WebElement inputRepeatPassword;
    WebElement inputNickname;
    WebElement inputBirthday;
    WebElement inputSchedule;
    WebElement buttonUpdate;
    WebElement msgWarning;

    @Before
    public void setUp() {
        System.setProperty(
                "webdriver.chrome.driver",
                "./src/test/resources/chromedriver.exe");

        this.driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(Config.loginRute);

        inputLoginEmail = driver.findElement(By.id("tfEmailLogin"));
        inputLoginPassword = driver.findElement(By.id("tfPasswordLogin"));
        buttonLogin = driver.findElement(By.id("btnLogin"));
    }

    @Test
    public void testCorrectUpdate(){
        this.goToUpdate();
        inputNickname.sendKeys("One");
        inputPassword.sendKeys("Beethoven1");
        inputRepeatPassword.sendKeys("Beethoven1");
        inputBirthday.sendKeys("12112000");
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click()", buttonUpdate);
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.close();
    }

    public void goToUpdate(){
        this.goToMyProfile();
        buttonEdit = driver.findElement(By.id("btnEdit"));
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click()", buttonEdit);
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        inputPassword = driver.findElement(By.id("tfPassword"));
        inputRepeatPassword = driver.findElement(By.id("tfRepeatPassword"));
        inputNickname = driver.findElement(By.id("tfNickname"));
        inputBirthday = driver.findElement(By.id("dpBirthday"));
        inputSchedule = driver.findElement(By.id("update_schedule_3"));
        buttonUpdate = driver.findElement(By.id("btnUpdate"));
        msgWarning = driver.findElement(By.id("warning"));
    }

    public void goToMyProfile(){
        this.login();
        buttonMyProfile = driver.findElement(By.id("hbMyProfile"));
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click()", buttonMyProfile);
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void login() {
        WebDriverWait timer = new WebDriverWait(driver, 10);
        inputLoginEmail.sendKeys("email@test.com");
        inputLoginPassword.sendKeys("Beethoven1");
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click()", buttonLogin);
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testInputsEmpty() {
        this.goToUpdate();
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click()", buttonUpdate);
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.close();
    }

    @Test
    public void testNoEqualPassword(){
        this.goToUpdate();
        inputPassword.sendKeys("Beethoven1");
        inputRepeatPassword.sendKeys("Beethoven2");
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click()", buttonUpdate);
        String msgTextWarning = getInnerText(driver, msgWarning);
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertEquals("* Las contraseñas no coinciden", msgTextWarning);
        driver.close();
    }

    @Test
    public void testInvalidPassword(){
        this.goToUpdate();
        inputPassword.sendKeys("hola");
        inputRepeatPassword.sendKeys("hola");
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click()", buttonUpdate);

        String msgTextWarning = getInnerText(driver, msgWarning);
        assertEquals("* La contraseña debe contener al menos una mayúscula y un número", msgTextWarning);
        driver.close();
    }

    @Test
    public void testUpdatePassword(){
        this.goToUpdate();
        inputPassword.sendKeys("Beethoven1");
        inputRepeatPassword.sendKeys("Beethoven1");
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click()", buttonUpdate);
        driver.close();
    }

    @Test
    public void testUpdateNickname(){
        this.goToUpdate();
        inputNickname.sendKeys("One");
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click()", buttonUpdate);
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.close();
    }

    @Test
    public void testUpdateBirthday(){
        this.goToUpdate();
        inputBirthday.sendKeys("01022003");
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click()", buttonUpdate);
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.close();
    }

    @Test
    public void testUpdateMinorBirthday(){
        this.goToUpdate();
        inputBirthday.sendKeys("01022015");
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click()", buttonUpdate);
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String msgTextWarning = getInnerText(driver, msgWarning);
        assertEquals("* Debes ser mayor que 11 años y menor que 100 para poder registrarte", msgTextWarning);
        driver.close();
    }

    public static String getInnerText(WebDriver driver, WebElement element) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        return (String) executor.executeScript("return arguments[0].innerText", element);
    }
}