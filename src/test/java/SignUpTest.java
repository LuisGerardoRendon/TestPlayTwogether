import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

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
    WebElement msgWarning;

    @Before
    public void setUp() {
        System.setProperty(
                "webdriver.chrome.driver",
                "./src/test/resources/chromedriver.exe");

        this.driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(Config.signupRute);
/*
        inputEmail = driver.findElement(By.id("tfEmail"));
        inputPassword = driver.findElement(By.id("tfPassword"));
        inputRepeatPassword = driver.findElement(By.id("tfRepeatPassword"));
        inputNickname = driver.findElement(By.id("tfNickname"));
        inputBirthday = driver.findElement(By.id("dpBirthday"));
        inputSchedule = driver.findElement(By.id("radio-for-test"));
        msgWarning = driver.findElement(By.id("warning"));

 */
        buttonSignUp = driver.findElement(By.id("btnSignUp"));
    }

    @Test
    public void testInputsEmpty() {

        WebDriverWait timer = new WebDriverWait(driver, 10);
        //Actions actions = new Actions(driver);
        //actions.moveToElement(buttonSignUp).click().build().perform();
        buttonSignUp.click();
        WebDriverWait timer1 = new WebDriverWait(driver, 10);
        String msgTextWarning = getInnerText(driver, msgWarning);

        assertEquals("Llena todos los campos obligatorios (*)", msgTextWarning);
        //driver.close(); //Para cerrar las ventanas del navegador

    }
/*
    @Test
    public void testCorrectSignUp(){
        WebDriverWait timer = new WebDriverWait(driver, 10);
        inputEmail.sendKeys("email@test.com");
        inputNickname.sendKeys("TestUser");
        inputPassword.sendKeys("Beethoven1");
        inputRepeatPassword.sendKeys("Beethoven1");
        inputBirthday.sendKeys("11101999");
        inputSchedule.click();

        buttonSignUp.click();
        WebDriverWait timer1 = new WebDriverWait(driver, 10);
        String msgTextWarning = getInnerText(driver, msgWarning);
        assertEquals("Por favor, ingrese un email válido", msgTextWarning);
        driver.close();
    }

    @Test
    public void testNoEmail(){
        WebDriverWait timer = new WebDriverWait(driver, 10);

        inputNickname.sendKeys("TestUser");
        inputPassword.sendKeys("Beethoven1");
        inputRepeatPassword.sendKeys("Beethoven1");
        inputBirthday.sendKeys("11101999");
        inputSchedule.click();
        buttonSignUp.click();
        WebDriverWait timer1 = new WebDriverWait(driver, 10);
        String msgTextWarning = getInnerText(driver, msgWarning);
        assertEquals("Por favor, ingrese un email válido", msgTextWarning);
        driver.close();
    }

    @Test
    public void testNoNickname(){
        WebDriverWait timer = new WebDriverWait(driver, 10);
        inputEmail.sendKeys("email@test.com");

        inputPassword.sendKeys("Beethoven1");
        inputRepeatPassword.sendKeys("Beethoven1");
        inputBirthday.sendKeys("11101999");
        inputSchedule.click();

        buttonSignUp.click();
        WebDriverWait timer1 = new WebDriverWait(driver, 10);
        String msgTextWarning = getInnerText(driver, msgWarning);
        assertEquals("Por favor, ingrese un email válido", msgTextWarning);
        driver.close();
    }

    @Test
    public void testNoPassword(){
        WebDriverWait timer = new WebDriverWait(driver, 10);
        inputEmail.sendKeys("email@test.com");
        inputNickname.sendKeys("TestUser");

        inputRepeatPassword.sendKeys("Beethoven1");
        inputBirthday.sendKeys("11101999");
        inputSchedule.click();

        buttonSignUp.click();
        WebDriverWait timer1 = new WebDriverWait(driver, 10);
        String msgTextWarning = getInnerText(driver, msgWarning);
        assertEquals("Por favor, ingrese un email válido", msgTextWarning);
        driver.close();
    }

    @Test
    public void testNoRepeatPassword(){
        WebDriverWait timer = new WebDriverWait(driver, 10);
        inputEmail.sendKeys("email@test.com");
        inputNickname.sendKeys("TestUser");
        inputPassword.sendKeys("Beethoven1");

        inputBirthday.sendKeys("11101999");
        inputSchedule.click();

        buttonSignUp.click();
        WebDriverWait timer1 = new WebDriverWait(driver, 10);
        String msgTextWarning = getInnerText(driver, msgWarning);
        assertEquals("Por favor, ingrese un email válido", msgTextWarning);
        driver.close();
    }

    @Test
    public void testNoBirthday(){
        WebDriverWait timer = new WebDriverWait(driver, 10);
        inputEmail.sendKeys("email@test.com");
        inputNickname.sendKeys("TestUser");
        inputPassword.sendKeys("Beethoven1");
        inputRepeatPassword.sendKeys("Beethoven1");

        inputSchedule.click();

        buttonSignUp.click();
        WebDriverWait timer1 = new WebDriverWait(driver, 10);
        String msgTextWarning = getInnerText(driver, msgWarning);
        assertEquals("Por favor, ingrese un email válido", msgTextWarning);
        driver.close();
    }

    @Test
    public void testNoShedule(){
        WebDriverWait timer = new WebDriverWait(driver, 10);
        inputEmail.sendKeys("email@test.com");
        inputNickname.sendKeys("TestUser");
        inputPassword.sendKeys("Beethoven1");
        inputRepeatPassword.sendKeys("Beethoven1");
        inputBirthday.sendKeys("11101999");


        buttonSignUp.click();
        WebDriverWait timer1 = new WebDriverWait(driver, 10);
        String msgTextWarning = getInnerText(driver, msgWarning);
        assertEquals("Por favor, ingrese un email válido", msgTextWarning);
        driver.close();
    }


    @Test
    public void testInvalidPassword(){
        inputEmail.sendKeys("rendon.luisgerardo@gmail.com");
        inputPassword.sendKeys("123456");
        buttonSignUp.click();
        String msgTextWarning = getInnerText(driver, msgWarning);
        assertEquals("Por favor, ingrese una contraseña válida", msgTextWarning);
        driver.close();
    }

 */

    public static String getInnerText(WebDriver driver, WebElement element) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        return (String) executor.executeScript("return arguments[0].innerText", element);
    }




}