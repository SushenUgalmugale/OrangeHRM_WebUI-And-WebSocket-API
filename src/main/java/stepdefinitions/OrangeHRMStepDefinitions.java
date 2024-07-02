package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import utils.BrowserDriver;
import java.io.IOException;

public class OrangeHRMStepDefinitions {

    private WebDriver driver;
    private LoginPage loginPage;

    public OrangeHRMStepDefinitions() {
        // Initialize LoginPage with WebDriver instance
        driver = BrowserDriver.getDriver();
        loginPage = new LoginPage(driver);
    }

    @Given("I am on the OrangeHRM login page")
    public void iAmOnTheOrangeHRMLoginPage() {
        loginPage.enterURL();
    }

    @When("I enter username and password for OrangeHRM")
    public void iEnterUsernameAndPassword() {
        loginPage.enterUsernamePassword();
    }

    @And("I click the login button")
    public void iClickTheLoginButton() {
        loginPage.clickLoginButton();
    }

    @Then("I should see the dashboard")
    public void seetheDashboardPage() throws IOException, InterruptedException {
        loginPage.dashboardpageDisplay();
    }

    @And("I navigate to My Info page")
    public void naviagteMyInfoPage() {
        loginPage.navigateInfopage();
    }

    @And("I update the Date of Birth field and save")
    public void DOBUpdate() throws InterruptedException, IOException {
        loginPage.updatingDOB();
    }

    @Then("My Info page should be updated with the new Date of Birth")
    public void verifyDOBIsUpdated() throws IOException, InterruptedException {
        loginPage.verifyDateIsUpdated();
    }

    @Then("I navigate to the login page after clicking on logout button")
    public void logoutpage() throws IOException {
        loginPage.iLogoutPage();
    }
}