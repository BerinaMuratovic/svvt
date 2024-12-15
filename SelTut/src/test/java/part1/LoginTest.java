package part1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class LoginTest {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://olx.ba/login");
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testLogin_Success() throws InterruptedException {
        Thread.sleep(2000);
        // Enter valid username and password
        WebElement username = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username")));
        username.sendKeys("berina211");

        WebElement password = driver.findElement(By.name("password"));
        password.sendKeys("berininasifra");

        WebElement loginButton = driver.findElement(By.cssSelector("button.my-lg"));
        loginButton.click();

        // Assert: Check if redirected to user profile page or dashboard
        WebElement profileSection = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("__nuxt"))); // Replace "profile-section" with the actual ID
        Assert.assertTrue(profileSection.isDisplayed(), "Login failed: Profile section not visible.");
    }

    @Test
    public void testLogin_Failure() {
        // Enter invalid username and password
        WebElement username = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username")));
        username.sendKeys("wrong_username");

        WebElement password = driver.findElement(By.name("password"));
        password.sendKeys("wrong_password");

        WebElement loginButton = driver.findElement(By.cssSelector("button.my-lg"));
        loginButton.click();

        // Assert: Check for error message
        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".alert-danger"))); //
        Assert.assertTrue(errorMessage.isDisplayed(), "Login failure: Error message not shown.");
    }

    @Test
    public void testLogin_EmptyFields() {
        // Leave both username and password fields empty
        WebElement username = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username")));
        username.clear();  // Ensure the field is empty

        WebElement password = driver.findElement(By.name("password"));
        password.clear();  // Ensure the field is empty

        WebElement loginButton = driver.findElement(By.cssSelector("button.my-lg"));
        loginButton.click();

        // Assert: Check for error message or validation message indicating that fields cannot be empty
        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".alert-danger"))); // Replace with actual CSS class for empty field error
        Assert.assertTrue(errorMessage.isDisplayed(), "Login failure: Error message not shown for empty fields.");
    }
}