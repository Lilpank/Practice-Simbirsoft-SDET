package org.example.tests;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.example.helpers.ArraysSorter;
import org.example.helpers.Filter;
import org.example.pages.CustomersPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class TestCustomersPage extends TestBasePage {
    private WebDriver driver = null;

    private CustomersPage customersPage = null;

    private SoftAssert softAssert = null;

    /**
     * A method to initialize the driver, add customer page, and soft assert.
     */
    @BeforeTest
    public void init() {
        driver = instanceDriver();
        customersPage = new CustomersPage(driver);
        customersPage.clickBtnTabCustomers();
        softAssert = new SoftAssert();
    }


    /**
     * A test to check sorting customers by firstName.
     */
    @Test(priority = 1)
    @Description("Test check sorting customers by firstName")
    @Step
    public void testSortCustomersByFirstName() {
        var customersFirstName = Filter.getFirstNamesFromCustomers(customersPage.getCustomers());

        customersPage.clickSortFirstName();
        var customersFirstNameAfterClickSort = String.join(" ", Filter.getFirstNamesFromCustomers(customersPage.getCustomers()));

        Assert.assertNotEquals(String.join(" ", customersFirstName), customersFirstNameAfterClickSort);

        ArraysSorter.toggleSortDirection((List<String>) customersFirstName);

        Assert.assertEquals((String.join(" ", customersFirstName)), customersFirstNameAfterClickSort);
    }

    /**
     * Test check delete customer by firstName
     */
    @Test(priority = 2)
    @Description("Test check delete customer by firstName")
    @Step
    public void testDeleteCustomerByAVGLengthOfFirstName() {
        var firstName = customersPage.getCustomerMinLengthFirstName();
        customersPage.deleteCustomerByFirstName(firstName);

        var customersFirstName = String.join(" ", Filter.getFirstNamesFromCustomers(customersPage.getCustomers()));

        softAssert.assertFalse(customersFirstName.contains(firstName));
    }

    @AfterTest
    public void clearDriver() {
        driver.quit();
    }
}
