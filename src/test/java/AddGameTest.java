import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class AddGameTest {
    private WebDriver driver;
    WebElement buttonLogin;
    WebElement inputEmail;
    WebElement inputPassword;
    WebElement tfNickname;
    WebElement tfLevel;
    WebElement cbAgent;
    WebElement cbRank;
    WebElement tfHours;
    WebElement tfNote;
    WebElement msgWarning;
    WebElement btnSaveGame;

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

    }

    public  void login(){
        inputEmail.sendKeys("lampro.azul@gmail.com");
        inputPassword.sendKeys("Beethoven1");
        buttonLogin.click();
        waitTest(5);

    }

    public void goGames(){
        WebElement buttonGames = driver.findElement(By.id("hbGames"));
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click()", buttonGames);
        waitTest(3);

    }

    @Test
    public void addGameTest(){
        goToValorant();
        this.fillFIelds();
        JavascriptExecutor executor2 = (JavascriptExecutor)driver;
        executor2.executeScript("arguments[0].click()", this.btnSaveGame);
        waitTest(5);
        String message = getInnerText(driver, msgWarning);
        assertEquals("Ya has registrado valorant previamente, por favor, intenta con otro juego", message);
        driver.close();

    }



    @Test
    public void addGameWithoutHoursTest(){
        goToValorant();
        this.fillFIeldsIncompleteWithoutHours();
        JavascriptExecutor executor2 = (JavascriptExecutor)driver;
        executor2.executeScript("arguments[0].click()", this.btnSaveGame);
        waitTest(1);
        String message = getInnerText(driver, msgWarning);
        assertEquals("Llena correctamente todos los campos obligatorios (*)", message);
        driver.close();

    }

    @Test
    public void addGameInvalidHours(){
        goToValorant();
        this.fillFIeldsIncompleteWithoutHours();
        this.tfHours.sendKeys("Hola");
        JavascriptExecutor executor2 = (JavascriptExecutor)driver;
        executor2.executeScript("arguments[0].click()", this.btnSaveGame);
        waitTest(1);
        String message = getInnerText(driver, msgWarning);
        assertEquals("Las horas totales no son válidas", message);
        driver.close();
    }

    public void goToValorant(){
        this.login();
        this.goGames();
        WebElement buttonValorant = driver.findElement(By.id("ValorantCard"));
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click()", buttonValorant);
        waitTest(3);
        this.findFields();
    }

    public void findFields(){
         this.tfNickname = driver.findElement(By.id("tfNickname"));
        this.tfLevel = driver.findElement(By.id("tfLevel"));
        this.cbAgent = driver.findElement(By.id("cbAgent"));
        this.cbRank = driver.findElement(By.id("cbRank"));
        this.tfHours = driver.findElement(By.id("tfHours"));
        this.tfNote = driver.findElement(By.id("tfNote"));
        this.btnSaveGame = driver.findElement(By.id("btnAddGame"));
        this.msgWarning = driver.findElement(By.id("warning"));
    }
    public void fillFIelds(){
        this.tfNickname.sendKeys("elLamproPeltis");
        this.tfLevel.sendKeys("10");
        this.cbAgent.sendKeys("Astra");
        this.cbRank.sendKeys("Hierro");
        this.tfHours.sendKeys("200");
        this.tfNote.sendKeys("Soy tu padre");
    }

    public void fillFIeldsIncompleteWithoutHours(){
        this.tfNickname.sendKeys("elLamproPeltis");
        this.tfLevel.sendKeys("10");
        this.cbAgent.sendKeys("Astra");
        this.cbRank.sendKeys("Hierro");
    }
    public void fillFIeldsWithoutRank(){
        this.tfNickname.sendKeys("elLamproPeltis");
        this.tfLevel.sendKeys("10");
        this.cbAgent.sendKeys("Astra");
        this.tfHours.sendKeys("200");
        this.tfNote.sendKeys("Soy tu padre");
    }
    public void fillFIeldsWithouthAgent(){
        this.tfNickname.sendKeys("elLamproPeltis");
        this.tfLevel.sendKeys("10");
        this.cbRank.sendKeys("Hierro");
        this.tfHours.sendKeys("200");
        this.tfNote.sendKeys("Soy tu padre");
    }
    public void fillFIeldsWithoutLevel(){
        this.tfNickname.sendKeys("elLamproPeltis");
        this.cbAgent.sendKeys("Astra");
        this.cbRank.sendKeys("Hierro");
        this.tfHours.sendKeys("200");
        this.tfNote.sendKeys("Soy tu padre");
    }
    public void fillFIeldsWithoutNickname(){
        this.tfLevel.sendKeys("10");
        this.cbAgent.sendKeys("Astra");
        this.cbRank.sendKeys("Hierro");
        this.tfHours.sendKeys("200");
        this.tfNote.sendKeys("Soy tu padre");
    }




    public static void waitTest(int seconds){
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static String getInnerText(WebDriver driver, WebElement element) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        return (String) executor.executeScript("return arguments[0].innerText", element);
    }
}
