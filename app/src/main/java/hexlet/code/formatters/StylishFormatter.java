package hexlet.code.formatters;

import java.util.Map;

public class StylishFormatter implements Formatter {
    @Override
    public String format(Map<String, Map<String, Object>> diff) {
        StringBuilder result = new StringBuilder("{\n");

        for (Map.Entry<String, Map<String, Object>> entry : diff.entrySet()) {
            String key = entry.getKey();
            Map<String, Object> value = entry.getValue();
            String type = (String) value.get("type");

            switch (type) {
                case "removed":
                    result.append(String.format("  - %s: %s%n", key, value.get("value")));
                    break;
                case "added":
                    result.append(String.format("  + %s: %s%n", key, value.get("value")));
                    break;
                case "changed":
                    result.append(String.format("  - %s: %s%n", key, value.get("oldValue")));
                    result.append(String.format("  + %s: %s%n", key, value.get("newValue")));
                    break;
                case "unchanged":
                    result.append(String.format("    %s: %s%n", key, value.get("value")));
                    break;
                default:
                    throw new RuntimeException("Unknown type: " + type);
            }
        }

        result.append("}");
        return result.toString();
    }
}
