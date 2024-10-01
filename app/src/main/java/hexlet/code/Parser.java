package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class Parser {
    public static Map<String, Object> parse(String filepath) throws IOException {
        ObjectMapper objectMapper;

        if (filepath.endsWith(".yaml") || filepath.endsWith(".yml")) {
            objectMapper = new ObjectMapper(new YAMLFactory());  // YAML парсер
        } else {
            objectMapper = new ObjectMapper();  // JSON парсер
        }
        return objectMapper.readValue(new File(filepath), Map.class);
    }
}

