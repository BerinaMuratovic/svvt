package part1;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class OlxShopTest {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://olx.ba/register");
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testOpenOlxShop() throws InterruptedException {
        // Klik na OLX shop tab
        WebElement olxShopTab = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"__layout\"]/div/div[2]/div[2]/div[1]/div/ul/li[2]")));
        olxShopTab.click();

        // Dodaj 30 sekundi pauze
        Thread.sleep(10000); // Vrijeme u milisekundama (30 sec)

        System.out.println("Test traje 30 sekundi i zatim završava.");


    }
    @Test(dependsOnMethods = {"testOpenOlxShop"})

    public void testOlxShop_ValidData() {
        driver.get("https://olx.ba/register");

        // Create an explicit wait
        wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Adjust the timeout if needed

        try {
            // Locate the OLX Shop tab
            WebElement olxShopTab = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"__layout\"]/div/div[2]/div[2]/div[1]/div/ul/li[2]")));

            // Check if OLX shop is active
            if (olxShopTab.getAttribute("class").contains("active")) {
                System.out.println("OLX shop is active.");

                // Interact with the email input
                WebElement emailInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"__layout\"]/div/div[2]/div[2]/div[1]/div/div/div[1]/div/input")));
                emailInput.sendKeys("test1@example.com");
            } else {
                // Handle the case where the Classic profile is active
                System.out.println("OLX shop is not active. Switching to the correct tab or skipping.");
                // Optionally, click to activate the OLX shop tab
                olxShopTab.click();

                // Wait for the tab to become active
                wait.until(ExpectedConditions.attributeContains(olxShopTab, "class", "active"));

                // Interact with the email input after switching
                WebElement emailInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"__layout\"]/div/div[2]/div[2]/div[1]/div/div/div[1]/div/input")));
                emailInput.sendKeys("test1@example.com");
            }

        } catch (Exception e) {
            System.out.println("Error during test execution: " + e.getMessage());
            Assert.fail("Test failed due to exception: " + e.getMessage());
        }
    }

}
