import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SearchTest {
    private WebDriver driver;
    private WebElement tfNickname;
    private WebElement cbSchedule;
    private WebElement cbGames;
    private WebElement cbGender;
    private WebElement tfAge;
    private WebElement btnGeneralSearch;
    private WebElement btnNicknameSearch;
    private  WebElement warningNickname;

    private WebElement lbTittlePlayerList;

    @Before
    public void setUp() {
        System.setProperty(
                "webdriver.chrome.driver",
                "./src/test/resources/chromedriver.exe");

        this.driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(Config.searchRute);
        tfNickname = driver.findElement(By.id("tfNickname"));
        cbSchedule = driver.findElement(By.id("cbSchedule"));
        cbGames = driver.findElement(By.id("cbGames"));
        cbGender = driver.findElement(By.id("cbGender"));



        tfAge = driver.findElement(By.id("tfAge"));
        btnGeneralSearch = driver.findElement(By.id("btnGeneralSearch"));
        btnNicknameSearch = driver.findElement(By.id("btnSearchByNickname"));

    }

    @Test
    public void testInputsEmpty() {
        waitTest(3);
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click()", btnGeneralSearch);
        boolean playersFound = goToPlayerList();
        assertTrue(playersFound);
        driver.close();

    }

    @Test
    public void testInputNicknameEmpty(){
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click()", btnNicknameSearch);
        waitTest(1);
        warningNickname = driver.findElement(By.id("warningNickname"));
        String warningMessage = getInnerText(driver, warningNickname);
        assertEquals("Escriba un nickname por favor", warningMessage);
        driver.close();
    }

    @Test
    public void testInputNickname(){
        tfNickname.sendKeys("efra");
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click()", btnNicknameSearch);
        waitTest(1);
        boolean passToPlayerList = goToPlayerList();
        assertTrue(passToPlayerList);
        driver.close();
    }

    @Test
    public void testOnlyGender() {
        selectGender();
        WebDriverWait timer = new WebDriverWait(driver, 10);
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click()", btnGeneralSearch);
        boolean playersFound = goToPlayerList();
        assertTrue(playersFound);
        driver.close();

    }

    @Test
    public void testOnlyAge() {
        selectMinAge();
        WebDriverWait timer = new WebDriverWait(driver, 10);
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click()", btnGeneralSearch);
        boolean playersFound = goToPlayerList();
        assertTrue(playersFound);
        driver.close();

    }

    @Test
    public void testOnlySechedule() {
        selectSchedule();
        waitTest(3);
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click()", btnGeneralSearch);
        boolean playersFound = goToPlayerList();
        assertTrue(playersFound);
        driver.close();
    }

    @Test
    public void testOnlyGame() {
        selectGame();
        waitTest(3);
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click()", btnGeneralSearch);
        boolean playersFound = goToPlayerList();
        assertTrue(playersFound);
        driver.close();

    }


    @Test
    public void testAllFilters() {
        selectMinAge();
        selectSchedule();
        selectGame();
        selectGender();
        waitTest(3);
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click()", btnGeneralSearch);
        boolean playersFound = goToPlayerList();
        assertTrue(playersFound);
        driver.close();

    }

    @Test
    public void testWithoutMinAge() {
        selectSchedule();
        selectGame();
        selectGender();
        waitTest(3);
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click()", btnGeneralSearch);
        boolean playersFound = goToPlayerList();
        assertTrue(playersFound);
        driver.close();

    }
    @Test
    public void testWithoutSchedule() {
        selectMinAge();
        selectGame();
        selectGender();
        waitTest(3);
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click()", btnGeneralSearch);
        boolean playersFound = goToPlayerList();
        assertTrue(playersFound);
        driver.close();

    }
    @Test
    public void testWithoutGame() {
        selectMinAge();
        selectSchedule();
        selectGender();
        waitTest(3);
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click()", btnGeneralSearch);
        boolean playersFound = goToPlayerList();
        assertTrue(playersFound);
        driver.close();

    }
    @Test
    public void testWithoutGender() {
        selectMinAge();
        selectSchedule();
        selectGame();
        waitTest(3);
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click()", btnGeneralSearch);
        boolean playersFound = goToPlayerList();
        assertTrue(playersFound);
        driver.close();

    }

    @Test
    public void testWithoutMinAgeAndSchedule() {
        selectGame();
        selectGender();
        waitTest(3);
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click()", btnGeneralSearch);
        boolean playersFound = goToPlayerList();
        assertTrue(playersFound);
        driver.close();

    }
    @Test
    public void testWithoutScheduleAndGame() {
        selectMinAge();
        selectGender();
        waitTest(3);
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click()", btnGeneralSearch);
        boolean playersFound = goToPlayerList();
        assertTrue(playersFound);
        driver.close();

    }
    @Test
    public void testWithoutGameAndGender() {
        selectMinAge();
        selectSchedule();
        waitTest(3);
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click()", btnGeneralSearch);
        boolean playersFound = goToPlayerList();
        assertTrue(playersFound);
        driver.close();

    }






    public void selectSchedule(){
        cbSchedule.sendKeys("Hora pico");
    }
    public void selectGame(){
        cbGames.sendKeys("lol");
    }
    public void selectGender(){
        cbGender.sendKeys("Femenino");
    }
    public void selectMinAge(){
        tfAge.sendKeys("23");
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
    public boolean goToPlayerList(){
        waitTest(3);
        boolean playersFound;
        try {
            lbTittlePlayerList = driver.findElement(By.id("lbTittle"));
            playersFound = true;
        }catch (NoSuchElementException e){
            playersFound = false;
        }
        return  playersFound;
    }



}
