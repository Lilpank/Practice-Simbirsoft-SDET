package org.example.APITesting.helpers;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.example.APITesting.pojo.EntityPojo;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

public class ApiDataProvider {
    @DataProvider(name = "entityData")
    public static Object[][] userData() throws IOException {
        Gson gson = new Gson();
        Type userListType = new TypeToken<List<EntityPojo>>() {}.getType();
        List<EntityPojo> entities = gson.fromJson(new FileReader("src/test/resources/json/entityCreationBody.json"), userListType);
        Object[][] data = new Object[entities.size()][1];
        for (int i = 0; i < entities.size(); i++) {
            data[i][0] = entities.get(i);
        }

        return data;
    }
    @DataProvider(name = "getDataApiUrl")
    public static Object[][] getDataApiUrl(ITestContext context) {
        Map<String, String> suiteParams = context.getCurrentXmlTest().getSuite().getParameters();
        return new Object[][]{{suiteParams.get("apiUrl")}};
    }
}
