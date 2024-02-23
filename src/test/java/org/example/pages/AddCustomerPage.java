package org.example.pages;

import lombok.Getter;
import org.example.dto.CustomerDTO;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Getter
public class AddCustomerPage extends BasePage {
    @FindBy(css = ".btn.btn-lg.tab[ng-class='btnClass1']")
    private WebElement btnTabAddCustomer;

    @FindBy(xpath = " //input[@placeholder='First Name']")
    private WebElement inputFirstName;

    @FindBy(xpath = "//input[@placeholder='Last Name']")
    private WebElement inputLastName;


    @FindBy(xpath = "//input[@placeholder='Post Code']")
    private WebElement inputPostCode;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement btnSubmitCustomer;

    public AddCustomerPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void clickBtnTabAddCustomer() {
        click(btnTabAddCustomer);
    }

    public void clickBtnSubmitCustomer() {
        click(btnSubmitCustomer);
    }

    private void setInputFirstName(String firstName) {
        send(inputFirstName, firstName);
    }

    private void setInputLastName(String lastName) {
        send(inputLastName, lastName);
    }

    private void setInputPostCode(String code) {
        send(inputPostCode, code);
    }

    public void createCustomer(CustomerDTO customer) {
        setInputFirstName(customer.getFirstName());
        setInputLastName(customer.getLastName());
        setInputPostCode(customer.getPostCode());
    }
}