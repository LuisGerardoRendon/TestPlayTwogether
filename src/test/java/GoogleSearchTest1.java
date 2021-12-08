import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class GoogleSearchTest1 {
    private WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty(
                "webdriver.chrome.driver",
                "./src/test/resources/chromedriver.exe");


        this.driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.pjeveracruz.gob.mx/pjev/listasJuzgados");
    }

    @Test
    public void testGooglePage() {
        WebElement distrito = driver.findElement(By.id("distrito"));
        WebElement juzgado = driver.findElement(By.id("juzgado"));
        WebElement acuerdo = driver.findElement(By.id("tipoAcuerdo"));
        WebElement numero = driver.findElement(By.id("numero"));
        WebElement anio = driver.findElement(By.id("anio"));
        WebElement botonBuscar = driver.findElement(By.id("buscar"));


        distrito.sendKeys("XALAPA");
        WebDriverWait timer = new WebDriverWait(driver, 15);
        juzgado.sendKeys("JUZGADO SEXTO DE PRIMERA INSTANCIA");
        acuerdo.sendKeys("EXPEDIENTE");
        numero.sendKeys("0001");
        anio.sendKeys(" 2014");
        botonBuscar.click();



    }


}