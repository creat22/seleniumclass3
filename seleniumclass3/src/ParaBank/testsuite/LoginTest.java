package ParaBank.testsuite;

import ParaBank.browserfactory.BaseTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LoginTest extends BaseTest {

    public static String baseUrl = "https://parabank.parasoft.com/parabank/index.htm";

    @Before
    public void setupTest() {
        openBrowser(baseUrl);
    }

    @Test
    public void loginWithValidCredentials() {
        enterCredentialsAndLogin("Kriti12", "Pass12345");
        WebElement accountOverview = driver.findElement(By.xpath("//div[@id='leftPanel']/ul/li[2]/a"));
        accountOverview.click();
        System.out.println(accountOverview.isDisplayed());

    }

    @Test
    public void verifyErrorMessage() {
        enterCredentialsAndLogin("xyz123", "Pass12345");
        String actualMessage = driver.findElement(By.xpath("//div[@id='rightPanel']/p")).getText();
        String expectedMessage = "The username and password could not be verified.";
        Assert.assertEquals("Verify Error Message", actualMessage, expectedMessage);

    }

    @Test
    public void logOutSuccessfully() {
        enterCredentialsAndLogin("Harry22", "Password12345");
        WebElement logOut = driver.findElement(By.xpath("//div[@id='leftPanel']/ul/li[8]/a"));
        logOut.click();
        WebElement CustomerLogin = driver.findElement(By.xpath("//div[@id='leftPanel']/h2"));
        System.out.println(CustomerLogin.isDisplayed());
    }

  /*  @After
    public void close() {
        closeBrowser();
    }*/
}
