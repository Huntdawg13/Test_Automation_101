package stepdefinitions;

import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;

public class LoginSteps{
    WebDriver driver;

    @Given("I open the login page")
    public void i_open_the_login_page(){
      //  System.setProperty("webdriver.chrome.driver", "path/to/chromedriver"); //Must have chromedriver downloaded to run w/ correct path
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://example.com/login");
    }

    @When("I enter username {string} and password {string}")
    public void i_enter_username_and_password(String username, String password){
        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
    }

    @And("I click the login button")
    public void i_click_the_login_button(){
        driver.findElement(By.id("loginButton")).click();
    }

    @Then("I should see {string}")
    public void i_should_see(String expectedResult){
        try {
            if (expectedResult.equals("Dashboard")){
                WebElement dashboard = driver.findElement(By.id("dashboard"));
                Assert.assertTrue(dashboard.isDisplayed());
            } else if (expectedResult.equals("Error Message")){
                Alert alert = driver.switchTo().alert();
                Assert.assertTrue(alert.getText().contains("Invalid Credentials"));
                alert.accept();
            }
        } catch (NoSuchElementException e){
            Assert.fail("Expected element not found: " + expectedResult);
        } finally {
            driver.quit();
        }
    }
}