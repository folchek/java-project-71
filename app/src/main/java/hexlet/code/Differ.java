package hexlet.code;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Differ {
    public static String compare(Map<String, Object> data1, Map<String, Object> data2, String format) {
        // Получаем объединенное множество всех ключей
        Set<String> allKeys = new TreeSet<>(data1.keySet());
        allKeys.addAll(data2.keySet());

        StringBuilder result = new StringBuilder();

        // Форматируем результат в зависимости от формата (по умолчанию "stylish")
        if ("stylish".equals(format)) {
            result.append("{\n");
            for (String key : allKeys) {
                if (!data1.containsKey(key)) {
                    // Ключ добавлен во второй файл
                    result.append("  + ").append(key).append(": ").append(data2.get(key)).append("\n");
                } else if (!data2.containsKey(key)) {
                    // Ключ удален во втором файле
                    result.append("  - ").append(key).append(": ").append(data1.get(key)).append("\n");
                } else if (data1.get(key).equals(data2.get(key))) {
                    // Значение не изменилось
                    result.append("    ").append(key).append(": ").append(data1.get(key)).append("\n");
                } else {
                    // Значение изменилось
                    result.append("  - ").append(key).append(": ").append(data1.get(key)).append("\n");
                    result.append("  + ").append(key).append(": ").append(data2.get(key)).append("\n");
                }
            }
            result.append("}");
        } else if ("plain".equals(format)) {
            for (String key : allKeys) {
                if (!data1.containsKey(key)) {
                    result.append("Property '").append(key).append("' was added with value: ")
                            .append(formatValue(data2.get(key))).append("\n");
                } else if (!data2.containsKey(key)) {
                    result.append("Property '").append(key).append("' was removed\n");
                } else if (!data1.get(key).equals(data2.get(key))) {
                    result.append("Property '").append(key).append("' was updated. From ")
                            .append(formatValue(data1.get(key))).append(" to ").append(formatValue(data2.get(key)))
                            .append("\n");
                }
            }
        } else if ("json".equals(format)) {
            // Пример форматирования в JSON (можно улучшить в будущем)
            result.append("{\n");
            for (String key : allKeys) {
                result.append("  \"").append(key).append("\": ");
                if (!data1.containsKey(key)) {
                    result.append("{\"status\": \"added\", \"value\": ").append(formatJsonValue(data2.get(key))).append("}");
                } else if (!data2.containsKey(key)) {
                    result.append("{\"status\": \"removed\", \"value\": ").append(formatJsonValue(data1.get(key))).append("}");
                } else if (data1.get(key).equals(data2.get(key))) {
                    result.append("{\"status\": \"unchanged\", \"value\": ").append(formatJsonValue(data1.get(key))).append("}");
                } else {
                    result.append("{\"status\": \"changed\", \"oldValue\": ").append(formatJsonValue(data1.get(key)))
                            .append(", \"newValue\": ").append(formatJsonValue(data2.get(key))).append("}");
                }
                result.append(",\n");
            }
            result.append("}");
        }

        return result.toString();
    }
    private static String formatValue(Object value) {
        if (value instanceof String) {
            return "'" + value + "'";
        }
        if (value == null) {
            return "null";
        }
        return value.toString();
    }
    private static String formatJsonValue(Object value) {
        if (value instanceof String) {
            return "\"" + value + "\"";
        }
        if (value == null) {
            return "null";
        }
        return value.toString();
    }
}
