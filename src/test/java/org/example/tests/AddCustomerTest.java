package org.example.tests;

import io.qameta.allure.*;
import org.example.config.SuiteDataProvider;
import org.example.dto.CustomerDTO;
import org.example.helpers.Generator;
import org.example.pages.AddCustomerPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

public class AddCustomerTest extends BaseTest {
    private final String notificationMessageSuccess = "Customer added successfully with customer id";

    private AddCustomerPage addCustomerPage = null;
    private WebDriver driver = null;

    private final String url;

    @Factory(dataProvider = "getDataWebUrl", dataProviderClass = SuiteDataProvider.class)
    public AddCustomerTest(String url) {
        this.url = url;
    }

    /**
     * A method to initialize the driver, add customer page, and soft assert.
     */
    @BeforeTest
    public void init() {
        driver = instanceDriver();
//        driver.get(PropertyProvider.getPropertyWebUrl());
        driver.get(url);
        addCustomerPage = new AddCustomerPage(driver);
        addCustomerPage.clickBtnTabAddCustomer();
    }

    /**
     * Test check create customer
     */
    @Epic("Customers management")
    @Feature("Adding a new customer")
    @Description("Test check create customer")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("DanGor")
    @Test
    public void testAddCustomer() {
        var postCode = Generator.generatePostCode();
        var firstName = Generator.generateFirstName(postCode);
        var lastName = "lastName";
        CustomerDTO customer = new CustomerDTO(firstName, lastName, postCode);

        addCustomerPage.createCustomer(customer);
        addCustomerPage.clickBtnSubmitCustomer();

        String notificationMessage = driver.switchTo().alert().getText();
        Assert.assertTrue(notificationMessage.contains(notificationMessageSuccess));
        driver.switchTo().alert().accept();
    }

    @AfterTest
    public void clearDriver() {
        driver.quit();
    }
}
