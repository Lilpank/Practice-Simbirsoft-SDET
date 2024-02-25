package org.example.pages;

import lombok.Getter;
import org.example.dto.CustomerDTO;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Represents the page where a new customer can be added.
 * Extends BasePage class.
 */
@Getter
public class AddCustomerPage extends BasePage {
    /**
     * WebElement representing the button to switch to the "Add Customer" tab.
     */
    @FindBy(xpath = "//button[normalize-space()='Add Customer']")
    private WebElement btnTabAddCustomer;

    /**
     * WebElement representing the input field for the first name of the customer.
     */
    @FindBy(xpath = " //input[@placeholder='First Name']")
    private WebElement inputFirstName;

    /**
     * WebElement representing the input field for the last name of the customer.
     */
    @FindBy(xpath = "//input[@placeholder='Last Name']")
    private WebElement inputLastName;

    /**
     * WebElement representing the input field for the post code of the customer.
     */
    @FindBy(xpath = "//input[@placeholder='Post Code']")
    private WebElement inputPostCode;

    /**
     * WebElement representing the submit button to add the customer.
     */
    @FindBy(xpath = "//button[@type='submit']")
    private WebElement btnSubmitCustomer;

    /**
     * Constructor for AddCustomerPage class.
     *
     * @param driver The WebDriver instance to be used.
     */
    public AddCustomerPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    /**
     * Clicks the 'Add Customer' button tab.
     */
    public void clickBtnTabAddCustomer() {
        click(btnTabAddCustomer);
    }

    /**
     * Clicks the submit customer button.
     */
    public void clickBtnSubmitCustomer() {
        click(btnSubmitCustomer);
    }

    /**
     * Sets the input first name.
     *
     * @param firstName the first name to set
     */
    private void setInputFirstName(String firstName) {
        send(inputFirstName, firstName);
    }

    /**
     * Sets the last name input.
     *
     * @param lastName the last name to be set
     */
    private void setInputLastName(String lastName) {
        send(inputLastName, lastName);
    }

    /**
     * Sets the input post code.
     *
     * @param code the input post code to set
     */
    private void setInputPostCode(String code) {
        send(inputPostCode, code);
    }

    /**
     * Creates a new customer by filling in the input fields with the provided CustomerDTO object.
     *
     * @param customer The CustomerDTO object containing customer details.
     */
    public void createCustomer(CustomerDTO customer) {
        setInputFirstName(customer.getFirstName());
        setInputLastName(customer.getLastName());
        setInputPostCode(customer.getPostCode());
    }
}