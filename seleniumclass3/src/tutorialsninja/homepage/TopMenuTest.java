package tutorialsninja.homepage;

import ParaBank.browserfactory.BaseTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class TopMenuTest extends BaseTest {

    public static String baseUrl = "http://tutorialsninja.com/demo/index.php?";
    @Before
    public void setBaseUrl(){
        openBrowser(baseUrl);
    }
    public void selectMenu(String menu){
        driver.findElement(By.linkText(menu)).click();
    }
    @Test
    public void NavigateToDesktops(){
        Actions action = new Actions(driver);
        WebElement desktopsTab = driver.findElement(By.xpath("//nav[@id='menu']/div[2]/ul/li[1]/a"));
        action.moveToElement(desktopsTab).click().build().perform();
        selectMenu("Show AllDesktops");
        String actualText = driver.findElement(By.xpath("//h2[text()='Desktops']")).getText();
        String expectedText = "Desktops";
        Assert.assertTrue(expectedText.equalsIgnoreCase(actualText));
    }
    @Test
    public void NavigateToLaptopsAndNotebooks(){
        Actions action = new Actions(driver);
        WebElement LaptopsAndNotebookTab = driver.findElement(By.xpath("//nav[@id='menu']/div[2]/ul/li[2]/a"));
        action.moveToElement(LaptopsAndNotebookTab).click().build().perform();
        selectMenu("Show AllLaptops & Notebooks");
        String actualText1 = driver.findElement(By.xpath("//h2[text()='Laptops & Notebooks']")).getText();
        String expectedText1 = "Laptops & Notebooks";
        Assert.assertTrue(expectedText1.equalsIgnoreCase(actualText1));
    }
    @Test
    public void NavigateToComponents(){
        Actions action = new Actions(driver);
        WebElement ComponentsTab = driver.findElement(By.xpath("//nav[@id='menu']/div[2]/ul/li[3]/a"));
        action.moveToElement(ComponentsTab).click().build().perform();
        selectMenu("Show AllComponents");
        String actualText2 = driver.findElement(By.xpath("//h2[text()='Components']")).getText();
        String expectedText2 = "Components";
        Assert.assertTrue(expectedText2.equalsIgnoreCase(actualText2));
    }
   /* @After
    public void close(){
        closeBrowser();
    }*/
}
