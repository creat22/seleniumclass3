package nopCommerce.homepage;

import ParaBank.browserfactory.BaseTest;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class TopMenuTest extends BaseTest {
    public static String baseUrl = "https://demo.nopcommerce.com/";
    @Before
    public void setUp(){
        openBrowser(baseUrl);
    }
    public void selectMenu(String menu){
         driver.findElement(By.linkText(menu)).click();
    }
    @Test
    public void verifyPageNavigation(){
        Actions action = new Actions(driver);
        WebElement ComputerTab = driver.findElement(By.xpath("/html/body/div[6]/div[2]/ul[1]/li[1]/a"));
        action.moveToElement(ComputerTab).click().build().perform();
        selectMenu("Software");
    }
   /* @After
    public void tearDown(){
        closeBrowser();
    }*/
}
