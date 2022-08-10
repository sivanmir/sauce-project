import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class Sauce {
    WebDriver driver;

    public Sauce() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Win10\\Desktop\\chromedriver.exe");
        this.driver = new ChromeDriver();
        this.driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
        this.driver.manage().window().maximize();
    }

    public String openSite(String url) {

        this.driver.get(url);
        return this.driver.getCurrentUrl();
    }

    public void loginAs(String UserName, String Password) {
        driver.get("https://www.saucedemo.com/");
        WebElement UserNameInput = driver.findElement(By.id("user-name"));
        UserNameInput.sendKeys(UserName);
        WebElement PasswordInput = driver.findElement(By.id("password"));
        PasswordInput.sendKeys(Password);
        driver.findElement(By.id("login-button")).click();
    }

    public String location() {
        return driver.getCurrentUrl();
    }

    public void click(String cssSelector) {

        driver.findElement(By.cssSelector(cssSelector)).click();
    }

    public void FillInfo(String FirstName, String LastName, String ZIP) {
        driver.findElement(By.id("first-name")).sendKeys(FirstName);
        driver.findElement(By.id("last-name")).sendKeys(LastName);
        driver.findElement(By.id("postal-code")).sendKeys(ZIP);
        driver.findElement(By.id("continue")).click();
        driver.findElement(By.id("finish")).click();

    }

    public void finish() {
        driver.quit();
    }

    public void logOut() {
        driver.findElement(By.id("react-burger-menu-btn")).click();
        driver.findElement(By.id("logout_sidebar_link")).click();

    }

    public void assertNotExist(String selector) {
        WebElement select = driver.findElement(By.cssSelector(selector));
        Assert.assertNull(select);
    }

    public String getText (String selector){
        return driver.findElement(By.cssSelector(selector)).getText();
    }
}
