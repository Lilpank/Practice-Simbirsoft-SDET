package org.example.tests;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.example.dto.CustomerDTO;
import org.example.helpers.Generator;
import org.example.pages.AddCustomerPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TestAddCustomerPage extends TestBasePage {
    private final String notificationMessageSuccess = "Customer added successfully with customer id";

    private AddCustomerPage addCustomerPage = null;
    private WebDriver driver = null;

    private SoftAssert softAssert = null;

    /**
     * A method to initialize the driver, add customer page, and soft assert.
     */
    @BeforeTest
    public void init() {
        driver = instanceDriver();
        addCustomerPage = new AddCustomerPage(driver);
        addCustomerPage.clickBtnTabAddCustomer();
        softAssert = new SoftAssert();
    }

    /**
     * Test check create customer
     */
    @Test
    @Description("Test check create customer")
    @Step
    public void testAddCustomer() {
        var postCode = Generator.generatePostCode();
        var firstName = Generator.generateFirstName(postCode);
        var lastName = "lastName";
        CustomerDTO customer = new CustomerDTO(firstName, lastName, postCode);

        addCustomerPage.createCustomer(customer);
        addCustomerPage.clickBtnSubmitCustomer();

        String notificationMessage = driver.switchTo().alert().getText();
        softAssert.assertTrue(notificationMessage.contains(notificationMessageSuccess));
        driver.switchTo().alert().accept();
    }

    @AfterTest
    public void clearDriver() {
        driver.quit();
    }
}
