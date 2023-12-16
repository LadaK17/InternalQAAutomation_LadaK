package HomeWork7lessonUPDATEDtask;
import java.util.Objects;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Test {
    public static void main(String[] args) {
        // Тестові дані
        String json1 = "{\"data\":[{\"id\":1,\"name\":\"John Doe\"},{\"id\":2,\"name\":\"Jane Smith\"},{\"id\":3,\"name\":\"Bob Johnson\"}],\"pagination\":{\"limit\":10,\"offset\":0,\"total\":3}}";
        String json2 = "{\"data\":[{\"id\":4,\"name\":\"Alice Brown\"},{\"id\":5,\"name\":\"Charlie Davis\"}],\"pagination\":{\"limit\":10,\"offset\":0,\"total\":2}}";

        // Десеріалізація JSON у об'єкти
        ApiData<DataItem> apiData1 = parseJson(json1, DataItem.class);
        ApiData<DataItem> apiData2 = parseJson(json2, DataItem.class);

        // Порівняння за total за допомогою Comparable
        if (apiData1 != null && apiData2 != null) {
            boolean result = apiData1.getPagination().equals(apiData2.getPagination());
            System.out.println(result);
        } else {
            System.out.println("Error in deserialization");
        }
    }

    // Метод для десеріалізації JSON-рядка у об'єкт
    private static <T> ApiData<T> parseJson(String json, Class<T> itemType) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(json, objectMapper.getTypeFactory().constructParametricType(ApiData.class, itemType));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
