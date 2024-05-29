package ParaBank.browserfactory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class BaseTest {

    public static WebDriver driver;

    public void openBrowser(String baseUrl){
        driver = new ChromeDriver();
        driver.get(baseUrl);
        String title = driver.getCurrentUrl();
        System.out.println(title);

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(3000));
    }

    public void enterCredentialsAndLogin(String username, String password){
        driver.findElement(By.name("username")).sendKeys(username);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.cssSelector("#loginPanel > form > div:nth-child(5) > input")).click();
    }
 /*   @After
    public void closeBrowser(){driver.quit();}*/


}
