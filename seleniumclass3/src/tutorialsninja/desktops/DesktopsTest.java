package tutorialsninja.desktops;

import ParaBank.browserfactory.BaseTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DesktopsTest extends BaseTest {

    public static String baseUrl = "https://tutorialsninja.com/demo/index.php";

    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }

    @Test
    public void verifyProductArrangeInAlphaBaticalOrder() {
        Actions action = new Actions(driver);
        WebElement desktopsTab = driver.findElement(By.xpath("//nav[@id='menu']/div[2]/ul/li[1]/a"));
        WebElement showAllDesktops = driver.findElement(By.xpath("//nav[@id='menu']/div[2]/ul/li[1]/div/a"));
        action.moveToElement(desktopsTab).moveToElement(showAllDesktops).click().build().perform();

        WebElement sortByDropdown = driver.findElement(By.id("input-sort"));
        sortByDropdown.findElement(By.xpath("//select[@id='input-sort']/option[3]")).click();

        List<WebElement> ListOfProduct = driver.findElements(By.xpath("//h4/a"));

        List<String> obtainedList = new ArrayList<>();
        for (WebElement product : ListOfProduct) {
            obtainedList.add(product.getText());
        }
        List<String> sortedList = new ArrayList<>(obtainedList);
        Collections.sort(sortedList, Collections.reverseOrder());

        System.out.println(obtainedList);
        System.out.println(sortedList);

        //Assert.assertEquals(obtainedList,sortedList);
        driver.quit();
    }

    @Test
    public void verifyProductAddedToShoppingCartSuccessfully() throws InterruptedException {
        Actions action = new Actions(driver);
        WebElement desktopsTab = driver.findElement(By.xpath("//nav[@id='menu']/div[2]/ul/li[1]/a"));
        WebElement showAllDesktops = driver.findElement(By.xpath("//nav[@id='menu']/div[2]/ul/li[1]/div/a"));
        action.moveToElement(desktopsTab).moveToElement(showAllDesktops).click().build().perform();
        WebElement sortByDropdown = driver.findElement(By.id("input-sort"));
        sortByDropdown.findElement(By.xpath("//select[@id='input-sort']/option[2]")).click();
        driver.findElement(By.xpath("//div[@id='content']/div[4]/div[3]/div/div[2]/div[1]/h4/a")).click();
        driver.findElement(By.xpath("//*[@id='content']/div/div[2]/h1")).isDisplayed();

        String month = "November 2022";
        String day = "30";
        driver.findElement(By.xpath("//div[@id='product']/div[1]/div/span/button")).click();
        Thread.sleep(7000);

        while (true) {
            String text = driver.findElement(By.xpath("/html/body/div[3]/div/div[1]/table/thead/tr[1]/th[2]")).getText();

            if (text.equals(month)) {
                break;
            } else {
                driver.findElement(By.xpath("/html/body/div[3]/div/div[1]/table/thead/tr[1]/th[3]")).click();
            }
        }
        driver.findElement(By.xpath("/html/body/div[3]/div/div[1]/table/tbody/tr/td[contains(text()," + day + ")]")).click();
        driver.findElement(By.id("input-quantity")).clear();
        driver.findElement(By.id("input-quantity")).sendKeys("1");
        driver.findElement(By.id("button-cart")).click();

        Thread.sleep(7000);

        String actualMessage = driver.findElement(By.xpath("/html/body/div[2]/div[1]")).getText();
        String expectedMessage = "Success: You have added HP LP3065 to your shopping cart!\n " + "×";
        Assert.assertEquals("Verify message",expectedMessage, actualMessage);
        driver.findElement(By.xpath("//a[text()='shopping cart']")).click();

        String actualMessage1 = driver.findElement(By.xpath("//div[@id='content']/h1")).getText();
        String expectedMessage1 = "Shopping Cart  (1.00kg)";
        Assert.assertTrue(expectedMessage1.equalsIgnoreCase(actualMessage1));

        String actualMessage2 = driver.findElement(By.xpath("////div[@id='content']/form/div/table/tbody/tr/td[2]/a")).getText();
        String expectedMessage2 = "HP LP3065";
        Assert.assertTrue(expectedMessage2.equalsIgnoreCase(actualMessage2));

        String actualMessage3 = driver.findElement(By.xpath("//div[@id='content']/form/div/table/tbody/tr/td[2]/small[1]")).getText();
        String expectedMessage3 = "Delivery Date:2011-04-22";
        Assert.assertTrue(expectedMessage3.equalsIgnoreCase(actualMessage3));

        String actualMessage4 = driver.findElement(By.xpath("//*[@id='content']/form/div/table/tbody/tr/td[3]")).getText();
        String expectedMessage4 = "Product 21";
        Assert.assertTrue(expectedMessage4.equalsIgnoreCase(actualMessage4));

        String actualMessage5 = driver.findElement(By.xpath("//div[@id='content']/form/div/table/tbody/tr/td[6]")).getText();
        String expectedMessage5 = "£74.73";
        Assert.assertTrue(expectedMessage5.equalsIgnoreCase(actualMessage5));
    }
    /*@After

    public void close(){
        closeBrowser();
    }*/
    }

