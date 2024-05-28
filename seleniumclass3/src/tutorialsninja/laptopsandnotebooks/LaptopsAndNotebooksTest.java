package tutorialsninja.laptopsandnotebooks;

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

        List<WebElement> price = driver.findElements(By.xpath("//p[@class='price']"));
        List<String> prices = new ArrayList<>();
        for(WebElement e : price){
            prices.add(e.getText());
        }
        List<String> sortedPrices = new ArrayList<>(prices);
        Collections.sort(sortedPrices);
        System.out.println(sortedPrices.equals(prices));
    }
    @Test
    public void verifyThatUserPlaceOrderSuccessfully() throws Exception {
        Actions action = new Actions(driver);
        WebElement LaptopsNotebookTab = driver.findElement(By.xpath("//nav[@id='menu']/div[2]/ul/li[2]/a"));
        action.moveToElement(LaptopsNotebookTab).click().build().perform();
        selectMenu("Show AllLaptops & Notebooks");
        driver.findElement(By.xpath("//select[@id='input-sort']/option[5]")).click();
        driver.findElement(By.xpath("//div[@id='content']/div[4]/div[2]/div/div[2]/div[1]/h4/a")).click();
        String SonyVAIO = driver.findElement(By.xpath("//div[@id='content']/div/div[2]/h1")).getText();
        String expectedMsg = "Sony VAIO";
        Assert.assertEquals(expectedMsg, SonyVAIO);
        driver.findElement(By.xpath("//*[@id='form-currency']/div/button")).click();
        driver.findElement(By.xpath("//*[@id='form-currency']/div/ul/li[2]/button")).click();
        driver.findElement(By.cssSelector("#button-cart")).click();
        String actualMsg = driver.findElement(By.cssSelector("#product-product > div.alert.alert-success.alert-dismissible")).getText();
        String expMsg = "Success: You have added Sony VAIO to your shopping cart!\n" + "×";
        Assert.assertEquals(expMsg, actualMsg);
        driver.findElement(By.xpath("//div[@id='product-product']/div[1]/a[2]")).click();
        String shoppingCartMsg = driver.findElement(By.xpath("//div[@id=\"content\"]/h1")).getText();
        String expectMsg = "Shopping Cart  (0.00kg)";
        Assert.assertEquals(expectMsg, shoppingCartMsg);

        driver.findElement(By.xpath("//div[@id='content']/form/div/table/tbody/tr/td[4]/div/input")).clear();
        driver.findElement(By.xpath("//div[@id='content']/form/div/table/tbody/tr/td[4]/div/input")).sendKeys("2");
        driver.findElement(By.xpath("//div[@id='content']/form/div/table/tbody/tr/td[4]/div/span/button[1]")).click();
        String modifiedMsg = driver.findElement(By.cssSelector("#checkout-cart > div.alert.alert-success.alert-dismissible")).getText();
        String expectedMsg2 = "Success: You have modified your shopping cart!\n" + "×";
        Assert.assertEquals(expectedMsg2, modifiedMsg);

        //verify Total amount

        String TotalAmount = driver.findElement(By.xpath("//div[@id='content']/form/div/table/tbody/tr/td[6]")).getText();
        String expectedAmount = "£1,472.45";
        Assert.assertEquals(expectedAmount, TotalAmount);
        //checkout
        driver.findElement(By.xpath("//div[@id='content']/div[3]/div[2]/a")).click();

        //verify the text Checkout & New Customer
        String CheckoutText = driver.findElement(By.xpath("//div[@id='content']/h1")).getText();
        String expectedText = "Checkout";
        Assert.assertEquals(expectedText, CheckoutText);

        /*String NewCustomerText = driver.findElement(By.xpath("//div[@id='collapse-checkout-option']/div/div/div[1]/h2")).getText();
        String expectedText2 = "New Customer";
        Assert.assertEquals(expectedText2, NewCustomerText);*/

        driver.findElement(By.xpath("//div[@id='collapse-checkout-option']/div/div/div[1]/div[2]/label/input")).click();
        driver.findElement(By.cssSelector("#button-account")).click();
        driver.findElement(By.cssSelector("#input-payment-firstname")).sendKeys("Kriti");
        driver.findElement(By.cssSelector("#input-payment-lastname")).sendKeys("Mishra");
        driver.findElement(By.cssSelector("#input-payment-email")).sendKeys("Kriti12@gmial.com");
        driver.findElement(By.cssSelector("#input-payment-telephone")).sendKeys("07854126987");
        driver.findElement(By.cssSelector("#input-payment-address-1")).sendKeys("1534A LondonRoad");
        driver.findElement(By.cssSelector("#input-payment-city")).sendKeys("London");
        driver.findElement(By.cssSelector("#input-payment-postcode")).sendKeys("SW16 4EW");
        //driver.findElement(By.cssSelector("#input-payment-country")).sendKeys("United Kingdom");
        WebElement dropdown = driver.findElement(By.cssSelector("#input-payment-zone"));
        dropdown.click();
        List<WebElement> listofRegion = driver.findElements(By.xpath("//*[@id='input-payment-zone']/option"));
        for (WebElement Region : listofRegion) {
            if (Region.getText().equalsIgnoreCase("Greater London")) {
                Region.click();
            }
        }
        driver.findElement(By.xpath("//input[@id='button-guest']")).click();
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("#collapse-shipping-method > div > p:nth-child(5) > textarea")).sendKeys("required ASAP");
        driver.findElement(By.xpath("//input[@id='button-shipping-method']")).click();
        driver.findElement(By.xpath("//div[@id='collapse-payment-method']/div/div[2]/div/input[1]")).click();
        driver.findElement(By.xpath("//input[@id='button-payment-method']")).click();
        driver.findElement(By.xpath("//input[@id='button-confirm']")).click();
        Thread.sleep(2000);
        String orderMsg = driver.findElement(By.cssSelector("#content > h1")).getText();
        String expectedMsg5= "Your order has been placed!";
        Assert.assertEquals(expectedMsg5,orderMsg);
    }
    }

