package hexlet.code;

import java.util.Map;
import java.util.TreeMap;
import java.util.HashMap;

public class DiffCalculator {
    public static Map<String, Map<String, Object>> calculate(
            Map<String, Object> data1,
            Map<String, Object> data2
    ) {
        Map<String, Map<String, Object>> diff = new TreeMap<>();

        Map<String, Boolean> allKeys = new HashMap<>();
        data1.keySet().forEach(key -> allKeys.put(key, true));
        data2.keySet().forEach(key -> allKeys.put(key, true));

        for (String key : allKeys.keySet()) {
            Map<String, Object> keyInfo = new HashMap<>();

            if (!data2.containsKey(key)) {
                keyInfo.put("type", "removed");
                keyInfo.put("value", data1.get(key));
            } else if (!data1.containsKey(key)) {
                keyInfo.put("type", "added");
                keyInfo.put("value", data2.get(key));
            } else if (!data1.get(key).equals(data2.get(key))) {
                keyInfo.put("type", "changed");
                keyInfo.put("oldValue", data1.get(key));
                keyInfo.put("newValue", data2.get(key));
            } else {
                keyInfo.put("type", "unchanged");
                keyInfo.put("value", data1.get(key));
            }

            diff.put(key, keyInfo);
        }

        return diff;
    }
}
