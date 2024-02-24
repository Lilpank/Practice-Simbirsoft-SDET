package org.example.config;

import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

import java.util.Map;

public class SuiteDataProvider {
    @DataProvider(name = "getDataWebUrl")
    public static Object[][] getDataWebUrl(ITestContext context) {
        Map<String, String> suiteParams = context.getCurrentXmlTest().getSuite().getParameters();
        return new Object[][]{{suiteParams.get("webUrl")}};
    }
}
