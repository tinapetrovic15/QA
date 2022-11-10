package day3;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

public class SwagLabsTest2 {
    private static String url = "https://www.saucedemo.com/";
    public WebDriver driver;
    public String driverPath = "C:\\Users\\tpetrovic\\Desktop\\QA\\chromedriver_win32\\chromedriver.exe";
    public String homeUrl = "https://www.saucedemo.com/inventory.html";
    public String sauceLabsBackpackUrl = "https://www.saucedemo.com/inventory-item.html?id=4";
    public String checkOutUrl = "https://www.saucedemo.com/checkout-step-one.html";
    public String finishUrl = "https://www.saucedemo.com/checkout-step-two.html";
    public String shoppingCartUrl = "https://www.saucedemo.com/cart.html";
    public String completeUrl = "https://www.saucedemo.com/checkout-complete.html";

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
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"inventory_container\"]/div")));
        Assert.assertEquals(driver.getCurrentUrl(),homeUrl);
        System.out.println("User "+"standard_user"+" is logged in");
    }
    @Test(priority = 2)
    public void sauceLabsBackpack(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(3000));
        WebElement element = driver.findElement(By.id("item_4_title_link"));

        element.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"inventory_item_container\"]/div/div/div[2]/div[1]")));
        Assert.assertEquals(driver.getCurrentUrl(),sauceLabsBackpackUrl);

        System.out.println("The Sauce Labs Backpack page is open");

        driver.findElement(By.xpath("//*[@id=\"inventory_item_container\"]/div/div/div[2]/div[1]"));
        System.out.println("Title is on the page");

        driver.findElement(By.xpath("//*[@id=\"inventory_item_container\"]/div/div/div[2]/div[2]"));
        System.out.println("Description is on the page");

        driver.findElement(By.xpath("//*[@id=\"inventory_item_container\"]/div/div/div[2]/div[3]"));
        System.out.println("Price is on the page");

    }
    @Test(priority = 3)
    public void sauceLabsBackpackAddCart(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(3000));
        WebElement addCartBtn = driver.findElement(By.xpath("//*[@id=\"add-to-cart-sauce-labs-backpack\"]"));

        addCartBtn.click();
        driver.findElement(By.xpath("//*[@id=\"remove-sauce-labs-backpack\"]"));
        System.out.println("Add to cart button is working");
    }
    @Test(priority = 4)
    public void backToProducts(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(3000));
        WebElement element = driver.findElement(By.id("back-to-products"));

        element.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"inventory_container\"]"))); //proveri
        Assert.assertEquals(driver.getCurrentUrl(),homeUrl);
        System.out.println("The page home is open");
    }
    @Test(priority = 5)
    public void sauceLabsFleeceJacketAddCart(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(3000));
        WebElement addCartBtn = driver.findElement(By.id("add-to-cart-sauce-labs-fleece-jacket"));

        addCartBtn.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("remove-sauce-labs-fleece-jacket")));
        driver.findElement(By.id("remove-sauce-labs-fleece-jacket"));
        System.out.println("Add to cart button is working");
    }
    @Test(priority = 6)
    public void shoppingCart(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(3000));
        WebElement element = driver.findElement(By.xpath("//*[@id=\"shopping_cart_container\"]/a"));

        element.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("cart_contents_container")));
        Assert.assertEquals(driver.getCurrentUrl(),shoppingCartUrl);
        System.out.println("The page shopping cart is open");
    }
    @Test(priority = 7)
    public void shoppingCartCheckOut(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(3000));
        WebElement element = driver.findElement(By.id("checkout"));

        element.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"checkout_info_container\"]/div/form/div[1]")));
        Assert.assertEquals(driver.getCurrentUrl(),checkOutUrl);
        System.out.println("The page shopping cart check out is open");
    }
    @Test(priority = 8)
    public void continueBtn(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(3000));
        WebElement firstName = driver.findElement(By.xpath("//*[@id=\"first-name\"]"));
        WebElement lastName = driver.findElement(By.xpath("//*[@id=\"last-name\"]"));
        WebElement zipCode = driver.findElement(By.xpath("//*[@id=\"postal-code\"]"));

        firstName.sendKeys("Tina");
        lastName.sendKeys("Petrovic");
        zipCode.sendKeys("36000");

        WebElement continueButton = driver.findElement(By.xpath("//*[@id=\"continue\"]"));
        continueButton.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"checkout_summary_container\"]")));

        Assert.assertEquals(driver.getCurrentUrl(),finishUrl);
        System.out.println("The page finish is open");

    }
    @Test(priority = 9)
    public void finish(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(3000));
        WebElement finishBtn = driver.findElement(By.id("finish"));

        finishBtn.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"checkout_complete_container\"]/img")));
        Assert.assertEquals(driver.getCurrentUrl(),completeUrl);
        System.out.println("Order is send");
    }
    @Test(priority = 10)
    public void thankYouForYourOrder()
    {
        String expected = "THANK YOU FOR YOUR ORDER";
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(3000));
        WebElement thankEl = driver.findElement(By.xpath("//*[@id=\"checkout_complete_container\"]/h2"));
        Assert.assertEquals(thankEl.getText(),expected);
        System.out.println("Element thank you for your order is on page");
    }
    @Test(priority = 11)
    public void logout(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(3000));
        WebElement burgerBtn = driver.findElement(By.id("react-burger-menu-btn"));

        burgerBtn.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("logout_sidebar_link")));
        System.out.println("The page home is open");

        driver.findElement(By.id("logout_sidebar_link")).click();
        Assert.assertEquals(driver.getCurrentUrl(),url);
        System.out.println("The user is logged out");
    }
    @AfterTest
    public void terminateBrowser() {

        driver.quit();
    }
}
