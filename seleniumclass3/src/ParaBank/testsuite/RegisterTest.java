package ParaBank.testsuite;

import ParaBank.browserfactory.BaseTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class RegisterTest extends BaseTest {

    public static String basUrl = "https://parabank.parasoft.com/parabank/index.htm";

    @Before
    public void setUp(){
        openBrowser(basUrl);
    }
    @Test
    public void registerAccount() throws InterruptedException {
        WebElement Register = driver.findElement(By.xpath("//div[@id='loginPanel']/p[2]/a"));
        Register.click();
        String actualTitle = driver.findElement(By.xpath("//h1[@class='title']")).getText();
        String expectingTitle ="Signing up is easy!";
        Assert.assertEquals("Verify the text",actualTitle,expectingTitle);
        System.out.println(actualTitle);
        WebElement firstName = driver.findElement(By.id("customer.firstName"));
        firstName.sendKeys("Kriti");
        WebElement lastName = driver.findElement(By.id("customer.lastName"));
        lastName.sendKeys("Senon");
        WebElement address= driver.findElement(By.id("customer.address.street"));
        address.sendKeys("75 Eastcote Lne");
        WebElement city = driver.findElement(By.id("customer.address.city"));
        city.sendKeys("Harrow");
        WebElement state = driver.findElement(By.id("customer.address.state"));
        state.sendKeys("Middlesex");
        WebElement zipCode = driver.findElement(By.id("customer.address.zipCode"));
        zipCode.sendKeys("SW16 4EU");
        WebElement phoneNUmber = driver.findElement(By.id("customer.phoneNumber"));
        phoneNUmber.sendKeys("07862451374");
        WebElement ssn = driver.findElement(By.id("customer.ssn"));
        ssn.sendKeys("123-45-6789");
        WebElement usernameField = driver.findElement(By.id("customer.username"));
        usernameField.sendKeys("Kriti12");
        WebElement passwordField = driver.findElement(By.id("customer.password"));
        passwordField.sendKeys("Pass12345");
        WebElement passwordConfirm = driver.findElement(By.id("repeatedPassword"));
        passwordConfirm.sendKeys("Pass12345");
        WebElement registerLink = driver.findElement(By.cssSelector("#customerForm > table > tbody > tr:nth-child(13) > td:nth-child(2) > input"));
        registerLink.click();

       Thread.sleep(4000);

        String actualMessage = driver.findElement(By.xpath("////div[@id='rightPanel']/p")).getText();
        String expectedMessage = "Your account was created successfully. You are now logged in.";
        Assert.assertEquals("Verify Welcome Message",expectedMessage,actualMessage);

       // driver.quit();


    }
}
