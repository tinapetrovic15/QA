package day2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

public class SwagLabsTest {
    private static String url = "https://www.saucedemo.com/";
    public WebDriver driver;
    public String driverPath = "chromedriver_win32\\chromedriver.exe";
    public String homeUrl = "https://www.saucedemo.com/inventory.html";

    @BeforeTest
    public void launchBrowser() {
        System.out.println("Launcing browser");
        System.setProperty("webdriver.chrome.driver",driverPath);
        driver = new ChromeDriver();
    }

    @Test(priority = 0)
    public void open_url() {

        driver.get(url);
        System.out.println("The page is open");
    }
    @Test(priority = 1)
    public void login(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(3000));
        WebElement element = driver.findElement(By.id("user-name"));
        element.sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/div[1]/div[2]/span")));
        Assert.assertEquals(driver.getCurrentUrl(),homeUrl);
        System.out.println("User "+"standard_user"+" is logged in");

    }
    @Test(priority = 2,dependsOnMethods = "login", groups = "verification")
    public void productVerify(){
        WebElement element = driver.findElement(By.xpath("/html/body/div/div/div/div[1]/div[2]/span"));
        element.isDisplayed();
        System.out.println("Products is on the page");

    }
    @Test(priority = 2,dependsOnMethods = "login", groups = "verification")
    public void shoppingCartVerify(){
        WebElement element = driver.findElement(By.id("shopping_cart_container"));
        element.isDisplayed();
        System.out.println("The shopping chart is on the page");
    }
    @Test(priority = 2,dependsOnMethods = "login", groups = "verification")
    public void burgerMenuVerify(){
        WebElement element = driver.findElement(By.id("react-burger-menu-btn"));
        element.isDisplayed();
        System.out.println("Burger menu is on the page");

    }
    @Test(priority = 2,dependsOnMethods = "login", groups = "verification")
    public void twitterVerify(){
        WebElement element = driver.findElement(By.xpath("//*[@id=\"page_wrapper\"]/footer/ul/li[1]"));
        element.isDisplayed();
        System.out.println("Twitter is on the page");
    }
    @Test(priority = 2,dependsOnMethods = "login", groups = "verification")
    public void facebookVerify(){
        WebElement element = driver.findElement(By.xpath("//*[@id=\"page_wrapper\"]/footer/ul/li[2]"));
        element.isDisplayed();
        System.out.println("Facebook is on the page");
    }
    @Test(priority = 2,dependsOnMethods = "login", groups = "verification")
    public void lnVerify(){
        WebElement element = driver.findElement(By.xpath("//*[@id=\"page_wrapper\"]/footer/ul/li[3]"));
        element.isDisplayed();
        System.out.println("Linkedln is on the page");
    }
    @Test(priority = 3,dependsOnGroups = "verification")
    public void logout(){
        WebElement element = driver.findElement(By.id("react-burger-menu-btn"));
        element.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(3000));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("logout_sidebar_link")));
        driver.findElement(By.id("logout_sidebar_link")).click();
        System.out.println(driver.getCurrentUrl());
        Assert.assertEquals(driver.getCurrentUrl(),url);
        System.out.println("The user is logged out");
    }
    @AfterTest
    public void terminateBrowser() {

        driver.quit();
    }




}
