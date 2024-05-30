package nopCommerce.Electronics;

import nopCommerce.homepage.TopMenuTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class ElectronicTest extends TopMenuTest {

    public static String baseUrl = "https://demo.nopcommerce.com/";

    @Before
    public void setUp(){
        openBrowser(baseUrl);
    }
    @Test
    public void verifyUserShouldNavigateToCellPhonesPage(){
        Actions action = new Actions(driver);
        WebElement electronicsTab = driver.findElement(By.xpath("/html/body/div[6]/div[2]/ul[1]/li[2]/a"));
        action.moveToElement(electronicsTab).click().build().perform();
        selectMenu("Cell phones");
    }
    @Test
    public void verifyThatTheProductAddedAndPlaceOrderSuccessfully() throws InterruptedException {
        verifyUserShouldNavigateToCellPhonesPage();
        String CellPhoneText = driver.findElement(By.xpath("//div[@id='main']/div/div[3]/div/div[1]/h1")).getText();
        String expectedText = "Cell phones";
        Assert.assertEquals(expectedText,CellPhoneText);
        driver.findElement(By.xpath("//div[@id='main']/div/div[3]/div/div[2]/div[1]/div[1]/a[2]")).click();
        Thread.sleep(4000);

        driver.findElement(By.xpath("//div[@id='main']/div/div[3]/div/div[2]/div[2]/div[2]/div/div/div[3]/div/div[2]/h2/a")).click();
        String NokiaLumia1020 = driver.findElement(By.xpath("//*[@id='product-details-form']/div/div[1]/div[2]/div[1]/h1")).getText();
        String expectedText2 = "Nokia Lumia 1020";
        Assert.assertEquals(expectedText2,NokiaLumia1020);
        String priceTag = driver.findElement(By.cssSelector("#price-value-20")).getText();
        String expectedPrice = "$349.00";
        Assert.assertEquals(expectedPrice,priceTag);
        driver.findElement(By.cssSelector("#product_enteredQuantity_20")).clear();
        driver.findElement(By.cssSelector("#product_enteredQuantity_20")).sendKeys("2");
        driver.findElement(By.cssSelector("#add-to-cart-button-20")).click();
        Thread.sleep(3000);

        String shoppingCartMsg = driver.findElement(By.cssSelector("#bar-notification > div > p")).getText();
        String expectedMsg3 = "The product has been added to your shopping cart";
        Assert.assertEquals(expectedMsg3,shoppingCartMsg);

        driver.findElement(By.xpath("//*[@id='bar-notification']/div/span")).click();
        Actions action = new Actions(driver);
        WebElement shoppingCart = driver.findElement(By.cssSelector("#topcartlink > a > span.cart-label"));
        action.moveToElement(shoppingCart).click().build().perform();
        WebElement GoToCartButton = driver.findElement(By.cssSelector("#flyout-cart > div > div.buttons > button"));
        GoToCartButton.click();
        //verification of shopping cart
        String shoppingCartText = driver.findElement(By.xpath("//div[@id='main']/div/div/div/div[1]/h1")).getText();
        String expectedMsg5 = "Shopping cart";
        Assert.assertEquals(expectedMsg5,shoppingCartText);

        String totalPrice = driver.findElement(By.xpath("//*[@id='shopping-cart-form']/div[1]/table/tbody/tr/td[6]/span")).getText();
        String expectedTotal = "$698.00";
        Assert.assertEquals(expectedTotal,totalPrice);

       /* String quantity = driver.findElement(By.xpath("//*[@id='itemquantity11228']")).getText();
        String expectedQuantity = "2";
        Assert.assertEquals(expectedQuantity,quantity);*/
        driver.findElement(By.cssSelector("#termsofservice")).click();
        driver.findElement(By.cssSelector("#checkout")).click();

        //verify signIn message
        String signInMsg = driver.findElement(By.xpath("//div[@id='main']/div/div/div/div[1]/h1")).getText();
        String expectedMsg7 = "Welcome, Please Sign In!";
        Assert.assertEquals(expectedMsg7,signInMsg);
        driver.findElement(By.xpath("//*[@id='main']/div/div/div/div[2]/div[1]/div[1]/div[3]/button[2]")).click();
        driver.findElement(By.xpath("//input[@id='FirstName']")).sendKeys("Peter");
        driver.findElement(By.xpath("//input[@id='LastName']")).sendKeys("Pan");
        driver.findElement(By.xpath("//input[@id='Email']")).sendKeys("peterpan123@gmail.com");
        driver.findElement(By.xpath("//input[@name='Password']")).sendKeys("Password");
        driver.findElement(By.xpath("//input[@id='ConfirmPassword']")).sendKeys("Password");
        driver.findElement(By.xpath("//button[@name='register-button']")).submit();
        String actualMessage = driver.findElement(By.xpath("//div[@class='result']")).getText();
        String expectingMessage = "Your registration completed";
        Assert.assertEquals("Validating Text",expectingMessage,actualMessage);
        driver.findElement(By.xpath("//*[@id='main']/div/div/div/div[2]/div[2]/a")).click();
        driver.findElement(By.cssSelector("#termsofservice")).click();
        driver.findElement(By.cssSelector("#checkout")).click();
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("#BillingNewAddress_FirstName")).sendKeys("Peter");
        driver.findElement(By.cssSelector("#BillingNewAddress_LastName")).sendKeys("Pan");
        driver.findElement(By.cssSelector("#BillingNewAddress_Email")).sendKeys("peterpan123@gmail.com");
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
        driver.findElement(By.cssSelector("#shippingoption_1")).click();
        driver.findElement(By.xpath("//div[@id='shipping-method-buttons-container']/button")).click();
        driver.findElement(By.cssSelector("#paymentmethod_1")).click();
        driver.findElement(By.xpath("//div[@id='payment-method-buttons-container']/button")).click();

        Thread.sleep(2000);
        Select creditCard = new Select(driver.findElement(By.cssSelector("#CreditCardType")));
        creditCard.selectByVisibleText("Master card");
        driver.findElement(By.cssSelector("#CardholderName")).sendKeys("Mr Peter Pan");
        driver.findElement(By.cssSelector("#CardNumber")).sendKeys("4242424242424242");
        driver.findElement(By.cssSelector("#ExpireMonth")).sendKeys("1");
        driver.findElement(By.cssSelector("#ExpireYear")).sendKeys("2025");
        driver.findElement(By.cssSelector("#CardCode")).sendKeys("564");
        driver.findElement(By.xpath("//div[@id='payment-info-buttons-container']/button")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//div[@id='confirm-order-buttons-container']/button")).click();

        //verify the message & text
        String thankYouText = driver.findElement(By.xpath("//*[@id='main']/div/div/div/div[1]/h1")).getText();
        String expectedText5 = "Thank you";
        Assert.assertEquals(expectedText5,thankYouText);

        String processedMsg = driver.findElement(By.xpath("//*[@id='main']/div/div/div/div[2]/div/div[1]/strong")).getText();
        String expectedMsg8 ="Your order has been successfully processed!";
        Assert.assertEquals(expectedMsg8,processedMsg);

        driver.findElement(By.xpath("//*[@id='main']/div/div/div/div[2]/div/div[3]/button")).click();
        Thread.sleep(2000);
        //verify welcome store
        String welcomeMsg = driver.findElement(By.xpath("//*[@id='main']/div/div/div/div/div[2]/div[1]/h2")).getText();
        String expectedMsg9 = "Welcome to our store";
        Assert.assertEquals(expectedMsg9,welcomeMsg);
        driver.findElement(By.xpath("/html/body/div[6]/div[1]/div[1]/div[2]/div[1]/ul/li[2]/a"));



    }
}
