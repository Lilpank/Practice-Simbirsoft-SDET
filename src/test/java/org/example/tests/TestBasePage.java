package org.example.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeSuite;

public class TestBasePage {
    private static final String url = "https://www.globalsqa.com/angularJs-protractor/BankingProject/#/manager";

    public WebDriver chromeDriver = null;

    /**
     * Initialize the webdriver before running the test class.
     */
    @BeforeSuite
    public void init() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver/chromedriver.exe");
    }

    /**
     * Create a new instance of the webdriver.
     *
     * @return WebDriver
     */
    public WebDriver instanceDriver() {
        if (chromeDriver == null) {
            chromeDriver = new ChromeDriver();
            chromeDriver.get(url);
            return chromeDriver;
        }
        return chromeDriver;
    }
}
