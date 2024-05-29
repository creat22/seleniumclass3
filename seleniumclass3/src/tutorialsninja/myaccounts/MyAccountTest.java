package tutorialsninja.myaccounts;

import ParaBank.browserfactory.BaseTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class MyAccountTest extends BaseTest {

    public static String baseUrl = "http://tutorialsninja.com/demo/index.php?";
    @Before
    public void setup(){
        openBrowser(baseUrl);
    }
    public void selectMyAccountOptions(String option){/*
        List<WebElement> accountOptions = driver.findElements(By.cssSelector("#top-links > ul > li.dropdown.open > ul"));
        for(WebElement element : accountOptions){
            if(element.getText().equalsIgnoreCase(option));
            element.click();
        }*/
        driver.findElement(By.linkText(option)).click();
    }
    @Test
    public void verifyThatUserRegisterPageSuccessfully() throws Exception{
        Actions action = new Actions(driver);
        WebElement MyAccountTab = driver.findElement(By.xpath("//span[text()='My Account']"));
        action.moveToElement(MyAccountTab).click().build().perform();
        selectMyAccountOptions("Register");
     //verify text register account
        String registerText = driver.findElement(By.xpath("//div[@id='content']/h1")).getText();
        String expectedMsg = "Register Account";
        Assert.assertEquals(expectedMsg,registerText);
        //registration details
        driver.findElement(By.cssSelector("#input-firstname")).sendKeys("Kriti");
        driver.findElement(By.cssSelector("#input-lastname")).sendKeys("Mishra");
        driver.findElement(By.cssSelector("#input-email")).sendKeys("Kriti123@gmail.com");
        driver.findElement(By.cssSelector("#input-telephone")).sendKeys("07536214895");
        driver.findElement(By.cssSelector("#input-password")).sendKeys("Password12345");
        driver.findElement(By.cssSelector("#input-confirm")).sendKeys("Password12345");
        driver.findElement(By.xpath("//div[@id='content']/form/fieldset[3]/div/div/label[1]/input")).click();
        driver.findElement(By.cssSelector("#content > form > div > div > input[type=checkbox]:nth-child(2)")).click();
        driver.findElement(By.cssSelector("#content > form > div > div > input.btn.btn-primary")).click();
        //verify message account created
        String AccountMsg = driver.findElement(By.cssSelector("#content > h1")).getText();
        String expectedMsg2 = "Your Account Has Been Created!";
        Assert.assertEquals(expectedMsg2,AccountMsg);

        driver.findElement(By.xpath("//div[@id='content']/div/div/a")).click();
        driver.findElement(By.cssSelector("#top-links > ul > li.dropdown.open > a > span.hidden-xs.hidden-sm.hidden-md")).click();
        selectMyAccountOptions("Logout");
        //verify Logout text
        String logoutText = driver.findElement(By.cssSelector("#content > h1")).getText();
        String expectedMsg3 = "Account Logout";
        Assert.assertEquals(expectedMsg3,logoutText);
        driver.findElement(By.xpath("//div[@id='content']/div/div/a")).click();
    }
    @Test
    public void verifyThatUserShouldLoginAndLogoutSuccessfully() throws Exception{
        WebElement dropdown = driver.findElement(By.xpath("//span[text()='My Account']"));
        dropdown.click();
        selectMyAccountOptions("Login");
        driver.findElement(By.cssSelector("#input-email")).sendKeys("Kriti123@gmail.com");
        driver.findElement(By.cssSelector("#input-password")).sendKeys("Password12345");
        driver.findElement(By.xpath("//div[@id='content']/div/div[2]/div/form/input")).click();
        //verify text MyAccount
        String MyAccountText = driver.findElement(By.cssSelector("#content > h2:nth-child(1)")).getText();
        String expectedMsg4 = "My Account";
        Assert.assertEquals(expectedMsg4,MyAccountText);

        driver.findElement(By.xpath("//span[text()='My Account']")).click();
        selectMyAccountOptions("Logout");
        //verify logOut Text

        String logOutText = driver.findElement(By.cssSelector("#content > h1")).getText();
        String expectMsg5 = "Account Logout";
        Assert.assertEquals(expectMsg5,logOutText);
        driver.findElement(By.xpath("//div[@id='content']/div/div/a")).click();
    }
    /*@After
    public void teardown(){
        closeBrowser();
    }*/

}
