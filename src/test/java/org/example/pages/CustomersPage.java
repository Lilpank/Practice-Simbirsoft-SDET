package org.example.pages;

import org.example.helpers.Filter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.StreamSupport;

public class CustomersPage extends BasePage {
    @FindBy(xpath = "//button[normalize-space()='Customers']")
    private WebElement btnTabCustomers;

    @FindBy(xpath = "//a[normalize-space()='First Name']")
    private WebElement btnfirstName;

    @FindBy(css = "input[placeholder='Search Customer']")
    private WebElement inputSearchCustomer;

    public CustomersPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void setInputSearchCustomer(String searchCustomer) {
        send(inputSearchCustomer, searchCustomer);
    }

    public void clickBtnTabCustomers() {
        click(btnTabCustomers);
    }

    public void clickSortFirstName() {
        click(btnfirstName);
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
     * @return the minimum length first name of the customers
     */
    public String getCustomerMinLengthFirstName() {
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

        if (indexes.isEmpty()) {
            return;
        }
        for (var index : indexes) {
            var btnDelete = findElement(By.xpath("//tbody/tr[" + (index + 1) + "]/td[5]//button[1]"));
            click(btnDelete);
        }
    }
}
