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
    private static final String pathJson = "src/test/resources/json";

    /**
     * A method to provide data for entities.
     *
     * @return         	an array of objects containing data for entities
     */
    @DataProvider(name = "entitiesData")
    public static Object[][] entitiesData() throws IOException {
        Gson gson = new Gson();
        Type userListType = new TypeToken<List<EntityPojo>>() {
        }.getType();
        List<EntityPojo> entities = gson.fromJson(new FileReader(pathJson + "/entityCreationBody.json"), userListType);
        Object[][] data = new Object[entities.size()][1];
        for (int i = 0; i < entities.size(); i++) {
            data[i][0] = entities.get(i);
        }
        return data;
    }

    /**
     * A method to provide data for API URL.
     *
     * @param  context	ITestContext
     * @return         	url for API
     */
    @DataProvider(name = "getDataApiUrl")
    public static Object[][] getDataApiUrl(ITestContext context) {
        Map<String, String> suiteParams = context.getCurrentXmlTest().getSuite().getParameters();
        return new Object[][]{{suiteParams.get("apiUrl")}};
    }
}
