package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import java.io.IOException;
import java.util.Map;

public class Parser {
    public static Map<String, Object> parse(String data, String format) throws IOException {
        ObjectMapper objectMapper = getObjectMapper(format);
        return objectMapper.readValue(data, Map.class);
    }

    private static ObjectMapper getObjectMapper(String format) throws IOException {
        return switch (format.toLowerCase()) {
            case "json" -> new ObjectMapper();
            case "yaml", "yml" -> new YAMLMapper();
            default -> throw new IOException("Unsupported data format: '%s'. Use 'json' or 'yaml'.".formatted(format));
        };
    }
}
