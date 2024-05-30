package nopCommerce.computer;

import nopCommerce.homepage.TopMenuTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestSuite extends TopMenuTest {
    public static String baseUrl ="https://demo.nopcommerce.com/";

    @Before
    public void setUp(){
        openBrowser(baseUrl);
    }
    @Test
    public void verifyProductArrangeInAlphaBaticalOrder(){
        Actions action = new Actions(driver);
        WebElement ComputerTab = driver.findElement(By.xpath("/html/body/div[6]/div[2]/ul[1]/li[1]/a"));
        action.moveToElement(ComputerTab).click().build().perform();
        selectMenu("Desktops");
        WebElement sortByDropdown = driver.findElement(By.cssSelector("#products-orderby"));
        sortByDropdown.click();
        driver.findElement(By.cssSelector("#products-orderby > option:nth-child(3)")).click();

        List<WebElement> listOfProducts = driver.findElements(By.cssSelector(".product-title a"));
        List<String> obtainedList = new ArrayList<>();
        for(WebElement products:listOfProducts){
            obtainedList.add(products.getText());}
        List<String> sortedList = new ArrayList<>(obtainedList);
        Collections.sort(sortedList, Collections.reverseOrder());
        //assert obtainedList.equals(sortedList);
        System.out.println(obtainedList);
        System.out.println(sortedList);}
    @Test
    public void verifyProductAddedToShoppingCartSuccessFully() throws InterruptedException {
        Actions action = new Actions(driver);
        WebElement ComputerTab = driver.findElement(By.xpath("/html/body/div[6]/div[2]/ul[1]/li[1]/a"));
        action.moveToElement(ComputerTab).click().build().perform();
        selectMenu("Desktops");
        WebElement sortByDropdown = driver.findElement(By.cssSelector("#products-orderby"));
        sortByDropdown.click();
        driver.findElement(By.cssSelector("#products-orderby > option:nth-child(2)")).click();
        Thread.sleep(5000);
        WebElement buildOwn = driver.findElement(By.xpath("//*[@id='main']/div/div[3]/div/div[2]/div[2]/div[2]/div/div/div[1]/div/div[2]/div[3]/div[2]/button[1]"));
        buildOwn.click();
        String BuildComputerText = driver.findElement(By.xpath("//form[@id='product-details-form']/div/div[1]/div[2]/div[1]/h1")).getText();
        String expectedMsg = "Build your own computer";
        Assert.assertEquals(expectedMsg,BuildComputerText);
        //select product using select class

        Select processorSelect = new Select(driver.findElement(By.cssSelector("#product_attribute_1")));
        processorSelect.selectByVisibleText("2.2 GHz Intel Pentium Dual-Core E2200");

        Select RAMSelect = new Select(driver.findElement(By.xpath("//*[@id='product_attribute_2']")));
        RAMSelect.selectByVisibleText("8GB [+$60.00]");

        driver.findElement(By.xpath("//*[@id='product_attribute_3_7']")).click();
        driver.findElement(By.xpath("//*[@id='product_attribute_4_9']")).click();
        driver.findElement(By.xpath("//*[@id='product_attribute_5_12']")).click();
        //verify the price
        /*String priceAmount = driver.findElement(By.xpath("//*[@id='price-value-1']")).getText();
        String expectedMsg2 = "$1,475.00";
        Assert.assertEquals(expectedMsg2,priceAmount);*/

        driver.findElement(By.xpath("//button[@id='add-to-cart-button-1']")).click();
        Thread.sleep(3000);

        //verify the shoppingcart message
        String shoppingCartMsg = driver.findElement(By.cssSelector("#bar-notification > div > p")).getText();
        String expectedMsg3 = "The product has been added to your shopping cart";
        Assert.assertEquals(expectedMsg3,shoppingCartMsg);

        //Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id='bar-notification']/div/span")).click();
        WebElement shoppingCart = driver.findElement(By.cssSelector("#topcartlink > a > span.cart-label"));
        action.moveToElement(shoppingCart).click().build().perform();
        WebElement GoToCartButton = driver.findElement(By.cssSelector("#flyout-cart > div > div.buttons > button"));
        GoToCartButton.click();

        //verify ShoppingCartText
        String shoppingCartText = driver.findElement(By.xpath("//div[@id='main']/div/div/div/div[1]/h1")).getText();
        String expectedMsg5 = "Shopping cart";
        Assert.assertEquals(expectedMsg5,shoppingCartText);
        driver.findElement(By.cssSelector("#quantity-up-11231")).click();

        Thread.sleep(2000);
        //verify the total amount
        String totalAmount = driver.findElement(By.xpath("//*[@id='shopping-cart-form']/div[1]/table/tbody/tr/td[6]/span")).getText();
        String expectedMsg6 = "$2,950.00";
        Assert.assertEquals(expectedMsg6,totalAmount);

        driver.findElement(By.cssSelector("#termsofservice")).click();
        driver.findElement(By.cssSelector("#checkout")).click();

        //verify signIn message
        String signInMsg = driver.findElement(By.xpath("//div[@id='main']/div/div/div/div[1]/h1")).getText();
        String expectedMsg7 = "Welcome, Please Sign In!";
        Assert.assertEquals(expectedMsg7,signInMsg);

        driver.findElement(By.xpath("//div[@id='main']/div/div/div/div[2]/div[1]/div[1]/div[3]/button[1]")).click();
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("#BillingNewAddress_FirstName")).sendKeys("Kriti");
        driver.findElement(By.cssSelector("#BillingNewAddress_LastName")).sendKeys("Mishra");
        driver.findElement(By.cssSelector("#BillingNewAddress_Email")).sendKeys("kriti12@gmail.com");
        WebElement dropDown = driver.findElement(By.cssSelector("#BillingNewAddress_CountryId"));
        dropDown.click();
        List<WebElement> listOfCountry = driver.findElements(By.xpath("//*[@id='BillingNewAddress_CountryId']/option"));
        for(WebElement countries:listOfCountry){
            if(countries.getText().equalsIgnoreCase("United Kingdom")){
                countries.click();}
        }
        driver.findElement(By.cssSelector("#BillingNewAddress_City")).sendKeys("London");
        driver.findElement(By.cssSelector("#BillingNewAddress_Address1")).sendKeys("1534A London Road");
        driver.findElement(By.cssSelector("#BillingNewAddress_ZipPostalCode")).sendKeys("SW16 4EU");
        driver.findElement(By.cssSelector("#BillingNewAddress_PhoneNumber")).sendKeys("07842165487");
        driver.findElement(By.xpath("//div[@id='billing-buttons-container']/button[2]")).click();

        Thread.sleep(2000);
        driver.findElement(By.cssSelector("#shippingoption_1")).click();
        driver.findElement(By.xpath("//div[@id='shipping-method-buttons-container']/button")).click();
        driver.findElement(By.cssSelector("#paymentmethod_1")).click();
        driver.findElement(By.xpath("//div[@id='payment-method-buttons-container']/button")).click();

        Thread.sleep(2000);
        Select creditCard = new Select(driver.findElement(By.cssSelector("#CreditCardType")));
        creditCard.selectByVisibleText("Master card");
        driver.findElement(By.cssSelector("#CardholderName")).sendKeys("Miss Kriti Mishra");
        driver.findElement(By.cssSelector("#CardNumber")).sendKeys("4242424242424242");
        driver.findElement(By.cssSelector("#ExpireMonth")).sendKeys("1");
        driver.findElement(By.cssSelector("#ExpireYear")).sendKeys("2025");
        driver.findElement(By.cssSelector("#CardCode")).sendKeys("564");
        driver.findElement(By.xpath("//div[@id='payment-info-buttons-container']/button")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//div[@id='confirm-order-buttons-container']/button")).click();

        //verify the message & text
        String thankYouText = driver.findElement(By.xpath("//*[@id='main']/div/div/div/div[1]/h1")).getText();
        String expectedText = "Thank you";
        Assert.assertEquals(expectedText,thankYouText);

        String processedMsg = driver.findElement(By.xpath("//*[@id='main']/div/div/div/div[2]/div/div[1]/strong")).getText();
        String expectedMsg8 ="Your order has been successfully processed!";
        Assert.assertEquals(expectedMsg8,processedMsg);

        driver.findElement(By.xpath("//*[@id='main']/div/div/div/div[2]/div/div[3]/button")).click();
        Thread.sleep(2000);
        //verify welcome store
        String welcomeMsg = driver.findElement(By.xpath("//*[@id='main']/div/div/div/div/div[2]/div[1]/h2")).getText();
        String expectedMsg9 = "Welcome to our store";
        Assert.assertEquals(expectedMsg9,welcomeMsg);
    }
}
