package HomeWork7lessonUPDATEDtask;

import java.util.Comparator;

public class ApiDataComparator implements Comparator<ApiData<DataItem>> {
    @Override
    public int compare(ApiData<DataItem> apiData1, ApiData<DataItem> apiData2) {
        int result = apiData1.getPagination().compareTo(apiData2.getPagination());
        if (result == 0) {
            // Якщо total однаковий, порівнюємо за першим id в Data
            int id1 = apiData1.getData().isEmpty() ? 0 : apiData1.getData().get(0).getId();
            int id2 = apiData2.getData().isEmpty() ? 0 : apiData2.getData().get(0).getId();
            return Integer.compare(id1, id2);
        }
        return result;
    }
}

