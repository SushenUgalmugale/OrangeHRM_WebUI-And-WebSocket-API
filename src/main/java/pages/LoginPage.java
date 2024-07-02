package pages;

import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.Duration;
import java.util.Properties;

public class LoginPage {

    private WebDriver driver;
    private Properties prop;
    private By usernameField = By.xpath("//input[@name='username']");
    private By passwordField = By.xpath("//input[@name='password']");
    private By loginButton = By.xpath("//button[@type='submit']");
    private By MyInfoBtn = By.xpath("//ul/li[6]/a/span");
    private By DateOfBirthText = By.xpath("//div[@class='oxd-input-group__label-wrapper']/label[contains(text(),'Date of Birth') or contains(text(),'Fecha de Nacimiento')]/../following-sibling::div//input");
    private By BtnSave = By.xpath("(//button[@type='submit'])[1]");
    private By actualMSG = By.xpath("(//div[@class='oxd-toast-content oxd-toast-content--success']/p)[1]");
    private By DateText = By.xpath("(//div[@class='oxd-calendar-date'])[9]");
    private By UserName=By.xpath("//span[@class='oxd-userdropdown-tab']/p[@class='oxd-userdropdown-name']");
    private By LogOutBtn=By.xpath("//ul[@class='oxd-dropdown-menu']/li/a[contains(text(),'Logout')]");
    private By LoginPage=By.xpath("//div[@class='orangehrm-login-branding']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        prop = new Properties();
        try {
            FileInputStream file= new FileInputStream("src/main/resources/config.properties");
            prop.load(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void enterURL() {
        driver.get(prop.getProperty("web.url"));
    }

    public void enterUsernamePassword() {
        driver.findElement(usernameField).sendKeys(prop.getProperty("username"));
        driver.findElement(passwordField).sendKeys(prop.getProperty("password"));
    }

    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    public void dashboardpageDisplay() throws IOException, InterruptedException {
        String expectedTitle = "OrangeHRM";
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.titleIs(expectedTitle));
        Assert.assertEquals("Dashboard Page is not as displayed", expectedTitle, driver.getTitle());
        Thread.sleep(2000);
        captureScreenshot("Dashboard_page.png");
    }

    public void navigateInfopage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(MyInfoBtn)).click();
    }

    public void updatingDOB() throws InterruptedException, IOException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Thread.sleep(2000);
        wait.until(ExpectedConditions.elementToBeClickable(DateOfBirthText)).click();
        captureScreenshot("screenshot_before_update.png");
        driver.findElement(By.xpath("//li[@class='oxd-calendar-selector-month']")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//li[@class='--active oxd-calendar-selector-month']//li)[9]"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[@class='oxd-calendar-selector-year']"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//li[@class='--active oxd-calendar-selector-year']//li)[41]"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(DateText)).click();
        // Scroll up the page
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, -250)");
        captureScreenshot("Screenshot_eAfter_update.png");
        wait.until(ExpectedConditions.elementToBeClickable(BtnSave)).click();

    }

    public void verifyDateIsUpdated() throws IOException, InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfElementLocated(actualMSG));
            String actualSuccessMSG = driver.findElement(actualMSG).getText();
            Assert.assertEquals("Date is not updated", "Success", actualSuccessMSG);
            Thread.sleep(2000);
            captureScreenshot("Screenshot_SuccessMsg.png");
    }

    public void iLogoutPage() throws IOException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(UserName)).click();
        captureScreenshot("Screenshot_zLogoutButton.png");
        wait.until(ExpectedConditions.elementToBeClickable(LogOutBtn)).click();
        wait.until(ExpectedConditions.elementToBeClickable(LoginPage)).isDisplayed();
    }

    public String captureScreenshot(String filename) throws IOException {
        // Get the path to the screenshots directory within the target directory
        Path screenshotsDirectory = Paths.get("target", "screenshots");
        // Create the screenshots directory if it doesn't exist
        if (!Files.exists(screenshotsDirectory)) {
            Files.createDirectories(screenshotsDirectory);
        }
        // Define the file path for the screenshot within the screenshots directory
        Path screenshotPath = screenshotsDirectory.resolve(filename);
        // Capture the screenshot and save it to the specified file path
        File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        Files.copy(screenshotFile.toPath(), screenshotPath, StandardCopyOption.REPLACE_EXISTING);
        // Return the file path of the captured screenshot
        return screenshotPath.toString();
    }
}
