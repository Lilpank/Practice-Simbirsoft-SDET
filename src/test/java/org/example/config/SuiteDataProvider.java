package org.example.config;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.example.apiTesting.pojo.EntityPojo;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

public class SuiteDataProvider {
    private static final String pathJson = "src/test/resources/json";

    /**
     * A method to provide data for WEB URL.
     *
     * @param context ITestContext
     * @return url for WEB
     */
    @DataProvider(name = "getDataWebUrl")
    public static Object[][] getDataWebUrl(ITestContext context) {
        Map<String, String> suiteParams = context.getCurrentXmlTest().getSuite().getParameters();
        return new Object[][]{{suiteParams.get("webUrl")}};
    }

    /**
     * A method to provide data for entities.
     *
     * @return an array of objects containing data for entities
     */
    @DataProvider(name = "entitiesData")
    public static Object[][] entitiesData() throws IOException {
        Gson gson = new Gson();
        Type userListType = new TypeToken<List<EntityPojo>>() {
        }.getType();
        List<EntityPojo> entities = gson.fromJson(new FileReader(pathJson + "/entityCreationBody.json"), userListType);
        Object[][] data = new Object[entities.size()][1];

        int i = 0;
        for (EntityPojo entity : entities) {
            data[i][0] = entity;
            i++;
        }

        return data;
    }
}
