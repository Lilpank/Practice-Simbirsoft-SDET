package org.example.UITesting.pages;

import org.example.UITesting.helpers.Filter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.StreamSupport;

/**
 * Represents the page where customers can be managed.
 * Extends BasePage class.
 */
public class CustomersPage extends BasePage {
    /**
     * WebElement representing the button to switch to the "Customers" tab.
     */
    @FindBy(xpath = "//button[normalize-space()='Customers']")
    private WebElement btnTabCustomers;

    /**
     * WebElement representing the button to sort by first name.
     */
    @FindBy(xpath = "//a[normalize-space()='First Name']")
    private WebElement btnFirstName;

    /**
     * WebElement representing the input field for searching customers.
     */
    @FindBy(css = "input[placeholder='Search Customer']")
    private WebElement inputSearchCustomer;

    /**
     * Constructor for CustomersPage class.
     *
     * @param driver The WebDriver instance to be used.
     */
    public CustomersPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    /**
     * Set the input for searching a customer.
     *
     * @param searchCustomer the customer to search for
     */
    public void setInputSearchCustomer(String searchCustomer) {
        send(inputSearchCustomer, searchCustomer);
    }

    /**
     * Clicks the button to switch to the "Customers" tab.
     */
    public void clickBtnTabCustomers() {
        click(btnTabCustomers);
    }

    /**
     * Clicks the sort button for the first name.
     */
    public void clickSortFirstName() {
        click(btnFirstName);
    }

    /**
     * Retrieves the customers using a CSS selector.
     *
     * @return the text of the element found by the CSS selector
     */
    public String getCustomers() {
        return findElement(By.cssSelector(".table.table-bordered.table-striped")).getText();
    }

    /**
     * Finds the customer with the shortest first name relative to the average length of all first names.
     *
     * @return the first name of the customer with the shortest first name
     */
    public String findMinNameRelativeToAverage() {
        var customersFirstName = Filter.getFirstNamesFromCustomers(getCustomers());
        List<String> firstNames = StreamSupport.stream(customersFirstName.spliterator(), false).toList();

        double averageLength = firstNames.stream().mapToInt(String::length).average().orElse(0);

        return firstNames
                .stream()
                .min((s1, s2) -> (int) (Math.abs(s1.length() - averageLength) - Math.abs(s2.length() - averageLength)))
                .orElseThrow();
    }

    /**
     * Deletes customers by their first name.
     *
     * @param firstName the first name of the customer to be deleted
     */
    public void deleteCustomerByFirstName(String firstName) {
        setInputSearchCustomer(firstName);

        List<String> target = new ArrayList<>();
        Filter.getFirstNamesFromCustomers(getCustomers()).forEach(target::add);

        var indexes = IntStream.range(0, target.size()).filter(i -> target.get(i).equals(firstName)).boxed().toList();

        for (var index : indexes) {
            var btnDelete = findElement(By.xpath("//tbody/tr[" + (index + 1) + "]/td[5]//button[1]"));
            click(btnDelete);
        }
    }
}
