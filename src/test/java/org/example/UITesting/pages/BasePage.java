package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage implements IBasePage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    private void waitForVisibilityOfElement(WebElement webElement) {
        wait.until(ExpectedConditions.visibilityOf(webElement));
    }

    private void waitForVisibilityOfElementLocated(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    @Override
    public void click(WebElement webElement) {
        waitForVisibilityOfElement(webElement);
        webElement.click();
    }

    @Override
    public void send(WebElement webElement, String text) {
        waitForVisibilityOfElement(webElement);
        webElement.sendKeys(text);
    }

    @Override
    public WebElement findElement(By locator) {
        waitForVisibilityOfElementLocated(locator);
        return driver.findElement(locator);
    }
}