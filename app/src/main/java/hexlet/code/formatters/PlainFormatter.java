package hexlet.code.formatters;

import java.util.Map;

public class PlainFormatter implements Formatter {
    @Override
    public String format(Map<String, Map<String, Object>> diff) throws Exception {
        StringBuilder result = new StringBuilder();

        for (Map.Entry<String, Map<String, Object>> entry : diff.entrySet()) {
            String key = entry.getKey();
            Map<String, Object> value = entry.getValue();
            String type = (String) value.get("type");

            switch (type) {
                case "added":
                    result.append(String.format("Property '%s' was added with value: %s%n",
                            key, formatValue(value.get("value"))));
                    break;
                case "removed":
                    result.append(String.format("Property '%s' was removed%n", key));
                    break;
                case "changed":
                    result.append(String.format("Property '%s' was updated. From %s to %s%n",
                            key, formatValue(value.get("oldValue")), formatValue(value.get("newValue"))));
                    break;
                case "unchanged":
                    break; // Не выводим неизмененные свойства
                default:
                    throw new Exception("Unknown type: " + type);
            }
        }

        return result.toString().trim(); // Удаляем последний перенос строки
    }

    private String formatValue(Object value) {
        if (value == null) {
            return "null";
        }
        if (value instanceof String) {
            return "'" + value + "'";
        }
        if (value instanceof Map || value instanceof Iterable) {
            return "[complex value]";
        }
        return value.toString();
    }
}
