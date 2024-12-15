package part1;


import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
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

    public class RegistrationTest {
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
        public void testRegistration_ValidData() {
            // Open the registration page
            driver.get("https://olx.ba/register");

            // Create an explicit wait
            wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Adjust the timeout if needed

            try {
                // Wait for the email input to be clickable and interactable
                WebElement emailOrPhone = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[2]/div/div/div[2]/div[2]/div[1]/div/div/div[1]/div/input")));
                emailOrPhone.sendKeys("test1@example.com");

                // Wait for the password input to be clickable
                WebElement password = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"__layout\"]/div/div[2]/div[2]/div[1]/div/div/div[2]/div/input")));
                password.sendKeys("strongpassword"); // Valid password

                // Wait for the nickname input to be clickable
                WebElement nickname = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"__layout\"]/div/div[2]/div[2]/div[1]/div/div/div[3]/div/input")));
                String nicknameText = "testnickname1";

                // Check if the nickname exceeds 15 characters, then trim it if necessary
                if (nicknameText.length() > 15) {
                    nicknameText = nicknameText.substring(0, 15);  // Trim to 15 characters
                }
                nickname.sendKeys(nicknameText); // Valid nickname

                // Wait for the gender dropdown to be clickable
                WebElement genderDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"__layout\"]/div/div[2]/div[2]/div[1]/div/div/div[4]/select")));
                genderDropdown.click(); // Click to open gender dropdown

                // Wait for the gender option to be clickable and select it
                WebElement genderOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//option[normalize-space(text())='Ženski']")));
                genderOption.click(); // Select gender

                // Wait for the region dropdown to be clickable
                WebElement regionSelectElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"__layout\"]/div/div[2]/div[2]/div[1]/div/div/div[5]/div[1]/div[2]/select")));
                regionSelectElement.click(); // Click to open region dropdown

                // Wait for the region option to be clickable and select it
                WebElement regionOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//option[normalize-space(text())='Kanton Sarajevo']")));
                regionOption.click(); // Select region

                // Wait for the "place" dropdown to appear after selecting region
                WebElement placeSelectElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"__layout\"]/div/div[2]/div[2]/div[1]/div/div/div[5]/div[2]/div/div[2]/select")));
                placeSelectElement.click(); // Click to open place dropdown

                // Wait for the place option to be clickable and select it
                WebElement placeOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//option[normalize-space(text())='Trnovo']")));
                placeOption.click(); // Select place

                // Wait for the terms checkbox to be clickable
                WebElement termsCheckbox = wait.until(ExpectedConditions.elementToBeClickable(By.id("checkbox")));
                termsCheckbox.click(); // Agree to terms and conditions

                // Wait for the register button to be clickable
                WebElement registerButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"__layout\"]/div/div[2]/div[2]/div[1]/div/div/button")));
                registerButton.click(); // Click the register button

                // Wait for the success message or next page to load
                WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#__layout > div > div:nth-child(2) > div")));
                Assert.assertTrue(successMessage.isDisplayed(), "Registration failed: Success message not displayed.");

            } catch (ElementNotInteractableException e) {
                System.out.println("Error: Element not interactable - " + e.getMessage());
                Assert.fail("Element not interactable during registration.");
            }
        }
        @Test
        public void testRegistration_ExistingEmail() {
            driver.get("https://olx.ba/register");

            // Create an explicit wait
            wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Adjust the timeout if needed

            try {
                // Wait for the email input to be clickable and interactable
                WebElement emailOrPhone = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='__layout']/div/div[2]/div[2]/div[1]/div/div/div[1]/div/input")));
                emailOrPhone.sendKeys("test1@example.com"); // Use an already existing email

                // Wait for the password input to be clickable
                WebElement password = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"__layout\"]/div/div[2]/div[2]/div[1]/div/div/div[2]/div/input")));
                password.sendKeys("strongpassword"); // Valid password

                // Wait for the nickname input to be clickable
                WebElement nickname = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"__layout\"]/div/div[2]/div[2]/div[1]/div/div/div[3]/div/input")));
                String nicknameText = "testnickname4";

                // Check if the nickname exceeds 15 characters, then trim it if necessary
                if (nicknameText.length() > 15) {
                    nicknameText = nicknameText.substring(0, 15);  // Trim to 15 characters
                }
                nickname.sendKeys(nicknameText); // Valid nickname

                // Wait for the gender dropdown to be clickable
                WebElement genderDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"__layout\"]/div/div[2]/div[2]/div[1]/div/div/div[4]/select")));
                genderDropdown.click(); // Click to open gender dropdown

                // Wait for the gender option to be clickable and select it
                WebElement genderOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//option[normalize-space(text())='Ženski']")));
                genderOption.click(); // Select gender

                // Wait for the region dropdown to be clickable
                WebElement regionSelectElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"__layout\"]/div/div[2]/div[2]/div[1]/div/div/div[5]/div[1]/div[2]/select")));
                regionSelectElement.click(); // Click to open region dropdown

                // Wait for the region option to be clickable and select it
                WebElement regionOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//option[normalize-space(text())='Kanton Sarajevo']")));
                regionOption.click(); // Select region

                // Wait for the "place" dropdown to appear after selecting region
                WebElement placeSelectElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"__layout\"]/div/div[2]/div[2]/div[1]/div/div/div[5]/div[2]/div/div[2]/select")));
                placeSelectElement.click(); // Click to open place dropdown

                // Wait for the place option to be clickable and select it
                WebElement placeOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//option[normalize-space(text())='Trnovo']")));
                placeOption.click(); // Select place

                // Wait for the terms checkbox to be clickable
                WebElement termsCheckbox = wait.until(ExpectedConditions.elementToBeClickable(By.id("checkbox")));
                termsCheckbox.click(); // Agree to terms and conditions

                // Wait for the register button to be clickable
                WebElement registerButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"__layout\"]/div/div[2]/div[2]/div[1]/div/div/button")));
                registerButton.click(); // Click the register button

                // Wait for the error message about the email already existing
                WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Email already exists')]")));
                Assert.assertTrue(errorMessage.isDisplayed(), "Registration failed: 'Email already exists' error message not displayed.");

            } catch (ElementNotInteractableException e) {
                System.out.println("Error: Element not interactable - " + e.getMessage());
                Assert.fail("Element not interactable during registration.");
            }
        }

        @Test
        public void testRegistration_InvalidEmail() {
            driver.get("https://olx.ba/register");

            // Create an explicit wait
            wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Adjust the timeout if needed

            try {
                // Wait for the email input to be clickable and interactable
                WebElement emailOrPhone = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[2]/div/div/div[2]/div[2]/div[1]/div/div/div[1]/div/input")));

                // Use an invalid email format (missing "@" symbol)
                emailOrPhone.sendKeys("invalidemail.com");

                // Wait for the password input to be clickable
                WebElement password = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"__layout\"]/div/div[2]/div[2]/div[1]/div/div/div[2]/div/input")));
                password.sendKeys("strongpassword"); // Valid password

                // Wait for the nickname input to be clickable
                WebElement nickname = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"__layout\"]/div/div[2]/div[2]/div[1]/div/div/div[3]/div/input")));
                String nicknameText = "testnickname";

                // Check if the nickname exceeds 15 characters, then trim it if necessary
                if (nicknameText.length() > 15) {
                    nicknameText = nicknameText.substring(0, 15);  // Trim to 15 characters
                }
                nickname.sendKeys(nicknameText); // Valid nickname

                // Wait for the gender dropdown to be clickable
                WebElement genderDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"__layout\"]/div/div[2]/div[2]/div[1]/div/div/div[4]/select")));
                genderDropdown.click(); // Click to open gender dropdown

                // Wait for the gender option to be clickable and select it
                WebElement genderOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//option[normalize-space(text())='Ženski']")));
                genderOption.click(); // Select gender

                // Wait for the region dropdown to be clickable
                WebElement regionSelectElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"__layout\"]/div/div[2]/div[2]/div[1]/div/div/div[5]/div[1]/div[2]/select")));
                regionSelectElement.click(); // Click to open region dropdown

                // Wait for the region option to be clickable and select it
                WebElement regionOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//option[normalize-space(text())='Kanton Sarajevo']")));
                regionOption.click(); // Select region

                // Wait for the "place" dropdown to appear after selecting region
                WebElement placeSelectElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"__layout\"]/div/div[2]/div[2]/div[1]/div/div/div[5]/div[2]/div/div[2]/select")));
                placeSelectElement.click(); // Click to open place dropdown

                // Wait for the place option to be clickable and select it
                WebElement placeOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//option[normalize-space(text())='Trnovo']")));
                placeOption.click(); // Select place

                // Wait for the terms checkbox to be clickable
                WebElement termsCheckbox = wait.until(ExpectedConditions.elementToBeClickable(By.id("checkbox")));
                termsCheckbox.click(); // Agree to terms and conditions

                // Wait for the register button to be clickable
                WebElement registerButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"__layout\"]/div/div[2]/div[2]/div[1]/div/div/button")));
                registerButton.click(); // Click the register button

                // Wait for the error message about the invalid email format
                WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Invalid email format')]"))); // Update XPath based on actual error message text
                Assert.assertTrue(errorMessage.isDisplayed(), "Registration failed: 'Invalid email format' error message not displayed.");

            } catch (ElementNotInteractableException e) {
                System.out.println("Error: Element not interactable - " + e.getMessage());
                Assert.fail("Element not interactable during registration.");
            }
        }
    }

