package utils;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.LoginPage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

public class BrowserDriver {

    private static WebDriver driver;
    private LoginPage loginPage;



    @Before
    public void initializeDriver() {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/main/resources/drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    public static WebDriver getDriver() {
        return driver;
    }



//    @After
//    public void tearDown(Scenario scenario) {
//        if (scenario.isFailed()) {
//            try {
//                // Capture screenshot and embed it in the report
//                driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//                byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
//                scenario.attach(screenshot, "image/png", "Screenshot");
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//        // Close the WebDriver instance
//        driver.close();
//    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            try {
                // Capture screenshot and embed it in the report
                byte[] screenshot = loginPage.captureScreenshot("screenshot_failure.png").getBytes();
                scenario.attach(screenshot, "image/png", "Screenshot");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        driver.close();
    }

}