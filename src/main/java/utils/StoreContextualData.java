package utils;

import java.util.HashMap;

public class StoreContextualData {

    private static HashMap<Object, Object> dataMap = new HashMap<>();

    public static void  setData(StoredValues values, Object data) {
        dataMap.put(values, data);
    }

    public static <T> T getData(StoredValues value) {
        return (T) dataMap.get(value);
    }

    public enum StoredValues {
        EMAIL_AMOUNT
    }
}
