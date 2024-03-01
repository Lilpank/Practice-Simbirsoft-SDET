package org.example.uiTesting.tests;

import io.qameta.allure.*;
import org.example.config.SuiteDataProvider;
import org.example.uiTesting.helpers.ArraysSorter;
import org.example.uiTesting.helpers.Filter;
import org.example.uiTesting.helpers.Utils;
import org.example.uiTesting.pages.CustomersPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import java.util.List;

public class CustomersTest extends BaseTest {
    private WebDriver driver = null;

    private CustomersPage customersPage = null;

    private final String url;

    @Factory(dataProvider = "getDataWebUrl", dataProviderClass = SuiteDataProvider.class)
    public CustomersTest(String url) {
        this.url = url;
    }

    /**
     * A method to initialize the driver, add customer page, and soft assert.
     */
    @BeforeTest
    public void init() {
        driver = instanceDriver();
        driver.get(url);
        customersPage = new CustomersPage(driver);
        customersPage.clickBtnTabCustomers();
    }

    /**
     * A test to check sorting customers by firstName.
     */

    @Test(priority = 1)
    @Epic("Customers management")
    @Feature("Sorting customers by first name")
    @Description("Test check sorting customers by firstName")
    @Severity(SeverityLevel.NORMAL)
    @Owner("DanGor")
    public void testSortCustomersByFirstName() {
        var customersFirstName = Filter.getFirstNamesFromCustomers(customersPage.getCustomers());

        customersPage.clickSortFirstName();
        var customersFirstNameAfterClickSort = Utils.joinIterableWithDelimiter(Filter.getFirstNamesFromCustomers(customersPage.getCustomers()), " ");

        Assert.assertNotEquals(Utils.joinIterableWithDelimiter(customersFirstName, " "), customersFirstNameAfterClickSort);

        ArraysSorter.toggleSortDirection((List<String>) customersFirstName);

        Assert.assertEquals((Utils.joinIterableWithDelimiter(customersFirstName, " ")), customersFirstNameAfterClickSort);
    }

    /**
     * Test check delete customer by firstName
     */
    @Test(priority = 2)
    @Epic("Customers management")
    @Feature("Delete customer by first name")
    @Description("The test checks the deletion of the client by the minimum length of the name relative to the average value")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("DanGor")
    public void testDeleteCustomerByAVGLengthOfFirstName() {
        var firstName = customersPage.findMinNameRelativeToAverage();
        customersPage.deleteCustomerByFirstName(firstName);

        var customersFirstName = Utils.joinIterableWithDelimiter(Filter.getFirstNamesFromCustomers(customersPage.getCustomers()), " ");

        Assert.assertFalse(customersFirstName.contains(firstName));
    }

    @AfterTest
    public void clearDriver() {
        driver.quit();
    }
}
