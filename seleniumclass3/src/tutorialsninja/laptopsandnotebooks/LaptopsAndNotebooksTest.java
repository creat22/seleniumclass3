package tutorialsninja.laptopsandnotebooks;

import ParaBank.browserfactory.BaseTest;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class LaptopsAndNotebooksTest extends BaseTest {

    public static String baseUrl = "http://tutorialsninja.com/demo/index.php?";
    @Before

    public void setUp(){
        openBrowser(baseUrl);}
    public void selectMenu(String menu){
        driver.findElement(By.linkText(menu)).click();
    }
    @Test
    public void verifyProductsPriceDisplayHighToLowSuccessfully(){
        Actions action = new Actions(driver);
        WebElement LaptopsNotebookTab = driver.findElement(By.xpath("//nav[@id='menu']/div[2]/ul/li[2]/a"));
        action.moveToElement(LaptopsNotebookTab).click().build().perform();
        selectMenu("Show AllLaptops & Notebooks");
        driver.findElement(By.xpath("//select[@id='input-sort']/option[5]")).click();

        List<WebElement> listOfProduct = driver.findElements(By.xpath())

    }
}
